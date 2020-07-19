package RingOfDestiny;

import RingOfDestiny.cards.MagicBullet.*;
import RingOfDestiny.cards.ShadowFlower.*;
import RingOfDestiny.helpers.SecondaryMagicVariable;
import basemod.BaseMod;

import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import basemod.abstracts.CustomCard;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import javafx.scene.effect.Shadow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import RingOfDestiny.patches.*;
import RingOfDestiny.character.*;
import RingOfDestiny.relics.*;

@SpireInitializer
public class RingOfDestiny
        implements EditStringsSubscriber,
        EditCharactersSubscriber,
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditKeywordsSubscriber{

    public static String MOD_ID = "RingOfDestiny";

    public static String makeID(String id) {
        return MOD_ID + ":" + id;
    }

    public static String assetPath(String path) {
        return MOD_ID + "/" + path;
    }

    public static String CharacterAssetPath(String ClassName,String path) {
        return MOD_ID + "/" + ClassName + "/" + path;
    }

    public static final String MODNAME = "RingOfDestiny";
    public static final String AUTHOR = "Rita";
    public static final String DESCRIPTION = "";


    //
    public static boolean displaySkin_Ironclad = false;
    public static boolean displaySkin_TheSilent = false;
    public static boolean displaySkin_Defect = false;
    public static boolean displaySkin_Watcher = false;

    private static final float configRow = 50.0f;
    private static final float configColumn = 300.0f;


    public static Properties RingOfDestinyDefaults = new Properties();

    public static Color shadowColorFix = new Color(0.473f,0.429f,0.644f,0.9F);

    public static final Color ShadowFlower_Color = new Color(0.277F,0.714F,0.617F,1.0F);
    public static final Color MagicBullet_Color = new Color(0.796F,0.273F,0.277F,1.0F);
    public static final Color Purchemist_Color = new Color(0.57F,0.277F,0.695F,1.0F);
    public static final Color Inherit_Color = new Color(0.246F,0.566F,0.839F,1.0F);
    public static final Color Summoner_Color = Inherit_Color;//new Color(0.796F,0.273F,0.273F,1.0F);




    public static final Logger logger = LogManager.getLogger(RingOfDestiny.class.getSimpleName());


    public RingOfDestiny() {
        BaseMod.subscribe(this);

        BaseMod.addColor(CardColorEnum.ShadowFlower_LIME,
                ShadowFlower_Color,  ShadowFlower_Color,  ShadowFlower_Color,  ShadowFlower_Color,  ShadowFlower_Color,  ShadowFlower_Color, ShadowFlower_Color,
                assetPath("img/cardui/ShadowFlower/512/bg_attack_lime.png"),
                assetPath("img/cardui/ShadowFlower/512/bg_skill_lime.png"),
                assetPath("img/cardui/ShadowFlower/512/bg_power_lime.png"),
                assetPath("img/cardui/ShadowFlower/512/card_lime_orb.png"),
                assetPath("img/cardui/ShadowFlower/1024/bg_attack_lime.png"),
                assetPath("img/cardui/ShadowFlower/1024/bg_skill_lime.png"),
                assetPath("img/cardui/ShadowFlower/1024/bg_power_lime.png"),
                assetPath("img/cardui/ShadowFlower/1024/card_lime_orb.png"),
                assetPath("img/cardui/ShadowFlower/512/card_lime_small_orb.png"));



        BaseMod.addColor(CardColorEnum.MagicBullet_LIME,
                MagicBullet_Color,  MagicBullet_Color,  MagicBullet_Color,  MagicBullet_Color,  MagicBullet_Color,  MagicBullet_Color, MagicBullet_Color,
                assetPath("img/cardui/MagicBullet/512/bg_attack_lime.png"),
                assetPath("img/cardui/MagicBullet/512/bg_skill_lime.png"),
                assetPath("img/cardui/MagicBullet/512/bg_power_lime.png"),
                assetPath("img/cardui/MagicBullet/512/card_lime_orb.png"),
                assetPath("img/cardui/MagicBullet/1024/bg_attack_lime.png"),
                assetPath("img/cardui/MagicBullet/1024/bg_skill_lime.png"),
                assetPath("img/cardui/MagicBullet/1024/bg_power_lime.png"),
                assetPath("img/cardui/MagicBullet/1024/card_lime_orb.png"),
                assetPath("img/cardui/MagicBullet/512/card_lime_small_orb.png"));

        BaseMod.addColor(CardColorEnum.Purchemist_LIME,
                Purchemist_Color,  Purchemist_Color,  Purchemist_Color,  Purchemist_Color,  Purchemist_Color,  Purchemist_Color, Purchemist_Color,
                assetPath("img/cardui/Purchemist/512/bg_attack_lime.png"),
                assetPath("img/cardui/Purchemist/512/bg_skill_lime.png"),
                assetPath("img/cardui/Purchemist/512/bg_power_lime.png"),
                assetPath("img/cardui/Purchemist/512/card_lime_orb.png"),
                assetPath("img/cardui/Purchemist/1024/bg_attack_lime.png"),
                assetPath("img/cardui/Purchemist/1024/bg_skill_lime.png"),
                assetPath("img/cardui/Purchemist/1024/bg_power_lime.png"),
                assetPath("img/cardui/Purchemist/1024/card_lime_orb.png"),
                assetPath("img/cardui/Purchemist/512/card_lime_small_orb.png"));

        BaseMod.addColor(CardColorEnum.Inherit_LIME,
                Inherit_Color,  Inherit_Color,  Inherit_Color,  Inherit_Color,  Inherit_Color,  Inherit_Color, Inherit_Color,
                assetPath("img/cardui/Inherit/512/bg_attack_lime.png"),
                assetPath("img/cardui/Inherit/512/bg_skill_lime.png"),
                assetPath("img/cardui/Inherit/512/bg_power_lime.png"),
                assetPath("img/cardui/Inherit/512/card_lime_orb.png"),
                assetPath("img/cardui/Inherit/1024/bg_attack_lime.png"),
                assetPath("img/cardui/Inherit/1024/bg_skill_lime.png"),
                assetPath("img/cardui/Inherit/1024/bg_power_lime.png"),
                assetPath("img/cardui/Inherit/1024/card_lime_orb.png"),
                assetPath("img/cardui/Inherit/512/card_lime_small_orb.png"));

        BaseMod.addColor(CardColorEnum.Summoner_LIME,
                Summoner_Color,  Summoner_Color,  Summoner_Color,  Summoner_Color,  Summoner_Color,  Summoner_Color, Summoner_Color,
                assetPath("img/cardui/Summoner/512/bg_attack_lime.png"),
                assetPath("img/cardui/Summoner/512/bg_skill_lime.png"),
                assetPath("img/cardui/Summoner/512/bg_power_lime.png"),
                assetPath("img/cardui/Summoner/512/card_lime_orb.png"),
                assetPath("img/cardui/Summoner/1024/bg_attack_lime.png"),
                assetPath("img/cardui/Summoner/1024/bg_skill_lime.png"),
                assetPath("img/cardui/Summoner/1024/bg_power_lime.png"),
                assetPath("img/cardui/Summoner/1024/card_lime_orb.png"),
                assetPath("img/cardui/Summoner/512/card_lime_small_orb.png"));


    }



    @SuppressWarnings("unused")
    public static void initialize() {
        new RingOfDestiny();
        logger.info("========================= 初始化完成 =========================");
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("========================= 开始加载人物 =========================");

        logger.info(ShadowFlower.charStrings.NAMES[1]);
        BaseMod.addCharacter(new ShadowFlower(ShadowFlower.charStrings.NAMES[1],AbstractPlayerEnum.ShadowFlower),assetPath("characters/ShadowFlower/Button.png"),assetPath("characters/ShadowFlower/portrait.png"),AbstractPlayerEnum.ShadowFlower);

        logger.info(MagicBullet.charStrings.NAMES[1]);
        BaseMod.addCharacter(new MagicBullet(MagicBullet.charStrings.NAMES[1],AbstractPlayerEnum.MagicBullet),assetPath("characters/MagicBullet/Button.png"),assetPath("characters/MagicBullet/portrait.png"),AbstractPlayerEnum.MagicBullet);

    }



    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new SecondaryMagicVariable());

        logger.debug("receiveEditCards started.");
        List<CustomCard> cards = new ArrayList<>();


// ======================
// ======================
// ======================暗影之花
        cards.add(new Defend_SF());
        cards.add(new Strike_SF());
        cards.add(new ShadowCard());
        cards.add(new PoisonedShiv());
        cards.add(new ShadowStab());
        cards.add(new Corrosion());
        cards.add(new FlashOfSlash());
        cards.add(new HexaMudra());
        cards.add(new ShadowBlossom());
        cards.add(new Spur());
        cards.add(new Agile());
        cards.add(new FrozenDark());
        cards.add(new DarkLight());
        cards.add(new ButaMudra());
        cards.add(new HeptMudra());
        cards.add(new ShadowRose());
        cards.add(new Ridicule());
        cards.add(new MetalRelease());
        cards.add(new WoodRelease());
        cards.add(new EarthRelease());
        cards.add(new WristBlade());
        cards.add(new Onikiri());
        cards.add(new SneakAttack());
        cards.add(new ShurikenFlip());
        cards.add(new ShadowCutter());
        cards.add(new EthMudra());
        cards.add(new OctMudra());
        cards.add(new DecaMudra());
        cards.add(new UndecMudra());
        cards.add(new KunaiSpray());
        cards.add(new FinalAttack());
        cards.add(new Stealth());
        cards.add(new Premeditate());
        cards.add(new Servicing());
        cards.add(new Conspiracy());
        cards.add(new FallenFlower());
        cards.add(new BeeZone());
        cards.add(new PentMudra());
        cards.add(new NonMudra());
        cards.add(new WaterRelease());
        cards.add(new FireRelease());
        cards.add(new ShadowShackles());
        cards.add(new Belt());
        cards.add(new GasBomb());
        cards.add(new RattanBeetle());
        cards.add(new StaminaBook());
        cards.add(new DeadlyShadow());
        cards.add(new DeadlyArts());
        cards.add(new Makibisi());
        cards.add(new MethMudra());
        cards.add(new PropMudra());
        cards.add(new PerfectExecution());
        cards.add(new DodeMudra());
        cards.add(new WorldOfFloweringTree());
        cards.add(new ShadowMark());
        cards.add(new InfectMark());
        cards.add(new ForbiddenArts());
        cards.add(new Recollection());
        cards.add(new ShadowPortal());
        cards.add(new DarkProjection());
        cards.add(new RepeatOffender());
        cards.add(new NinjutsuMudra());
        cards.add(new RepeatOffender());
        cards.add(new NinjutsuScroll());
        cards.add(new BottlePoison());



// ======================
// ======================
// ======================魔弹射手
        cards.add(new Defend_MB());
        cards.add(new Strike_MB());
        cards.add(new DeadlyShoot());
//        刻印之灵
        cards.add(new Potential());
        cards.add(new BreakBullet());
        cards.add(new DeathLeakage());
        cards.add(new BlastBullet());
        cards.add(new DarkShield());
        cards.add(new QuickDraw());
//        刻印结界
        cards.add(new BlackLight());
        cards.add(new Crimson());
        cards.add(new Flash());
        cards.add(new DevilKiss());
        cards.add(new Determination());
        cards.add(new TestBullet());
        cards.add(new AmmoRain());
        cards.add(new OriginBullet());
        cards.add(new ForgeBullet());
        cards.add(new CovertOperation());






        for (CustomCard card : cards) {
            BaseMod.addCard(card);
            UnlockTracker.unlockCard(card.cardID);
        }

        logger.debug("receiveEditCards finished.");
    }



    @Override
    public void receiveEditRelics() {
        logger.debug("receiveEditRelics started.");

//            BaseMod.addRelic(new BadgeBless(), RelicType.SHARED);



        BaseMod.addRelicToCustomPool(new ShadowKunai(), CardColorEnum.ShadowFlower_LIME);
        BaseMod.addRelicToCustomPool(new NinjaSuit(), CardColorEnum.ShadowFlower_LIME);


        logger.debug("receiveEditRelics finished.");
    }


    private Settings.GameLanguage languageSupport()
    {
        switch (Settings.language) {
            case ZHS:
                return Settings.language;
            //case JPN:
            //    return Settings.language;
            default:
                return Settings.GameLanguage.ENG;
        }
    }
    public void receiveEditStrings()
    {
        Settings.GameLanguage language = languageSupport();

        // Load english first to avoid crashing if translation doesn't exist for something
        loadLocStrings(Settings.GameLanguage.ENG);
        if(!language.equals(Settings.GameLanguage.ENG)) {
            loadLocStrings(language);
        }

    }

    private void loadLocStrings(Settings.GameLanguage language)
    {
        String path = "localization/" + language.toString().toLowerCase() + "/";

        BaseMod.loadCustomStringsFile(EventStrings.class, assetPath(path + "EventStrings.json"));
        BaseMod.loadCustomStringsFile(UIStrings.class, assetPath(path + "UIStrings.json"));
        BaseMod.loadCustomStringsFile(PotionStrings.class, assetPath(path + "PotionStrings.json"));
        BaseMod.loadCustomStringsFile(CardStrings.class, assetPath(path + "CardStrings.json"));
        //BaseMod.loadCustomStringsFile(MonsterStrings.class, assetPath(path + "monsters.json"));
        BaseMod.loadCustomStringsFile(PowerStrings.class, assetPath(path + "PowerStrings.json"));
        BaseMod.loadCustomStringsFile(RelicStrings.class, assetPath(path + "RelicStrings.json"));
        BaseMod.loadCustomStringsFile(CharacterStrings.class, assetPath(path + "CharacterStrings.json"));
        BaseMod.loadCustomStringsFile(OrbStrings.class, assetPath(path + "OrbStrings.json"));


    }


    private void loadLocKeywords(Settings.GameLanguage language)
    {
        String path = "localization/" + language.toString().toLowerCase() + "/";
        Gson gson = new Gson();
        String json = Gdx.files.internal(assetPath(path + "KeywordStrings.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);

        logger.info("========================= 开始加载关键字 =========================");
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword("ring_of_destiny", keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveEditKeywords() {

        Settings.GameLanguage language = languageSupport();

        // Load english first to avoid crashing if translation doesn't exist for something
        loadLocKeywords(Settings.GameLanguage.ENG);
        if (!language.equals(Settings.GameLanguage.ENG)) {
            loadLocKeywords(language);
        }
    }
}
