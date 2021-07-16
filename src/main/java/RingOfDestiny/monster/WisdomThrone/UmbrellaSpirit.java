package RingOfDestiny.monster.WisdomThrone;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.unique.ChangeStageAction;
import RingOfDestiny.actions.unique.CustomWaitAction;
import RingOfDestiny.actions.unique.UpgardeStateAction;
import RingOfDestiny.powers.Monster.WisdomThrone.AgainstWindPower;
import RingOfDestiny.powers.Monster.WisdomThrone.KeepOffRainPower;
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


public class UmbrellaSpirit extends CustomMonster {
    public static final String ID = RingOfDestiny.makeID("UmbrellaSpirit");
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;

    private boolean isSpirit = false;
    private int moveCount = 0;

    public UmbrellaSpirit() {
        super(NAME, ID, 88, 0.0F, -15.0F, 360.0F, 400.0F, null, 0.0F, -10.0F);

        if (AbstractDungeon.ascensionLevel >= 7) {
            setHp(150);
        } else {
            setHp(150);
        }


        if (AbstractDungeon.ascensionLevel >= 2) {
            this.damage.add(new DamageInfo(this, 20));
        } else {
            this.damage.add(new DamageInfo(this, 20));
        }


        this.type = EnemyType.ELITE;
        this.dialogX = -50.0F * Settings.scale;
        this.dialogY = 50.0F * Settings.scale;


        loadAnimation("RingOfDestiny/monsters/WisdomThrone/UmbrellaSpirit/UmbrellaSpirit.atlas", "RingOfDestiny/monsters/WisdomThrone/UmbrellaSpirit/UmbrellaSpirit.json", 2.6F);


        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.flipHorizontal = false;

    }


    public void usePreBattleAction() {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("fight_elite");
        addToBot(new ApplyPowerAction(this, this, new AgainstWindPower(this, 3)));
        addToBot(new ApplyPowerAction(this, this, new KeepOffRainPower(this, 4)));
    }


    public void takeTurn() {

        switch (this.nextMove) {
            case 0:
                addToBot(new DamageAction(AbstractDungeon.player, (DamageInfo) this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HEAVY, true));

                break;
            case 1:
                addToBot(new MakeTempCardInDiscardAction(new Slimed(), 3));
        }
        moveCount++;
        addToBot(new RollMoveAction(this));

    }


    protected void getMove(int num) {
        switch (moveCount % 2) {
            case 0:
                setMove((byte) 0, Intent.ATTACK, this.damage.get(0).base);
                break;
            case 1:
                setMove((byte) 1, Intent.DEBUFF);
                break;
        }
    }

    @Override
    public void update() {
        super.update();
        if(this.hasPower(IntangiblePlayerPower.POWER_ID) && !isSpirit){
            this.state.setAnimation(0, "Idle2", true);
            isSpirit = true;
        }

        if(!this.hasPower(IntangiblePlayerPower.POWER_ID) && isSpirit){
            loadAnimation("RingOfDestiny/monsters/WisdomThrone/UmbrellaSpirit/UmbrellaSpirit.atlas", "RingOfDestiny/monsters/WisdomThrone/UmbrellaSpirit/UmbrellaSpirit.json", 2.6F);
            AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
            e.setTime(e.getEndTime() * MathUtils.random());
            isSpirit = false;
        }
    }

    public void die() {
        super.die();
        if(AbstractDungeon.getMonsters().areMonstersBasicallyDead()){
            AbstractDungeon.scene.fadeInAmbiance();
            CardCrawlGame.music.fadeOutTempBGM();
        }
    }

    public void damage(DamageInfo info) {
        super.damage(info);

        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0 && currentHealth > 0) {
            if (isSpirit) {
                this.state.setAnimation(0, "Hit2", false);
                this.state.addAnimation(0, "Idle2", true, 0.0F);
            } else {
                this.state.setAnimation(0, "Hit", false);
                this.state.addAnimation(0, "Idle", true, 0.0F);
            }

        }
    }
}

