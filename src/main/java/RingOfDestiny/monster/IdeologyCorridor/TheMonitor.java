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
import com.megacrit.cardcrawl.characters.AbstractPlayer;
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


public class TheMonitor extends CustomMonster {
    public static final String ID = RingOfDestiny.makeID("TheMonitor");
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;


    private boolean isFirstMove = true;
    private boolean talky = true;
    private boolean spawned = false;

    private int vulAmount = 99;


    public TheMonitor() {
        super(NAME, ID, 88, 0.0F, -35.0F, 360.0F, 360.0F, null, 0.0F, -10.0F);

        if (AbstractDungeon.ascensionLevel >= 8) {
            setHp(80);
        } else {
            setHp(80);
        }


        if (AbstractDungeon.ascensionLevel >= 3) {
            this.damage.add(new DamageInfo(this, 9));
        } else {
            this.damage.add(new DamageInfo(this, 8));
        }


        this.type = AbstractMonster.EnemyType.ELITE;
        this.dialogX = -50.0F * Settings.scale;
        this.dialogY = 50.0F * Settings.scale;


        loadAnimation("RingOfDestiny/monsters/IdeologyCorridor/TheMonitor/TheMonitor.atlas", "RingOfDestiny/monsters/IdeologyCorridor/TheMonitor/TheMonitor.json", 1.25F);


        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.flipHorizontal = false;

    }

    public TheMonitor(float x, float y) {
        super(NAME, ID, 88, 0.0F, -15.0F, 300.0F, 300.0F, null, x, y);

        setHp(20);

        if (AbstractDungeon.ascensionLevel >= 4) {
            this.damage.add(new DamageInfo(this, 7));
        } else {
            this.damage.add(new DamageInfo(this, 6));
        }

        this.type = AbstractMonster.EnemyType.ELITE;
        this.dialogX = -50.0F * Settings.scale;
        this.dialogY = 50.0F * Settings.scale;

        loadAnimation("RingOfDestiny/monsters/IdeologyCorridor/TheMonitor/TheMonitor.atlas", "RingOfDestiny/monsters/IdeologyCorridor/TheMonitor/TheMonitor.json", 1.25F);


        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.flipHorizontal = false;

        this.isFirstMove = false;
        this.talky = false;
    }

    public void usePreBattleAction() {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("fight_elite");
    }


    public void takeTurn() {
        switch (this.nextMove) {
            case 0:
                addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new VulnerablePower(AbstractDungeon.player, vulAmount, true), vulAmount));

                break;

            case 1:
                addToBot(new ChangeStateAction(this, "Attack"));
                addToBot(new CustomWaitAction(0.35f));
                addToBot(new DamageAction(AbstractDungeon.player, (DamageInfo) this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                break;

            case 2:

                addToBot(new SpawnMonsterAction(new TheMonitor(-300.0F, 0.0F), false,0));
                addToBot(new SpawnMonsterAction(new TheMonitor(300.0F, 0.0F), false,2));

                break;
        }

        addToBot(new RollMoveAction(this));

    }


    protected void getMove(int num) {
        if (this.isFirstMove) {
            setMove((byte) 0, Intent.STRONG_DEBUFF);
            this.isFirstMove = false;
            return;
        }

        setMove((byte) 1, Intent.ATTACK, ((DamageInfo) this.damage.get(0)).base);


    }


    public void die() {
        super.die();
//        AbstractDungeon.scene.fadeInAmbiance();
//        CardCrawlGame.music.fadeOutTempBGM();

        this.deathTimer += 1.5f;
        this.state.setAnimation(0, "Corpse", false);
    }

    public void damage(DamageInfo info) {
        super.damage(info);

        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0 && currentHealth > 0)  {

            this.state.setAnimation(0, "Hit", false);
            this.state.addAnimation(0, "Idle", true, 0.0F);

        }

        if (this.currentHealth <= 20 && !this.spawned) {
            if(this.talky){
                this.talky = false;
                addToBot(new TalkAction(this, DIALOG[0], 1.0F, 2.0F));
            }

            setMove((byte) 2, AbstractMonster.Intent.UNKNOWN);
            createIntent();
            this.spawned = true;
        }

    }

    public void changeState(String stateName) {
        switch (stateName) {
            case "Attack":
                this.state.setAnimation(0, "Attack", false);
                this.state.addAnimation(0, "Idle", true, 0.0F);
                break;
        }
    }


}

