package RingOfDestiny.dungeons;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.monster.WisdomThrone.*;
import RingOfDestiny.scenes.WisdomThroneScene;
import actlikeit.dungeons.CustomDungeon;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.MonsterInfo;
import com.megacrit.cardcrawl.neow.NeowEvent;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.megacrit.cardcrawl.scenes.AbstractScene;


public class WisdomThrone extends CustomDungeon {

    public static final String ID = RingOfDestiny.makeID("WisdomThrone");

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    private static final EventStrings forkStrings = CardCrawlGame.languagePack.getEventString(RingOfDestiny.makeID("ForkInTheRoad"));
    public static final String[] TEXT = uiStrings.TEXT;
    public static final String NAME = TEXT[1];


    public WisdomThrone() {
        super(NAME, ID);
//        this.onEnterEvent(NeowEvent.class);
        this.setMainMusic("RingOfDestiny/audio/music/mainbg.ogg");
        this.addTempMusic("fight", "RingOfDestiny/audio/music/fight.ogg");
        this.addTempMusic("fight_elite", "RingOfDestiny/audio/music/fight_elite.ogg");
        this.addTempMusic("fight_boss", "RingOfDestiny/audio/music/fight_boss.ogg");
    }

    public WisdomThrone(CustomDungeon cd, AbstractPlayer p, ArrayList<String> emptyList) {
        super(cd, p, emptyList);
    }

    public WisdomThrone(CustomDungeon cd, AbstractPlayer p, SaveFile saveFile) {
        super(cd, p, saveFile);
    }

    @Override
    public AbstractScene DungeonScene() {
        return new WisdomThroneScene();
    }


    @Override
    public String getActNumberText() {
        return uiStrings.TEXT[0];
    }


    @Override
    public String getBodyText() {
        return forkStrings.DESCRIPTIONS[0];
    }

    @Override
    public String getAfterSelectText() {
        return forkStrings.DESCRIPTIONS[1];
    }

    @Override
    public String getOptionText() {
        return forkStrings.OPTIONS[0];
    }


    protected void initializeLevelSpecificChances() {
        shopRoomChance = 0.05F;
        restRoomChance = 0.12F;
        treasureRoomChance = 0.0F;
        eventRoomChance = 0.22F;
        eliteRoomChance = 0.08F;


        smallChestChance = 50;
        mediumChestChance = 33;
        largeChestChance = 17;


        commonRelicChance = 50;
        uncommonRelicChance = 33;
        rareRelicChance = 17;


        colorlessRareChance = 0.3F;
        if (AbstractDungeon.ascensionLevel >= 12) {
            cardUpgradedChance = 0.25F;
        } else {
            cardUpgradedChance = 0.5F;
        }
    }


    @Override
    protected void generateMonsters() {
        generateWeakEnemies(2);
        generateStrongEnemies(12);
        generateElites(10);
    }

    @Override
    protected void generateWeakEnemies(int count) {
        ArrayList<MonsterInfo> monsters = new ArrayList<MonsterInfo>();
        monsters.add(new MonsterInfo("3 Darklings", 2.0F));
        monsters.add(new MonsterInfo("Orb Walker", 2.0F));
        monsters.add(new MonsterInfo("3 Shapes", 2.0F));
        monsters.add(new MonsterInfo(Gargoyle.ID, 2.0F));
        MonsterInfo.normalizeWeights(monsters);
        populateMonsterList(monsters, count, false);
    }

    @Override
    protected void generateStrongEnemies(int count) {
        ArrayList<MonsterInfo> monsters = new ArrayList<MonsterInfo>();
        monsters.add(new MonsterInfo("Spire Growth", 1.0F));
        monsters.add(new MonsterInfo("Transient", 1.0F));
        monsters.add(new MonsterInfo("4 Shapes", 1.0F));
        monsters.add(new MonsterInfo("Maw", 1.0F));
        monsters.add(new MonsterInfo("Sphere and 2 Shapes", 1.0F));
        monsters.add(new MonsterInfo("Jaw Worm Horde", 1.0F));
        monsters.add(new MonsterInfo("3 Darklings", 1.0F));
        monsters.add(new MonsterInfo("Writhing Mass", 1.0F));

        monsters.add(new MonsterInfo("RingOfDestiny:Harp and Violin", 1.0F));
        MonsterInfo.normalizeWeights(monsters);
        populateFirstStrongEnemy(monsters, generateExclusions());
        populateMonsterList(monsters, count, false);
    }

    @Override
    protected void generateElites(int count) {
        ArrayList<MonsterInfo> monsters = new ArrayList<MonsterInfo>();
        monsters.add(new MonsterInfo(ShadowDance.ID, 2.0F));
        monsters.add(new MonsterInfo(UmbrellaSpirit.ID, 2.0F));
        monsters.add(new MonsterInfo(Tentacle.ID, 2.0F));
        MonsterInfo.normalizeWeights(monsters);
        populateMonsterList(monsters, count, true);
    }

    protected ArrayList<String> generateExclusions() {
        ArrayList<String> retVal = new ArrayList<String>();
        switch ((String) monsterList.get(monsterList.size() - 1)) {
            case "3 Darklings":
                retVal.add("3 Darklings");
                break;
            case "Orb Walker":
                retVal.add("Orb Walker");
                break;
            case "3 Shapes":
                retVal.add("4 Shapes");
                break;
        }


        return retVal;
    }


    protected void initializeBoss() {
        bossList.clear();

        bossList.add("Awakened One");
        bossList.add("Time Eater");
        bossList.add("Donu and Deca");

        Collections.shuffle(bossList, new Random(monsterRng.randomLong()));

    }


    @Override
    protected void initializeEventImg() {
        if (eventBackgroundImg != null) {
            eventBackgroundImg.dispose();
            eventBackgroundImg = null;
        }
        eventBackgroundImg = ImageMaster.loadImage("images/ui/event/panel.png");
    }

    protected void initializeShrineList() {
        shrineList.add("Match and Keep!");
        shrineList.add("Wheel of Change");
        shrineList.add("Golden Shrine");
        shrineList.add("Transmorgrifier");
        shrineList.add("Purifier");
        shrineList.add("Upgrade Shrine");
    }

    protected void initializeEventList() {
        eventList.add("Falling");
        eventList.add("MindBloom");
        eventList.add("The Moai Head");
        eventList.add("Mysterious Sphere");
        eventList.add("SensoryStone");
        eventList.add("Tomb of Lord Red Mask");
        eventList.add("Winding Halls");
    }

}
