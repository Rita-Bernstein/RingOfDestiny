package RingOfDestiny.dungeons;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.monster.Ending.Rita;
import RingOfDestiny.scenes.IdeologyCorridorScene;
import actlikeit.dungeons.CustomDungeon;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.monsters.MonsterInfo;
import com.megacrit.cardcrawl.neow.NeowEvent;
import com.megacrit.cardcrawl.rooms.*;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.megacrit.cardcrawl.scenes.AbstractScene;
import com.megacrit.cardcrawl.scenes.TheBottomScene;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class IdeologyCorridor extends CustomDungeon {

    public static final String ID = RingOfDestiny.makeID("IdeologyCorridor");

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    private static final EventStrings forkStrings = CardCrawlGame.languagePack.getEventString(RingOfDestiny.makeID("ForkInTheRoad"));
    public static final String[] TEXT = uiStrings.TEXT;
    public static final String NAME = TEXT[1];


    public IdeologyCorridor() {
        super(NAME, ID);
        this.onEnterEvent(NeowEvent.class);
        this.setMainMusic("RingOfDestiny/audio/music/mainbg.ogg");
        this.addTempMusic("fight", "RingOfDestiny/audio/music/fight.ogg");
        this.addTempMusic("fight_elite", "RingOfDestiny/audio/music/fight_elite.ogg");
        this.addTempMusic("fight_boss", "RingOfDestiny/audio/music/fight_boss.ogg");
    }

    public IdeologyCorridor(CustomDungeon cd, AbstractPlayer p, ArrayList<String> emptyList) {
        super(cd, p, emptyList);
    }

    public IdeologyCorridor(CustomDungeon cd, AbstractPlayer p, SaveFile saveFile) {
        super(cd, p, saveFile);
    }

    @Override
    public AbstractScene DungeonScene() {
        return new IdeologyCorridorScene();
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
        eventRoomChance = 0.25F;
        eliteRoomChance = 0.08F;
        smallChestChance = 50;
        mediumChestChance = 33;
        largeChestChance = 17;
        commonRelicChance = 50;
        uncommonRelicChance = 33;
        rareRelicChance = 17;
        colorlessRareChance = 0.3F;
        cardUpgradedChance = 0.0F;
    }


    @Override
    protected void generateMonsters() {
        generateWeakEnemies(weakpreset);
        generateStrongEnemies(strongpreset);
        generateElites(elitepreset);
    }

    @Override
    protected void generateWeakEnemies(int count) {
        ArrayList<MonsterInfo> monsters = new ArrayList<MonsterInfo>();
        monsters.add(new MonsterInfo("Cultist", 2.0F));
        monsters.add(new MonsterInfo("Jaw Worm", 2.0F));
        monsters.add(new MonsterInfo("2 Louse", 2.0F));
        monsters.add(new MonsterInfo("Small Slimes", 2.0F));
        MonsterInfo.normalizeWeights(monsters);
        populateMonsterList(monsters, count, false);
    }

    @Override
    protected void generateStrongEnemies(int count) {
        ArrayList<MonsterInfo> monsters = new ArrayList<MonsterInfo>();
        monsters.add(new MonsterInfo("Blue Slaver", 2.0F));
        monsters.add(new MonsterInfo("Gremlin Gang", 1.0F));
        monsters.add(new MonsterInfo("Looter", 2.0F));
        monsters.add(new MonsterInfo("Large Slime", 2.0F));
        monsters.add(new MonsterInfo("Lots of Slimes", 1.0F));
        monsters.add(new MonsterInfo("Exordium Thugs", 1.5F));
        monsters.add(new MonsterInfo("Exordium Wildlife", 1.5F));
        monsters.add(new MonsterInfo("Red Slaver", 1.0F));
        monsters.add(new MonsterInfo("3 Louse", 2.0F));
        monsters.add(new MonsterInfo("2 Fungi Beasts", 2.0F));
        MonsterInfo.normalizeWeights(monsters);
        populateFirstStrongEnemy(monsters, generateExclusions());
        populateMonsterList(monsters, count, false);
    }

    @Override
    protected void generateElites(int count) {
        ArrayList<MonsterInfo> monsters = new ArrayList<MonsterInfo>();
        monsters.add(new MonsterInfo("Gremlin Nob", 1.0F));
        monsters.add(new MonsterInfo("Lagavulin", 1.0F));
        monsters.add(new MonsterInfo("3 Sentries", 1.0F));
        MonsterInfo.normalizeWeights(monsters);
        populateMonsterList(monsters, count, true);
    }

    protected ArrayList<String> generateExclusions() {
        ArrayList<String> retVal = new ArrayList<String>();
        switch ((String) monsterList.get(monsterList.size() - 1)) {
            case "Looter":
                retVal.add("Exordium Thugs");
                break;

            case "Blue Slaver":
                retVal.add("Red Slaver");
                retVal.add("Exordium Thugs");
                break;
            case "2 Louse":
                retVal.add("3 Louse");
                break;
            case "Small Slimes":
                retVal.add("Large Slime");
                retVal.add("Lots of Slimes");
                break;
        }


        return retVal;
    }


    protected void initializeBoss() {
        bossList.clear();

        bossList.add("The Guardian");
        bossList.add("Hexaghost");
        bossList.add("Slime Boss");
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
        shrineList.add("Golden Shrine");
        shrineList.add("Transmorgrifier");
        shrineList.add("Purifier");
        shrineList.add("Upgrade Shrine");
        shrineList.add("Wheel of Change");
    }

    protected void initializeEventList() {
        eventList.add("Big Fish");
        eventList.add("The Cleric");
        eventList.add("Dead Adventurer");
        eventList.add("Golden Idol");
        eventList.add("Golden Wing");
        eventList.add("World of Goop");
        eventList.add("Liars Game");
        eventList.add("Living Wall");
        eventList.add("Mushrooms");
        eventList.add("Scrap Ooze");
        eventList.add("Shining Light");
    }

}
