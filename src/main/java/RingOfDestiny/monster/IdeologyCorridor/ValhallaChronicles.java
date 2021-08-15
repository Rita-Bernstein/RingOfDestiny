package RingOfDestiny.monster.IdeologyCorridor;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.unique.*;
import RingOfDestiny.monster.AbstractRingMonster;
import RingOfDestiny.vfx.combat.*;
import basemod.abstracts.CustomMonster;

import com.esotericsoftware.spine.Bone;
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


public class ValhallaChronicles extends AbstractRingMonster {
    public static final String ID = RingOfDestiny.makeID("ValhallaChronicles");
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;

    public ArrayList<ValhallaOrb> orbs = new ArrayList<ValhallaOrb>();
    public ArrayList<Bone> orbBones = new ArrayList<Bone>();

    private int moveCount = 0;
    private boolean intendChanged = false;
    public float animationScale = 1.25f;


    public ValhallaChronicles() {
        super(NAME, ID, 88, 0.0F, -15.0F, 360.0F, 450.0F, null, 0.0F, -10.0F);

        if (AbstractDungeon.ascensionLevel >= 9) {
            setHp(250);
        } else {
            setHp(250);
        }


        if (AbstractDungeon.ascensionLevel >= 4) {
            this.damage.add(new DamageInfo(this, 6));
            this.damage.add(new DamageInfo(this, 4));
        } else {
            this.damage.add(new DamageInfo(this, 6));
            this.damage.add(new DamageInfo(this, 4));
        }


        this.type = EnemyType.BOSS;
        this.dialogX = -50.0F * Settings.scale;
        this.dialogY = 50.0F * Settings.scale;


        loadAnimation("RingOfDestiny/monsters/IdeologyCorridor/ValhallaChronicles/ValhallaChronicles.atlas", "RingOfDestiny/monsters/IdeologyCorridor/ValhallaChronicles/ValhallaChronicles.json", animationScale);


        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.flipHorizontal = false;

        for (int i = 0; i < 6; i++) {
            ValhallaOrb ob = new ValhallaOrb(i + 1);
            ob.active = true;
            orbs.add(ob);
            orbBones.add(this.skeleton.findBone("q_" + i + 1));
        }


    }


    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        super.onUseCard(card, action);
        int count = 0;
        if (card.type == AbstractCard.CardType.ATTACK) {
            for (ValhallaOrb orb : this.orbs) {
                if (!orb.active) {
                    orb.active = true;
                    break;
                }
            }
        }

        for (ValhallaOrb orb : this.orbs) {
            if (orb.active) {
                count++;
            }
        }

        if (count >= 6 && !intendChanged && card.type == AbstractCard.CardType.ATTACK) {
            setMove((byte) 0, Intent.ATTACK_DEBUFF, this.damage.get(0).base);
            createIntent();
            this.intendChanged = true;
        }
    }

    public void usePreBattleAction() {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("fight_boss");
    }


    public void takeTurn() {
        AbstractCard c = new Burn();
        c.upgrade();


        switch (this.nextMove) {
            case 0:
                if (!orbs.isEmpty()){
                    for (ValhallaOrb orb : orbs) {
                        orb.evoke();
                    }
                }
                addToBot(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.FIRE, true));
                addToBot(new MakeTempCardInDiscardAction(c, 3));
                this.intendChanged = false;
                break;

            case 1:
                addToBot(new DamageAction(AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.FIRE, true));
                addToBot(new DamageAction(AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.FIRE, true));
                addToBot(new MakeTempCardInDiscardAction(c, 2));
                break;

            case 2:
                addToBot(new GainBlockAction(this, this, 10));
                break;
            case 3:
                addToBot(new GainBlockAction(this, this, 6));
                addToBot(new MakeTempCardInDiscardAction(c, 2));
                break;
        }

        addToBot(new RollMoveAction(this));

    }


    protected void getMove(int num) {
        int count = 0;
        for (ValhallaOrb orb : this.orbs) {
            if (orb.active) {
                count++;
            }
        }

        if (count >= 6 && !intendChanged) {
            setMove((byte) 0, Intent.ATTACK_DEBUFF, this.damage.get(0).base);
            return;
        }


        switch (this.moveCount % 3) {
            case 0:
                setMove((byte) 1, Intent.ATTACK_DEBUFF, this.damage.get(1).base, 2, true);
                break;

            case 1:

                setMove((byte) 2, Intent.DEFEND);
                break;


            default:
                setMove((byte) 3, Intent.DEFEND_DEBUFF);
                break;
        }

        this.moveCount++;
    }


    public void die() {
        super.die();

        if (!orbs.isEmpty()){
            for (ValhallaOrb orb : orbs) {
                orb.hide();
            }
        }

        if(AbstractDungeon.getMonsters().areMonstersBasicallyDead()){
            AbstractDungeon.scene.fadeInAmbiance();
            CardCrawlGame.music.fadeOutTempBGM();
            this.useFastShakeAnimation(5.0F);
            CardCrawlGame.screenShake.rumble(4.0F);
            onBossVictoryLogic();
        }


    }

    public void damage(DamageInfo info) {
        super.damage(info);
    }

    @Override
    public void update() {
        super.update();
        if (!orbs.isEmpty()) {
            for (ValhallaOrb orb : orbs) {
                orb.update();
                orb.x = this.drawX + this.animX + this.skeleton.findBone("q_" + orb.index).getWorldX();
                orb.y = this.drawY + this.animY + this.skeleton.findBone("q_" + orb.index).getWorldY();
                orb.scale = this.animationScale / 0.75f;
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        if (!orbs.isEmpty()) {
            for (ValhallaOrb orb : orbs) {
                orb.render(sb);
            }
        }

    }
}

