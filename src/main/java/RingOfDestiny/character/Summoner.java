package RingOfDestiny.character;


import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.ShadowFlower.Strike_SF;
import RingOfDestiny.cards.Summoner.*;
import RingOfDestiny.modules.EnergyOrbCustomBlue;
import RingOfDestiny.modules.EnergyOrbSummoner;
import RingOfDestiny.patches.*;
import RingOfDestiny.relics.DemonicContract;
import RingOfDestiny.relics.DogEyes;
import RingOfDestiny.relics.ShadowKunai;
import RingOfDestiny.skinCharacters.AbstractSkinCharacter;
import RingOfDestiny.ui.SoulStoneTutorial;
import basemod.abstracts.CustomPlayer;
import basemod.interfaces.OnCardUseSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
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
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.BurningBlood;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbBlue;

import java.util.ArrayList;

import static RingOfDestiny.RingOfDestiny.Summoner_Color;

public class Summoner extends AbstractRingCharacter {
    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString(RingOfDestiny.makeID("Summoner"));

    public static final int ENERGY_PER_TURN = 3;
    public static final int START_HP = 88;
    public static final int START_GOLD = 99;
    public static boolean firstAttackAnimation = true;

    public static final String[] orbTextures = {
            "RingOfDestiny/img/ui/topPanel/Summoner/1.png",//4
            "RingOfDestiny/img/ui/topPanel/Summoner/2.png",//2
            "RingOfDestiny/img/ui/topPanel/Summoner/3.png",//3
            "RingOfDestiny/img/ui/topPanel/Summoner/4.png",//5
            "RingOfDestiny/img/ui/topPanel/Summoner/5.png",//1
            "RingOfDestiny/img/ui/topPanel/Summoner/border.png",
            "RingOfDestiny/img/ui/topPanel/Summoner/1d.png",//4
            "RingOfDestiny/img/ui/topPanel/Summoner/2d.png",//2
            "RingOfDestiny/img/ui/topPanel/Summoner/3d.png",//3
            "RingOfDestiny/img/ui/topPanel/Summoner/4d.png",//5
            "RingOfDestiny/img/ui/topPanel/Summoner/5d.png",//1
    };

    public Summoner(String name, PlayerClass setClass) {
        super(name, setClass, new EnergyOrbSummoner(orbTextures, "RingOfDestiny/img/ui/topPanel/Summoner/energyVFX.png"), (String) null, null);
        this.drawX += 5.0F * Settings.scale;
        this.drawY += 7.0F * Settings.scale;

        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 170.0F * Settings.scale;

        initializeClass(null,
                "RingOfDestiny/characters/Summoner/shoulder2.png",
                "RingOfDestiny/characters/Summoner/shoulder.png",
                "RingOfDestiny/characters/Summoner/corpse.png",
                getLoadout(), 0.0F, -5.0F, 240.0F, 320.0F, new EnergyManager(ENERGY_PER_TURN));

        AbstractSkinCharacter character = CharacterSelectScreenPatches.characters[4];
        this.loadAnimation(
                character.skins[character.reskinCount].atlasURL,
                character.skins[character.reskinCount].jsonURL,
                character.skins[character.reskinCount].renderscale
        );
//        loadAnimation(RingOfDestiny.assetPath("characters/Summoner/animation/Summoner.atlas"), RingOfDestiny.assetPath("characters/Summoner/animation/Summoner.json"), 1.6f);

        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        this.stateData.setMix("Hit", "Idle", 0.1F);
        e.setTime(e.getEndTime() * MathUtils.random());

        this.shadowScale = 1.2f;
    }

    @Override
    public void preBattlePrep() {
        super.preBattlePrep();
//        if (!RingOfDestiny.neverSeeSoulStoneTutorial) {
//            AbstractDungeon.ftue = new SoulStoneTutorial();
//            RingOfDestiny.neverSeeSoulStoneTutorial = true;
//            RingOfDestiny.neverSeeSoulStoneTutorialSwitch.toggle.enabled = true;
//            RingOfDestiny.saveSettings();
//        }
    }

    public String getPortraitImageName() {
        return null;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(DemonicContract.ID);
        return retVal;
    }

    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Defend_SU.ID);
        retVal.add(Defend_SU.ID);
        retVal.add(Defend_SU.ID);

        retVal.add(Strike_SU.ID);
        retVal.add(Strike_SU.ID);
        retVal.add(Strike_SU.ID);

        retVal.add(MaliciousBarrier.ID);
        retVal.add(DoomsdayStrike.ID);
        retVal.add(PowerOfGreed.ID);
        retVal.add(PowerOfEnvy.ID);


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
        return CardColorEnum.Summoner_LIME;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Strike_SU();
    }

    @Override
    public Color getCardTrailColor() {
        return Summoner_Color.cpy();
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 8;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontPurple;
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
        return new Summoner(this.name, AbstractPlayerEnum.Summoner);
    }

    @Override
    public String getSpireHeartText() {
        return CardCrawlGame.languagePack.getEventString(RingOfDestiny.makeID("SpireHeart_Summoner")).DESCRIPTIONS[0];
    }


    @Override
    public Color getSlashAttackColor() {
        return Color.PURPLE;
    }


    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[1];
    }

    @Override
    public Color getCardRenderColor() {
        return Settings.PURPLE_COLOR;
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
            if (firstAttackAnimation)
                AbstractDungeon.player.state.setAnimation(0, "Attack1", false);
            else
                AbstractDungeon.player.state.setAnimation(0, "Attack2", false);

            firstAttackAnimation = !firstAttackAnimation;
            AbstractDungeon.player.state.addAnimation(0, "Idle", true, 0.0F);
        }
        if (c.type == AbstractCard.CardType.SKILL) {
//            if (firstAttackAnimation)
//                AbstractDungeon.player.state.setAnimation(0, "Skill1", false);
//            else
                AbstractDungeon.player.state.setAnimation(0, "Skill", false);

            AbstractDungeon.player.state.addAnimation(0, "Idle", true, 0.0F);
        }
        if (c.type == AbstractCard.CardType.POWER) {
            AbstractDungeon.player.state.setAnimation(0, "Power", false);
            AbstractDungeon.player.state.addAnimation(0, "Idle", true, 0.0F);
        }
    }

    @Override
    public void playDeathAnimation() {
        if (AbstractDungeon.player != null)
            AbstractDungeon.player.state.setAnimation(0, "Corpse", false);
    }

    @Override
    protected void updateFastAttackAnimation() {}
}

