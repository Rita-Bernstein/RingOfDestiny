package RingOfDestiny;

import RingOfDestiny.cards.Inherit.*;
import RingOfDestiny.cards.MagicBullet.*;
import RingOfDestiny.cards.Purchemist.*;
import RingOfDestiny.cards.ShadowFlower.*;
import RingOfDestiny.cards.Summoner.*;
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
    public static List<CustomCard> mb_SoleCards = new ArrayList<>();
    public static List<CustomCard> all_SoleCards = new ArrayList<>();

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

        logger.info(Purchemist.charStrings.NAMES[1]);
        BaseMod.addCharacter(new Purchemist(Purchemist.charStrings.NAMES[1],AbstractPlayerEnum.Purchemist),assetPath("characters/Purchemist/Button.png"),assetPath("characters/Purchemist/portrait.png"),AbstractPlayerEnum.Purchemist);

        logger.info(Inherit.charStrings.NAMES[1]);
        BaseMod.addCharacter(new Inherit(Inherit.charStrings.NAMES[1],AbstractPlayerEnum.Inherit),assetPath("characters/Inherit/Button.png"),assetPath("characters/Inherit/portrait.png"),AbstractPlayerEnum.Inherit);

        logger.info(Summoner.charStrings.NAMES[1]);
        BaseMod.addCharacter(new Summoner(Summoner.charStrings.NAMES[1],AbstractPlayerEnum.Summoner),assetPath("characters/Summoner/Button.png"),assetPath("characters/Summoner/portrait.png"),AbstractPlayerEnum.Summoner);

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
        cards.add(new ShadowCorrosion());
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
        cards.add(new EtchSpirit());
        cards.add(new Potential());
        cards.add(new BreakBullet());
        cards.add(new DeathLeakage());
        cards.add(new BlastBullet());
        cards.add(new DarkShield());
        cards.add(new QuickDraw());
        cards.add(new Enchantment());
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
        cards.add(new GrowBullet());
        cards.add(new DeathTrack());
        cards.add(new PhantasmalAssailants());
        cards.add(new InitialBullet());
        cards.add(new BloodyLeakage());
        cards.add(new EtchPhantom());
        cards.add(new SolidEtch());
        cards.add(new BulletZero());
        cards.add(new Residual());
        cards.add(new RestraintRing());
        cards.add(new EtchReflection());
        cards.add(new Reload());
        cards.add(new NimbleShield());
        cards.add(new InnerFocus());
        cards.add(new DanceOfDeath());
        cards.add(new Nightmare());
        cards.add(new Illusion());
        cards.add(new Strafing());
        cards.add(new CurtainOfNight());
        cards.add(new Apoptosis());
        cards.add(new OathOfBlood());
        cards.add(new FlowerOfDespair());
        cards.add(new RapidFire());
        cards.add(new SleepingBullet());
        cards.add(new TrackingBullet());
        cards.add(new Prelude());
        cards.add(new Epilogue());
        cards.add(new Underhand());
        cards.add(new DoomsdayMark());
        cards.add(new SecretAmmunition());
        cards.add(new LethalTwins());
        cards.add(new LoomingDeath());
        cards.add(new TimingBreath());
        cards.add(new VolleyBullet());
        cards.add(new PressuredBullet());
        cards.add(new MagicAbsorb());
        cards.add(new Demise());
        cards.add(new BlackFuneral());
        cards.add(new MoonCurtain());
        cards.add(new NightPendant());
        cards.add(new RiteOfOrigin());
        cards.add(new Elegance());



// ======================
// ======================
// ======================氪金术士
        cards.add(new Strike_PU());
        cards.add(new Defend_PU());
        cards.add(new FirstInvest());
        cards.add(new Disdain());
        cards.add(new DrawAgain());
        cards.add(new MultipleInvest());
        cards.add(new StayUpLate());
        cards.add(new Windfall());
        cards.add(new Wilful());
        cards.add(new RaiseMoney());
        cards.add(new NoviceReward());
        cards.add(new BloodBorne());
        cards.add(new Purification());
        cards.add(new Refine());
        cards.add(new Laugh());
        cards.add(new CatGash());
        cards.add(new Filter());
        cards.add(new Concentrate());
        cards.add(new Rebate());
        cards.add(new ThreeRaffles());
        cards.add(new NoLoss());
        cards.add(new CheckOut());
        cards.add(new FreePlay());
        cards.add(new GoldFinger());
        cards.add(new Wild());
        cards.add(new Superior());
        cards.add(new Shuffle());
        cards.add(new FiveDollar());
        cards.add(new FifteenDollar());
        cards.add(new ThirtyDollar());
        cards.add(new Overdraft());
        cards.add(new Happy());
        cards.add(new MassInvest());
        cards.add(new HoldOn());
        cards.add(new Recommend());
        cards.add(new AristocraticPedigree());
        cards.add(new NoInvest());
        cards.add(new MonthCard());
        cards.add(new Shiny());
        cards.add(new Reinforcement());
        cards.add(new BuyBuyBuy());
        cards.add(new Conversion());
        cards.add(new Joyride());
        cards.add(new SwornRevenge());
        cards.add(new DoubleInvest());
        cards.add(new DiamondOfDeath());
        cards.add(new BloodAvalanche());
        cards.add(new Corrosion());
        cards.add(new CrushDiamond());
        cards.add(new Metaphysics());
        cards.add(new InvestMagic());
        cards.add(new LargeGains());
        cards.add(new Rich());
        cards.add(new Repeat());
        cards.add(new ScrewRules());
        cards.add(new Cheat());
        cards.add(new HundredDollar());
        cards.add(new CatCannon());
        cards.add(new Bloodmourne());
        cards.add(new Extraction());
        cards.add(new GoldProtect());
        cards.add(new Privilege());
        cards.add(new DanceOfBlood());
        cards.add(new Show());
        cards.add(new Act());


// ======================
// ======================
// ======================传承天使
        cards.add(new Strike_IH());
        cards.add(new Defend_IH());




// ======================
// ======================
// ======================恶魔召唤师
        cards.add(new Defend_SU());
        cards.add(new Strike_SU());


        for (CustomCard card : cards) {
            BaseMod.addCard(card);
            UnlockTracker.unlockCard(card.cardID);

            if(card.hasTag(CustomTagsEnum.SoleCard)){
                all_SoleCards.add(card);
                System.out.println("公共唯一卡池加入"+ card.name);


                if(card.color == CardColorEnum.MagicBullet_LIME){
                    System.out.println("魔弹唯一卡池加入"+ card.name);
                    mb_SoleCards.add(card);
                }

            }

        }

        logger.debug("receiveEditCards finished.");
    }



    @Override
    public void receiveEditRelics() {
        logger.debug("receiveEditRelics started.");

//            BaseMod.addRelic(new BadgeBless(), RelicType.SHARED);



        BaseMod.addRelicToCustomPool(new ShadowKunai(), CardColorEnum.ShadowFlower_LIME);
        BaseMod.addRelicToCustomPool(new NinjaSuit(), CardColorEnum.ShadowFlower_LIME);


        BaseMod.addRelicToCustomPool(new Flintlock(), CardColorEnum.MagicBullet_LIME);
        BaseMod.addRelicToCustomPool(new GothicDress(), CardColorEnum.MagicBullet_LIME);
        BaseMod.addRelicToCustomPool(new Bow(), CardColorEnum.MagicBullet_LIME);
        BaseMod.addRelicToCustomPool(new LeatherShoes(), CardColorEnum.MagicBullet_LIME);


        BaseMod.addRelicToCustomPool(new DogEyes(), CardColorEnum.Purchemist_LIME);
        BaseMod.addRelicToCustomPool(new Truncheon(), CardColorEnum.Purchemist_LIME);


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
