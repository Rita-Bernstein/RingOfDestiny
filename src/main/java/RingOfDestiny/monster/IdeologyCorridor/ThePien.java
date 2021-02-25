package RingOfDestiny.monster.IdeologyCorridor;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.unique.*;
import RingOfDestiny.monster.AbstractRingMonster;
import RingOfDestiny.powers.Monster.IdeologyCorridor.ResurrectionPower;
import RingOfDestiny.vfx.combat.*;
import basemod.abstracts.CustomMonster;

import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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

import java.util.ArrayList;
import java.util.Iterator;

import RingOfDestiny.vfx.HealVerticalLineButHorizontalEffect;
import RingOfDestiny.helpers.*;


public class ThePien extends AbstractRingMonster {
    public static final String ID = RingOfDestiny.makeID("ThePien");
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;

    private int moveCount = 0;
    private int[] hits = new int[]{8, 5, 3};
    private int times;


    public ThePien() {
        this(0);
    }

    public ThePien(int times) {
        super(NAME, ID, 88, 0.0F, -25.0F, 420.0F * (float) Math.pow(0.75,times), 380.0F * (float) Math.pow(0.75,times), null, 0.0F, -10.0F);
        this.times = times;

        if (AbstractDungeon.ascensionLevel >= 9) {
            setHp(140 / (int) Math.pow(2, this.times));
        } else {
            setHp(140 / (int) Math.pow(2, this.times));
        }


        if (AbstractDungeon.ascensionLevel >= 4) {
            this.damage.add(new DamageInfo(this, 2));
            this.damage.add(new DamageInfo(this, 3));
            this.damage.add(new DamageInfo(this, 4));
            this.damage.add(new DamageInfo(this, 3));
        } else {
            this.damage.add(new DamageInfo(this, 1));
            this.damage.add(new DamageInfo(this, 2));
            this.damage.add(new DamageInfo(this, 3));
            this.damage.add(new DamageInfo(this, 2));
        }


        this.type = EnemyType.BOSS;
        this.dialogX = -50.0F * Settings.scale;
        this.dialogY = 50.0F * Settings.scale;


        loadAnimation("RingOfDestiny/monsters/IdeologyCorridor/ThePien/ThePien.atlas", "RingOfDestiny/monsters/IdeologyCorridor/ThePien/ThePien.json", 1.1f / (float) Math.pow(0.75d, this.times));


        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.flipHorizontal = false;

    }


    public void usePreBattleAction() {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("fight_boss");

        addToBot(new ApplyPowerAction(this, this, new ResurrectionPower(this)));
    }


    public void takeTurn() {
        switch (this.nextMove) {
            case 0:
                addToBot(new TalkAction(this, DIALOG[0], 1.0F, 2.0F));
                addToBot(new AnimateSlowAttackAction(this));
                if (times <= 2) {
                    for (int i = 0; i < hits[0]; i++)
                        addToBot(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
                } else {
                    for (int i = 0; i < hits[0]; i++)
                        addToBot(new DamageAction(AbstractDungeon.player, this.damage.get(3), AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
                }

                break;

            case 1:
                addToBot(new AnimateSlowAttackAction(this));
                for (int i = 0; i < hits[1]; i++)
                    addToBot(new DamageAction(AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
                break;

            case 2:
                addToBot(new AnimateSlowAttackAction(this));
                for (int i = 0; i < hits[2]; i++)
                    addToBot(new DamageAction(AbstractDungeon.player, this.damage.get(2), AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));

                addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new VulnerablePower(AbstractDungeon.player, 2, true), 2));
                break;

            default:
                break;
        }

        addToBot(new RollMoveAction(this));

    }


    protected void getMove(int num) {

        setMove((byte) 3, Intent.UNKNOWN);

        switch (this.times) {
            case 0:
                switch (this.moveCount % 2) {
                    case 0:

                        setMove((byte) 1, Intent.ATTACK, this.damage.get(1).base, this.hits[1], true);
                        break;


                    case 1:
                        setMove((byte) 2, Intent.ATTACK_DEBUFF, this.damage.get(2).base, this.hits[2], true);
                        break;
                }
                break;


            case 1:
                switch (this.moveCount % 3) {
                    case 0:
                        setMove((byte) 3, Intent.UNKNOWN);
                        break;

                    case 1:
                        setMove((byte) 0, Intent.ATTACK, this.damage.get(0).base, this.hits[0], true);
                        break;


                    case 2:
                        setMove((byte) 1, Intent.ATTACK, this.damage.get(1).base, this.hits[1], true);
                        break;
                }
                break;

            case 2:
                switch (this.moveCount % 3) {
                    case 0:
                        setMove((byte) 3, Intent.UNKNOWN);
                        break;

                    case 1:
                        setMove((byte) 0, Intent.ATTACK, this.damage.get(3).base, this.hits[0], true);
                        break;

                    case 2:
                        setMove((byte) 2, Intent.ATTACK_DEBUFF, this.damage.get(2).base, this.hits[2], true);
                        break;
                }
                break;

            default:
                setMove((byte) 3, Intent.UNKNOWN);
                break;
        }
        this.moveCount++;
    }


    public void die() {
        if (times <= 1)
            (AbstractDungeon.getCurrRoom()).cannotLose = true;
        else
            (AbstractDungeon.getCurrRoom()).cannotLose = false;


        if (!(AbstractDungeon.getCurrRoom()).cannotLose) {
            useFastShakeAnimation(5.0F);
            CardCrawlGame.screenShake.rumble(4.0F);

            super.die();

            AbstractDungeon.scene.fadeInAmbiance();
            CardCrawlGame.music.fadeOutTempBGM();
            onBossVictoryLogic();
        } else {
            ThePien p = new ThePien(this.times + 1);
            addToBot(new SpawnMonsterAction(p, true, 1));
//            addToBot(new RollMoveAction(p));
            addToBot(new DelayCreateIntentAction(p));
            addToBot(new DelayDieAction(this));
        }
    }

    public void damage(DamageInfo info) {
        super.damage(info);
    }
}

