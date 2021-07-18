package RingOfDestiny.dungeons;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.monster.KnowledgeHall.*;
import RingOfDestiny.scenes.KnowledgeHallScene;
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


public class KnowledgeHall extends CustomDungeon {

    public static final String ID = RingOfDestiny.makeID("KnowledgeHall");

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    private static final EventStrings forkStrings = CardCrawlGame.languagePack.getEventString(RingOfDestiny.makeID("ForkInTheRoad"));
    public static final String[] TEXT = uiStrings.TEXT;
    public static final String NAME = TEXT[1];


    public KnowledgeHall() {
        super(NAME, ID);
//        this.onEnterEvent(NeowEvent.class);
        this.setMainMusic("RingOfDestiny/audio/music/mainbg.ogg");
        this.addTempMusic("fight", "RingOfDestiny/audio/music/fight.ogg");
        this.addTempMusic("fight_elite", "RingOfDestiny/audio/music/fight_elite.ogg");
        this.addTempMusic("fight_boss", "RingOfDestiny/audio/music/fight_boss.ogg");


    }

    public KnowledgeHall(CustomDungeon cd, AbstractPlayer p, ArrayList<String> emptyList) {
        super(cd, p, emptyList);
    }

    public KnowledgeHall(CustomDungeon cd, AbstractPlayer p, SaveFile saveFile) {
        super(cd, p, saveFile);
    }

    @Override
    public AbstractScene DungeonScene() {
        return new KnowledgeHallScene();
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
            cardUpgradedChance = 0.125F;
        } else {
            cardUpgradedChance = 0.25F;
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
        monsters.add(new MonsterInfo("Spheric Guardian", 2.0F));
        monsters.add(new MonsterInfo("Chosen", 2.0F));
        monsters.add(new MonsterInfo("Shell Parasite", 2.0F));
        monsters.add(new MonsterInfo("3 Byrds", 2.0F));
        monsters.add(new MonsterInfo("2 Thieves", 2.0F));

        monsters.add(new MonsterInfo(Orator.ID, 2.0F));
        MonsterInfo.normalizeWeights(monsters);
        populateMonsterList(monsters, count, false);
    }

    @Override
    protected void generateStrongEnemies(int count) {
        ArrayList<MonsterInfo> monsters = new ArrayList<MonsterInfo>();
        monsters.add(new MonsterInfo("Chosen and Byrds", 2.0F));
        monsters.add(new MonsterInfo("Sentry and Sphere", 2.0F));
        monsters.add(new MonsterInfo("Snake Plant", 6.0F));
        monsters.add(new MonsterInfo("Snecko", 4.0F));
        monsters.add(new MonsterInfo("Centurion and Healer", 6.0F));
        monsters.add(new MonsterInfo("Cultist and Chosen", 3.0F));
        monsters.add(new MonsterInfo("3 Cultists", 3.0F));
        monsters.add(new MonsterInfo("Shelled Parasite and Fungi", 3.0F));
        MonsterInfo.normalizeWeights(monsters);
        populateFirstStrongEnemy(monsters, generateExclusions());
        populateMonsterList(monsters, count, false);
    }

    @Override
    protected void generateElites(int count) {
        ArrayList<MonsterInfo> monsters = new ArrayList<MonsterInfo>();
        monsters.add(new MonsterInfo(Sledgehammer.ID, 1.0F));
        monsters.add(new MonsterInfo("RingOfDestiny:3Meows", 1.0F));
        monsters.add(new MonsterInfo(DanceOfSakura.ID, 1.0F));
        MonsterInfo.normalizeWeights(monsters);
        populateMonsterList(monsters, count, true);
    }

    protected ArrayList<String> generateExclusions() {
        ArrayList<String> retVal = new ArrayList<String>();
        switch ((String) monsterList.get(monsterList.size() - 1)) {
            case "Spheric Guardian":
                retVal.add("Sentry and Sphere");
                break;
            case "3 Byrds":
                retVal.add("Chosen and Byrds");
                break;
            case "Chosen":
                retVal.add("Chosen and Byrds");
                retVal.add("Cultist and Chosen");
                break;
        }


        return retVal;
    }


    protected void initializeBoss() {
        bossList.clear();


        bossList.add(BraveWarrior.ID);
        bossList.add(BraveWarrior.ID);
        bossList.add(BraveWarrior.ID);

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
        eventList.add("Addict");
        eventList.add("Back to Basics");
        eventList.add("Beggar");
        eventList.add("Colosseum");
        eventList.add("Cursed Tome");
        eventList.add("Drug Dealer");
        eventList.add("Forgotten Altar");
        eventList.add("Ghosts");
        eventList.add("Masked Bandits");
        eventList.add("Nest");
        eventList.add("The Library");
        eventList.add("The Mausoleum");
        eventList.add("Vampires");
    }

}
