package RingOfDestiny.character;


import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.MagicBullet.*;
import RingOfDestiny.modules.EnergyOrbCustomBlue;
import RingOfDestiny.patches.*;
import RingOfDestiny.relics.ShadowKunai;
import basemod.abstracts.CustomPlayer;
import basemod.interfaces.OnCardUseSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.*;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbBlue;

import java.util.ArrayList;

public class MagicBullet extends CustomPlayer {
    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString(RingOfDestiny.makeID("MagicBullet"));

    public static final int ENERGY_PER_TURN = 3;
    public static final int START_HP = 98;
    public static final int START_GOLD = 99;


    public static final String[] orbTextures = {
            "images/ui/topPanel/blue/1.png",//4
            "images/ui/topPanel/blue/2.png",//2
            "images/ui/topPanel/blue/3.png",//3
            "images/ui/topPanel/blue/4.png",//5
            "images/ui/topPanel/blue/5.png",//1
            "images/ui/topPanel/blue/border.png",
            "images/ui/topPanel/blue/1d.png",
            "images/ui/topPanel/blue/2d.png",
            "images/ui/topPanel/blue/3d.png",
            "images/ui/topPanel/blue/4d.png",
            "images/ui/topPanel/blue/5d.png"
    };

    public MagicBullet(String name, PlayerClass setClass) {
        super(name, setClass, new EnergyOrbCustomBlue(orbTextures, "images/ui/topPanel/energyBlueVFX.png"), (String) null, null);
        this.drawX += 5.0F * Settings.scale;
        this.drawY += 7.0F * Settings.scale;

        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 170.0F * Settings.scale;

        initializeClass(null,
                "RingOfDestiny/characters/MagicBullet/shoulder2.png",
                "RingOfDestiny/characters/MagicBullet/shoulder.png",
                "RingOfDestiny/characters/MagicBullet/corpse.png",
                getLoadout(), 0.0F, -5.0F, 240.0F, 320.0F, new EnergyManager(ENERGY_PER_TURN));

        loadAnimation(RingOfDestiny.assetPath("characters/MagicBullet/animation/hero_002.atlas"), RingOfDestiny.assetPath("characters/MagicBullet/animation/hero_002.json"), 1.6f);

        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        this.stateData.setMix("Hit", "Idle", 0.1F);
        e.setTime(e.getEndTime() * MathUtils.random());
    }


    public String getPortraitImageName() {
        return null;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(ShadowKunai.ID);
        return retVal;
    }

    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Defend_MB.ID);

        retVal.add(Strike_MB.ID);
        retVal.add(Strike_MB.ID);
        retVal.add(Strike_MB.ID);

        retVal.add(DeathLeakage.ID);
        retVal.add(DeathLeakage.ID);

        return retVal;
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(
                getLocalizedCharacterName(),
                charStrings.TEXT[0],
                START_HP,
                START_HP,
                0,
                START_GOLD,
                5,
                this,
                getStartingRelics(),
                getStartingDeck(),
                false);
    }


    @Override
    public String getTitle(PlayerClass playerClass) {
        return charStrings.NAMES[1];
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return CardColorEnum.MagicBullet_LIME;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new DeathLeakage();
    }

    @Override
    public Color getCardTrailColor() {
        return Color.SKY.cpy();
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 9;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_DAGGER_1", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_DAGGER_1";
    }

    @Override
    public String getLocalizedCharacterName() {
        return charStrings.NAMES[0];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new MagicBullet(this.name, AbstractPlayerEnum.MagicBullet);
    }

    @Override
    public String getSpireHeartText() {
        return CardCrawlGame.languagePack.getEventString("RingOfDestiny:SpireHeart_MagicBullet").DESCRIPTIONS[0];
    }


    @Override
    public Color getSlashAttackColor() {
        return Color.SKY;
    }


    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[1];
    }

    @Override
    public Color getCardRenderColor() {
        return Color.SKY;
    }


    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL
        };
    }

    public void damage(DamageInfo info) {
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output - this.currentBlock > 0) {
            AnimationState.TrackEntry e = this.state.setAnimation(0, "Hit", false);
            this.state.addAnimation(0, "Idle", true, 0.0F);
            e.setTimeScale(1.0F);
        }

        super.damage(info);
    }


    @Override
    public void useCard(AbstractCard c, AbstractMonster monster, int energyOnUse) {
        super.useCard(c, monster, energyOnUse);
        if (c.type == AbstractCard.CardType.ATTACK) {
            AbstractDungeon.player.state.setAnimation(0, "gongji_1", true);
            AbstractDungeon.player.state.addAnimation(0, "Idle", true, 0.0F);
        }
        if (c.type == AbstractCard.CardType.SKILL) {
            AbstractDungeon.player.state.setAnimation(0, "fashu", true);
            AbstractDungeon.player.state.addAnimation(0, "Idle", true, 0.0F);
        }
        if (c.type == AbstractCard.CardType.POWER) {
            AbstractDungeon.player.state.setAnimation(0, "zhuangbei", true);
            AbstractDungeon.player.state.addAnimation(0, "Idle", true, 0.0F);
        }
    }
}

