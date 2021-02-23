package RingOfDestiny.monster.IdeologyCorridor;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.unique.ApplyVenomPowerToMinionAction;
import RingOfDestiny.actions.unique.ChangeStageAction;
import RingOfDestiny.actions.unique.CustomWaitAction;
import RingOfDestiny.actions.unique.UpgardeStateAction;
import RingOfDestiny.powers.Monster.IdeologyCorridor.KnitmeshPower;
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


public class SpiderQueen extends CustomMonster {
    public static final String ID = RingOfDestiny.makeID("SpiderQueen");
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;

    private int moveCount = 0;
    private int[] remainHpToLeave = new int[]{100, 60, 30};
    private int leaveNum = 0;
    private int saveHp = 0;

    private boolean intendChanged = false;


    public SpiderQueen() {
        super(NAME, ID, 88, 0.0F, -15.0F, 360.0F, 360.0F, null, 0.0F, 150.0F);

        if (AbstractDungeon.ascensionLevel >= 9) {
            setHp(150);
        } else {
            setHp(150);
        }


        if (AbstractDungeon.ascensionLevel >= 4) {
            this.damage.add(new DamageInfo(this, 10));
        } else {
            this.damage.add(new DamageInfo(this, 10));
        }


        this.type = EnemyType.BOSS;
        this.dialogX = -50.0F * Settings.scale;
        this.dialogY = 50.0F * Settings.scale;


        loadAnimation("RingOfDestiny/monsters/IdeologyCorridor/SpiderQueen/SpiderQueen.atlas", "RingOfDestiny/monsters/IdeologyCorridor/SpiderQueen/SpiderQueen.json", 1.75F);


        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle1", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.flipHorizontal = false;

    }


    public void usePreBattleAction() {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("fight_boss");
    }


    public void takeTurn() {

        switch (this.nextMove) {
            case 0:
                addToBot(new ChangeStateAction(this, "Attack"));
                addToBot(new CustomWaitAction(0.45f));
                addToBot(new DamageAction(AbstractDungeon.player, (DamageInfo) this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                break;

            case 1:
                addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new KnitmeshPower(AbstractDungeon.player), 1));
                break;


            case 2:
                addToBot(new ChangeStateAction(this, "Leave"));
                addToBot(new TalkAction(this, DIALOG[0], 1.0F, 2.0F));
                addToBot(new CustomWaitAction(0.6f));

                addToBot(new SpawnMonsterAction(new SpiderChildG(-300.0F, 0.0F), true, 1));
                addToBot(new SpawnMonsterAction(new SpiderChildG(-80.0F, 0.0F), true, 2));
                addToBot(new SpawnMonsterAction(new SpiderChildG(140.0F, 0.0F), true, 3));

                addToBot(new ApplyVenomPowerToMinionAction(this));
                break;

            default:
                break;
        }

        addToBot(new RollMoveAction(this));

    }


    protected void getMove(int num) {
        if (this.halfDead) {
            setMove((byte) 3, Intent.UNKNOWN);
            return;
        }

        switch (this.moveCount % 3) {
            case 2:
                setMove((byte) 1, Intent.STRONG_DEBUFF);
                break;

            default:
                setMove((byte) 0, Intent.ATTACK, ((DamageInfo) this.damage.get(0)).base);
                break;
        }

        this.moveCount++;
    }


    public void die() {
        useFastShakeAnimation(5.0F);
        CardCrawlGame.screenShake.rumble(4.0F);

        super.die();

        AbstractDungeon.scene.fadeInAmbiance();
        CardCrawlGame.music.fadeOutTempBGM();
        onBossVictoryLogic();


        this.deathTimer += 1.5f;
        this.state.setAnimation(0, "Corpse", false);

    }

    public void damage(DamageInfo info) {
        super.damage(info);

        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0 && currentHealth > 0) {
            this.state.setAnimation(0, "Hit", false);
            this.state.addAnimation(0, "Idle1", true, 0.0F);
        }

        if (this.leaveNum < 2) {
            if (this.currentHealth < this.remainHpToLeave[this.leaveNum + 1]){
                this.leaveNum ++;
            }
        }


        if (this.leaveNum <= 2 && !this.intendChanged) {
            if (this.currentHealth < this.remainHpToLeave[this.leaveNum]) {
                setMove((byte) 2, Intent.UNKNOWN);
                createIntent();
                this.intendChanged = true;
            }
        }
        if(this.saveHp - info.output >= 1){
            this.saveHp -= info.output;
        }else {
            this.saveHp = 1;
        }



    }

    public void changeState(String stateName) {
        switch (stateName) {
            case "Attack":
                this.state.setAnimation(0, "Attack", false);
                this.state.addAnimation(0, "Idle1", true, 0.0F);
                break;

            case "Leave":
                this.state.setAnimation(0, "Leave", false);
                this.state.addAnimation(0, "Idle2", true, 0.0F);
                this.leaveNum++;
                this.halfDead = true;
                (AbstractDungeon.getCurrRoom()).cannotLose = true;
                this.saveHp = this.currentHealth;
                this.currentHealth = 0;
                healthBarUpdatedEvent();
                break;

            case "Back":
                loadAnimation("RingOfDestiny/monsters/IdeologyCorridor/SpiderQueen/SpiderQueen.atlas", "RingOfDestiny/monsters/IdeologyCorridor/SpiderQueen/SpiderQueen.json", 1.75F);
                this.state.setAnimation(0, "Back", false);
                this.state.addAnimation(0, "Idle1", true, 0.0F);
                this.intendChanged = false;
                this.currentHealth = this.saveHp;
                healthBarUpdatedEvent();
                this.halfDead = false;
                (AbstractDungeon.getCurrRoom()).cannotLose = false;
                this.moveCount = 1;
                setMove((byte) 0, Intent.ATTACK, this.damage.get(0).base);
                createIntent();
                break;
        }
    }


}

