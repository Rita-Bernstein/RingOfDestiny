package RingOfDestiny.monster.KnowledgeHall;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.monster.SoulEaterAction;
import RingOfDestiny.actions.unique.*;
import RingOfDestiny.monster.AbstractRingMonster;
import RingOfDestiny.powers.Monster.IdeologyCorridor.ResurrectionPower;
import RingOfDestiny.powers.Monster.KnowledgeHall.FulPower;
import RingOfDestiny.powers.Monster.KnowledgeHall.SoulEaterPower;
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


public class Sledgehammer extends AbstractRingMonster {
    public static final String ID = RingOfDestiny.makeID("Sledgehammer");
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;

    private int moveCount = 1;


    public Sledgehammer() {
        super(NAME, ID, 88, 0.0F, -25.0F, 360.0F, 320.0F, null, 0.0F, -10.0F);

        if (AbstractDungeon.ascensionLevel >= 9) {
            setHp(20);
        } else {
            setHp(20);
        }


        if (AbstractDungeon.ascensionLevel >= 4) {
            this.damage.add(new DamageInfo(this, 3));
        } else {
            this.damage.add(new DamageInfo(this, 2));
        }


        this.type = EnemyType.ELITE;
        this.dialogX = -50.0F * Settings.scale;
        this.dialogY = 50.0F * Settings.scale;

        loadAnimation("RingOfDestiny/monsters/KnowledgeHall/Sledgehammer/Sledgehammer.atlas", "RingOfDestiny/monsters/KnowledgeHall/Sledgehammer/Sledgehammer.json", 1.6f);


        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.flipHorizontal = false;

    }


    public void usePreBattleAction() {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("fight_elite");

        addToBot(new ApplyPowerAction(this, this, new IntangiblePlayerPower(this, 99)));
    }


    public void takeTurn() {
        addToBot(new ChangeStateAction(this, "Attack"));
        addToBot(new CustomWaitAction(0.35f));
        for (int i = 0; i < this.moveCount -1; i++)
            addToBot(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY, true));

        addToBot(new RollMoveAction(this));

    }


    protected void getMove(int num) {
        setMove((byte) 0, Intent.ATTACK, this.damage.get(0).base, this.moveCount, true);
        this.moveCount++;
    }

    public void die() {
        super.die();

        this.deathTimer += 1.5f;
        this.state.setAnimation(0, "Corpse", false);
    }

    public void damage(DamageInfo info) {
        super.damage(info);

        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0 && currentHealth > 0) {

            this.state.setAnimation(0, "Hit", false);
            this.state.addAnimation(0, "Idle", true, 0.0F);

        }
    }


    public void changeState(String stateName) {
        switch (stateName) {
            case "Attack":
                this.state.setAnimation(0, "Attack3", false);
                this.state.addAnimation(0, "Idle", true, 0.0F);
                break;
        }
    }

}

