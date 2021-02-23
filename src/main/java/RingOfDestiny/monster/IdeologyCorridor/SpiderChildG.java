package RingOfDestiny.monster.IdeologyCorridor;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.unique.ChangeStageAction;
import RingOfDestiny.actions.unique.CustomWaitAction;
import RingOfDestiny.actions.unique.UpgardeStateAction;
import RingOfDestiny.powers.Monster.IdeologyCorridor.EyeForEyePower;
import RingOfDestiny.powers.Monster.IdeologyCorridor.VenomPower;
import RingOfDestiny.vfx.combat.*;
import basemod.abstracts.CustomMonster;

import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
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


public class SpiderChildG extends CustomMonster {
    public static final String ID = RingOfDestiny.makeID("SpiderChildG");
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;


    public SpiderChildG() {
        this(0.0f, -10.0f);
    }

    public SpiderChildG(float x, float y) {
        super(NAME, ID, 88, 0.0F, 0.0F, 120.0F, 100.0F, null, x, y);

        if (AbstractDungeon.ascensionLevel >= 7) {
            setHp(18, 25);
        } else {
            setHp(18, 25);
        }


        if (AbstractDungeon.ascensionLevel >= 2) {
            this.damage.add(new DamageInfo(this, 8));
        } else {
            this.damage.add(new DamageInfo(this, 8));
        }


        this.type = EnemyType.NORMAL;
        this.dialogX = -50.0F * Settings.scale;
        this.dialogY = 50.0F * Settings.scale;


        loadAnimation("RingOfDestiny/monsters/IdeologyCorridor/SpiderChildG/SpiderChildG.atlas", "RingOfDestiny/monsters/IdeologyCorridor/SpiderChildG/SpiderChildG.json", 2.0F);


        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.flipHorizontal = false;
    }

    @Override
    public void usePreBattleAction() {
        addToBot(new ApplyPowerAction(this, this, new VenomPower(this), 1));
    }


    public void takeTurn() {

        switch (this.nextMove) {
            case 0:
                addToBot(new AnimateSlowAttackAction(this));
                addToBot(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.SLASH_HEAVY, true));
                setMove((byte) 1, AbstractMonster.Intent.DEBUFF);
                break;

            case 1:
                addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new WeakPower(AbstractDungeon.player, 1, true), 1));
                addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new FrailPower(AbstractDungeon.player, 1, true), 1));
                setMove((byte) 0, AbstractMonster.Intent.ATTACK, this.damage.get(0).base);
                break;
        }

        addToBot(new RollMoveAction(this));

    }


    protected void getMove(int num) {

        if (AbstractDungeon.ascensionLevel >= 17) {
            if (lastTwoMoves((byte) 0)) {
                setMove((byte) 0, AbstractMonster.Intent.ATTACK, this.damage.get(0).base);
            } else {
                setMove((byte) 1, AbstractMonster.Intent.DEBUFF);
            }

        } else if (AbstractDungeon.aiRng.randomBoolean()) {
            setMove((byte) 0, AbstractMonster.Intent.ATTACK, this.damage.get(0).base);
        } else {
            setMove((byte) 1, AbstractMonster.Intent.DEBUFF);
        }
    }


    public void die() {
        super.die();


        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                if (!monster.isDead && !monster.isDying) {
                    if (monster instanceof SpiderChildG) {
                        return;
                    }
                }
            }
        }


        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                if (!monster.isDead && !monster.isDying) {
                    if (monster instanceof SpiderQueen) {
                        addToBot(new ChangeStateAction(monster, "Back"));
                    }
                }
            }
        }
    }
}

