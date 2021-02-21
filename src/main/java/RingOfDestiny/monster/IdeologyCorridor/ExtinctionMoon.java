package RingOfDestiny.monster.IdeologyCorridor;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.unique.ChangeStageAction;
import RingOfDestiny.actions.unique.CustomWaitAction;
import RingOfDestiny.actions.unique.UpgardeStateAction;
import RingOfDestiny.vfx.combat.*;
import basemod.abstracts.CustomMonster;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.*;

import com.esotericsoftware.spine.AnimationState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import java.util.Iterator;

import RingOfDestiny.vfx.HealVerticalLineButHorizontalEffect;
import RingOfDestiny.helpers.*;


public class ExtinctionMoon extends CustomMonster {
    public static final String ID = RingOfDestiny.makeID("ExtinctionMoon");
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;

    private int moveCount = 0;
    private boolean isFirstMove = true;

    private int firstAttackAmount = 2;
    private int poisonAmount = 5;
    private int block = 10;
    private int debuff = -1;


    public boolean superMoves = false;


    public ExtinctionMoon() {
        super(NAME, ID, 88, 0.0F, -15.0F, 360.0F, 360.0F, null, 0.0F, -10.0F);

        if (AbstractDungeon.ascensionLevel >= 8) {
            setHp(82, 88);
        } else {
            setHp(82, 88);
        }


        if (AbstractDungeon.ascensionLevel >= 4) {
            this.damage.add(new DamageInfo(this, 2));
        } else {
            this.damage.add(new DamageInfo(this, 3));
        }


        this.type = AbstractMonster.EnemyType.ELITE;
        this.dialogX = -50.0F * Settings.scale;
        this.dialogY = 50.0F * Settings.scale;


        loadAnimation("RingOfDestiny/monsters/IdeologyCorridor/ExtinctionMoon/ExtinctionMoon.atlas", "RingOfDestiny/monsters/IdeologyCorridor/ExtinctionMoon/ExtinctionMoon.json", 1.75F);


        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.flipHorizontal = false;

    }


    public void usePreBattleAction() {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("fight_elite");
    }


    public void takeTurn() {

        switch (this.nextMove) {
            case 0:
                addToBot(new ChangeStateAction(this, "Attack"));

                if (this.isFirstMove) {
                    addToBot(new TalkAction(this, DIALOG[0], 1.0F, 2.0F));
                    addToBot(new CustomWaitAction(0.6f));
                    this.isFirstMove = false;
                }else if(Settings.FAST_MODE){
                    addToBot(new CustomWaitAction(Settings.ACTION_DUR_MED + 0.6f));
                }else {
                    addToBot(new CustomWaitAction(1.6f));
                }


                for (int i = 0; i < firstAttackAmount; i++)
                    addToBot(new DamageAction(AbstractDungeon.player, (DamageInfo) this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HEAVY, true));

                addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new PoisonPower(AbstractDungeon.player, this, poisonAmount), poisonAmount));


                break;

            case 1:
                addToBot(new GainBlockAction(this, this, block));
                break;


            case 2:
                addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new StrengthPower(AbstractDungeon.player, debuff), debuff));

                break;
        }

        addToBot(new RollMoveAction(this));

    }


    protected void getMove(int num) {
        switch (this.moveCount % 3) {
            case 0:
                if (AbstractDungeon.aiRng.randomBoolean()) {
                    setMove((byte) 0, Intent.ATTACK_DEBUFF, ((DamageInfo) this.damage.get(0)).base, firstAttackAmount, true);
                    break;
                }
                setMove((byte) 1, Intent.DEFEND);
                break;

            case 1:
                if (!lastMove((byte) 1)) {
                    setMove((byte) 1, Intent.DEFEND);
                    break;
                }
                setMove((byte) 0, Intent.ATTACK_DEBUFF, ((DamageInfo) this.damage.get(0)).base, 2, true);
                break;


            default:
                setMove((byte) 2, Intent.STRONG_DEBUFF);
                break;
        }

        this.moveCount++;
    }


    public void die() {
        super.die();
        AbstractDungeon.scene.fadeInAmbiance();
        CardCrawlGame.music.fadeOutTempBGM();

        this.deathTimer += 1.5f;
        this.state.setAnimation(0, "Corpse", false);

    }

    public void damage(DamageInfo info) {
        int prevhp = this.currentHealth;

        super.damage(info);

        if (prevhp > this.currentHealth && this.currentHealth > 0) {
            this.state.setAnimation(0, "Hit", false);
            this.state.addAnimation(0, "Idle", true, 0.0F);
        }
    }

    public void changeState(String stateName) {
        switch (stateName) {
            case "Attack":
                this.state.setAnimation(0, "Attack1", false);
                this.state.addAnimation(0, "Idle", true, 0.0F);
                break;
        }
    }


}

