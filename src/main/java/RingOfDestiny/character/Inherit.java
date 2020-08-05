package RingOfDestiny.character;


import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.Inherit.*;
import RingOfDestiny.modules.EnergyOrbCustomBlue;
import RingOfDestiny.modules.EnergyOrbInherit;
import RingOfDestiny.modules.EnergyOrbSummoner;
import RingOfDestiny.patches.*;
import RingOfDestiny.relics.DogEyes;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.BurningBlood;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import java.util.ArrayList;

import static RingOfDestiny.RingOfDestiny.Inherit_Color;

public class Inherit extends CustomPlayer {
    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString(RingOfDestiny.makeID("Inherit"));

    public static final int ENERGY_PER_TURN = 3;
    public static final int START_HP = 90;
    public static final int START_GOLD = 99;
    public static boolean firstAttackAnimation = true;


    public static final String[] orbTextures = {
            "RingOfDestiny/img/ui/topPanel/Inherit/1.png",//4
            "RingOfDestiny/img/ui/topPanel/Inherit/2.png",//2
            "RingOfDestiny/img/ui/topPanel/Inherit/3.png",//3
            "RingOfDestiny/img/ui/topPanel/Inherit/4.png",//5
            "RingOfDestiny/img/ui/topPanel/Inherit/5.png",//1
            "RingOfDestiny/img/ui/topPanel/Inherit/border.png",
            "RingOfDestiny/img/ui/topPanel/Inherit/1d.png",//4
            "RingOfDestiny/img/ui/topPanel/Inherit/2d.png",//2
            "RingOfDestiny/img/ui/topPanel/Inherit/3d.png",//3
            "RingOfDestiny/img/ui/topPanel/Inherit/4d.png",//5
            "RingOfDestiny/img/ui/topPanel/Inherit/5d.png",//1
    };

    public Inherit(String name, PlayerClass setClass) {
        super(name, setClass, new EnergyOrbInherit(orbTextures, "RingOfDestiny/img/ui/topPanel/Inherit/energyVFX.png"), (String) null, null);
        this.drawX += 5.0F * Settings.scale;
        this.drawY += 7.0F * Settings.scale;

        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 170.0F * Settings.scale;

        initializeClass(null,
                "RingOfDestiny/characters/Inherit/shoulder2.png",
                "RingOfDestiny/characters/Inherit/shoulder.png",
                "RingOfDestiny/characters/Inherit/corpse.png",
                getLoadout(), 0.0F, -5.0F, 240.0F, 320.0F, new EnergyManager(ENERGY_PER_TURN));

        loadAnimation(RingOfDestiny.assetPath("characters/Inherit/animation/hero_00501.atlas"), RingOfDestiny.assetPath("characters/Inherit/animation/hero_00501.json"), 1.4f);

        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        this.stateData.setMix("Hit", "Idle", 0.1F);
        e.setTime(e.getEndTime() * MathUtils.random());
    }


    public String getPortraitImageName() {
        return null;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(BurningBlood.ID);
        return retVal;
    }

    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Strike_IH.ID);
        retVal.add(Strike_IH.ID);

        retVal.add(Defend_IH.ID);
        retVal.add(Defend_IH.ID);


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
    public ArrayList<AbstractCard> getCardPool(ArrayList<AbstractCard> tmpPool) {

        if (ModHelper.isModEnabled("Red Cards")) {
            CardLibrary.addRedCards(tmpPool);
        }
        if (ModHelper.isModEnabled("Green Cards")) {
            CardLibrary.addGreenCards(tmpPool);
        }

        if (ModHelper.isModEnabled("Blue Cards")) {
            CardLibrary.addBlueCards(tmpPool);
        }

        if (ModHelper.isModEnabled("Purple Cards")) {
            CardLibrary.addPurpleCards(tmpPool);
        }

        return super.getCardPool(tmpPool);
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return CardColorEnum.Inherit_LIME;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Strike_IH();
    }

    @Override
    public Color getCardTrailColor() {
        return Inherit_Color.cpy();
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
        return new Inherit(this.name, AbstractPlayerEnum.Inherit);
    }

    @Override
    public String getSpireHeartText() {
        return CardCrawlGame.languagePack.getEventString(RingOfDestiny.makeID("SpireHeart_Inherit")).DESCRIPTIONS[0];
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
            if (firstAttackAnimation) {
                AbstractDungeon.player.state.setAnimation(0, "gongji_1", false);
            } else {
                AbstractDungeon.player.state.setAnimation(0, "gongji_2", true);
            }
            firstAttackAnimation = !firstAttackAnimation;
            AbstractDungeon.player.state.addAnimation(0, "Idle", true, 0.0F);
        }
        if (c.type == AbstractCard.CardType.SKILL) {
            AbstractDungeon.player.state.setAnimation(0, "fashu", false);
            AbstractDungeon.player.state.addAnimation(0, "Idle", true, 0.0F);
        }
        if (c.type == AbstractCard.CardType.POWER) {
            AbstractDungeon.player.state.setAnimation(0, "zhuangbei", false);
            AbstractDungeon.player.state.addAnimation(0, "Idle", true, 0.0F);
        }
    }
}

