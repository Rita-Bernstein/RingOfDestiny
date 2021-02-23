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


public class TalkingChest extends CustomMonster {
    public static final String ID = RingOfDestiny.makeID("TalkingChest");
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;

    private int moveCount = 0;
    private boolean isFirstMove = true;
    private boolean changed = false;


    private int thornAmount = 3;
    private int thornBlock = 5;

    private int block = 10;



    public TalkingChest() {
        super(NAME, ID, 88, 0.0F, -15.0F, 360.0F, 360.0F, null, 0.0F, -10.0F);

        if (AbstractDungeon.ascensionLevel >= 8) {
            setHp(75, 85);
        } else {
            setHp(75, 85);
        }


        if (AbstractDungeon.ascensionLevel >= 3) {
            this.damage.add(new DamageInfo(this, 21));
        } else {
            this.damage.add(new DamageInfo(this, 20));
        }


        this.type = AbstractMonster.EnemyType.ELITE;
        this.dialogX = -50.0F * Settings.scale;
        this.dialogY = 50.0F * Settings.scale;


        loadAnimation("RingOfDestiny/monsters/IdeologyCorridor/TalkingChest/TalkingChest.atlas", "RingOfDestiny/monsters/IdeologyCorridor/TalkingChest/TalkingChest.json", 1.75F);


        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle2", true);
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

                addToBot(new ApplyPowerAction(this, this, new ThornsPower(this, thornAmount), thornAmount));
                addToBot(new GainBlockAction(this, this, thornBlock));

                break;

            case 1:
                addToBot(new GainBlockAction(this, this, this.block));
                break;


            case 2:
                addToBot(new ChangeStateAction(this, "Attack"));
                addToBot(new CustomWaitAction(0.3f));
                addToBot(new DamageAction(AbstractDungeon.player, (DamageInfo) this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                break;

            case 3:
                addToBot(new GainBlockAction(this, this, this.block));
                addToBot(new TalkAction(this, DIALOG[0], 1.0F, 2.0F));
                break;

            case 4:
                addToBot(new EscapeAction(this));
                (AbstractDungeon.getCurrRoom()).mugged = true;
                addToBot(new SetMoveAction(this, (byte)4, AbstractMonster.Intent.ESCAPE));
                break;
        }

        addToBot(new RollMoveAction(this));

    }


    protected void getMove(int num) {
        if (this.isFirstMove) {
            setMove((byte) 0, Intent.UNKNOWN);
            this.isFirstMove = false;
            return;
        }

        switch (this.moveCount % 5) {
            case 0:
                setMove((byte) 1, Intent.DEFEND);
                break;

            case 1:
                setMove((byte) 1, Intent.DEFEND);
                break;

            case 2:
                setMove((byte) 2, Intent.ATTACK, ((DamageInfo) this.damage.get(0)).base);
                break;

            case 3:
                setMove((byte) 3, Intent.DEFEND);
                break;

            default:
                setMove((byte) 4, Intent.ESCAPE);
                break;
        }

        this.moveCount++;
    }


    public void die() {
        super.die();
        AbstractDungeon.scene.fadeInAmbiance();
        CardCrawlGame.music.fadeOutTempBGM();
    }

    public void damage(DamageInfo info) {


        super.damage(info);

        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0 )  {
            if (changed) {
                this.state.setAnimation(0, "Hit1", false);
                this.state.addAnimation(0, "Idle1", true, 0.0F);
            } else {
                this.state.setAnimation(0, "Hit2", false);
                this.state.addAnimation(0, "Idle2", true, 0.0F);
            }
        }
    }

    public void changeState(String stateName) {
        switch (stateName) {
            case "Attack":
                loadAnimation("RingOfDestiny/monsters/IdeologyCorridor/TalkingChest/TalkingChest.atlas", "RingOfDestiny/monsters/IdeologyCorridor/TalkingChest/TalkingChest.json", 1.75F);

                AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle1", true);
                e.setTime(e.getEndTime() * MathUtils.random());

                this.state.setAnimation(0, "Attack", false);
                this.state.addAnimation(0, "Idle1", true, 0.0F);

                break;
        }
    }


}

