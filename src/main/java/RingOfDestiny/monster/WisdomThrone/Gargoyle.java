package RingOfDestiny.monster.WisdomThrone;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.unique.ChangeStageAction;
import RingOfDestiny.actions.unique.CustomWaitAction;
import RingOfDestiny.actions.unique.UpgardeStateAction;
import RingOfDestiny.powers.Monster.WisdomThrone.CryingBloodPower;
import RingOfDestiny.powers.Monster.WisdomThrone.Petrifaction;
import RingOfDestiny.powers.Monster.WisdomThrone.StoneSkinPower;
import RingOfDestiny.vfx.combat.*;
import basemod.abstracts.CustomMonster;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
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


public class Gargoyle extends CustomMonster {
    public static final String ID = RingOfDestiny.makeID("Gargoyle");
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;
    private boolean isStone = false;

    public Gargoyle() {
        super(NAME, ID, 88, 0.0F, -15.0F, 320.0F, 380.0F, null, 0.0F, -10.0F);

        if (AbstractDungeon.ascensionLevel >= 7) {
            setHp(70);
        } else {
            setHp(70);
        }


        if (AbstractDungeon.ascensionLevel >= 2) {
            this.damage.add(new DamageInfo(this, 3));
        } else {
            this.damage.add(new DamageInfo(this, 3));
        }


        this.type = EnemyType.NORMAL;
        this.dialogX = -50.0F * Settings.scale;
        this.dialogY = 50.0F * Settings.scale;


        loadAnimation("RingOfDestiny/monsters/WisdomThrone/Gargoyle/Gargoyle.atlas", "RingOfDestiny/monsters/WisdomThrone/Gargoyle/Gargoyle.json", 1.3F);


        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.flipHorizontal = false;
    }


    public void usePreBattleAction() {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("fight");

        addToBot(new ApplyPowerAction(this, this, new StoneSkinPower(this)));
        addToBot(new ApplyPowerAction(this, this, new Petrifaction(this)));
    }


    public void takeTurn() {

        switch (this.nextMove) {
            case 0:
                addToBot(new ChangeStateAction(this, "Attack"));
                addToBot(new CustomWaitAction(0.5f));
                for (int i = 0; i < 3; i++)
                    addToBot(new DamageAction(AbstractDungeon.player, (DamageInfo) this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HEAVY, true));

                addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new PoisonPower(AbstractDungeon.player, this, 3), 3));
                break;
            case 1:
                addToBot(new HealAction(this, this, this.maxHealth));
                addToBot(new ChangeStateAction(this, "Back"));
                AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsAction(this));
                this.isStone = false;
                break;
        }

        addToBot(new RollMoveAction(this));

    }


    protected void getMove(int num) {
        setMove((byte) 0, Intent.ATTACK_DEBUFF, ((DamageInfo) this.damage.get(0)).base, 3, true);
    }


    public void die() {
        if (!(AbstractDungeon.getCurrRoom()).cannotLose && isStone) {
            super.die();
            AbstractDungeon.scene.fadeInAmbiance();
            CardCrawlGame.music.fadeOutTempBGM();
        }
    }

    public void damage(DamageInfo info) {
        super.damage(info);

        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0 && currentHealth > 0) {
            this.state.setAnimation(0, "Hit", false);
            this.state.addAnimation(0, "Idle", true, 0.0F);
        }



        (AbstractDungeon.getCurrRoom()).cannotLose = false;

        if (this.currentHealth <= 0) {
            if (!isStone) {
                this.state.setAnimation(0, "Change", false);
                addToBot(new CustomWaitAction(0.5f));
                addToBot(new ChangeStateAction(this, "Stone"));
                this.isStone = true;
                addToBot(new HealAction(this, this, 4));
                (AbstractDungeon.getCurrRoom()).cannotLose = true;
                addToBot(new ApplyPowerAction(this, this, new IntangiblePlayerPower(this, 1),1));
                setMove((byte) 1, Intent.UNKNOWN);
                createIntent();
            }
        }
    }

    public void changeState(String stateName) {
        AnimationState.TrackEntry e;

        switch (stateName) {
            case "Attack":
                this.state.setAnimation(0, "Attack", false);
                this.state.addAnimation(0, "Idle", true, 0.0F);
                break;

            case "Stone":
                loadAnimation("RingOfDestiny/monsters/WisdomThrone/Gargoyle/Gargoyle_Stone.atlas", "RingOfDestiny/monsters/WisdomThrone/Gargoyle/Gargoyle_Stone.json", 1.3F);
                e = this.state.setAnimation(0, "Idle", true);
                e.setTime(e.getEndTime() * MathUtils.random());

                updateHitbox(0.0F, -15.0F, 320.0F, 320.0F);
                break;

            case "Back":
                loadAnimation("RingOfDestiny/monsters/WisdomThrone/Gargoyle/Gargoyle.atlas", "RingOfDestiny/monsters/WisdomThrone/Gargoyle/Gargoyle.json", 1.3F);
                e = this.state.setAnimation(0, "Idle", true);
                e.setTime(e.getEndTime() * MathUtils.random());
                updateHitbox(0.0F, -15.0F, 320.0F, 380.0F);
                break;
        }
    }
}

