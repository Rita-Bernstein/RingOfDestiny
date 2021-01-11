package RingOfDestiny.events;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.relics.TearsOfMaid;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;


public class FruitStall extends AbstractImageEvent {

	public static final String ID = RingOfDestiny.makeID("FruitStall");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    private CurrentScreen curScreen = CurrentScreen.INTRO;
    private OptionChosen option = OptionChosen.NONE;
    private FruitChosen selectedFruit = FruitChosen.NONE;
    private boolean[] Fruit = new boolean[4];

    private int StrawberryGold = 72;
    private int MANGOGold = 40;
    private int PEARGold = 36;

    private int goldLoss = 100;


    private enum CurrentScreen {
        INTRO, DONE,
    }

    private enum OptionChosen {
         STEAL, BUY, SELL, NONE,INTRO,DONE
    }

    private enum FruitChosen {
        PEAR, MANGO , Strawberry , NONE,INTRO,
    }

    public FruitStall() {
		super(NAME, DESCRIPTIONS[0], RingOfDestiny.assetPath("images/events/FruitStall.jpg"));

        getPlayerFruit();

		this.imageEventText.setDialogOption(OPTIONS[0]);
        this.imageEventText.setDialogOption(OPTIONS[1]);

        }

    void  getPlayerFruit() {

            if (AbstractDungeon.player.hasRelic("Pear")) {Fruit[0] = true;}else {Fruit[0] = false;}
            if (AbstractDungeon.player.hasRelic("Mango")) {Fruit[1] = true;}else {Fruit[2] = false;}
            if (AbstractDungeon.player.hasRelic("Strawberry")) {Fruit[2] = true;}else {Fruit[3] = false;}

        if (Fruit[0] || Fruit[1] || Fruit[2]){
            Fruit[3] = true;
        }else {Fruit[3] = false;}

    }


   public void onEnterRoom() {
            if (Settings.AMBIANCE_ON) {
                CardCrawlGame.sound.play(RingOfDestiny.makeID("FruitStall"));
            }
   }

 @Override
    protected void buttonEffect(int buttonPressed) {
        switch (selectedFruit){
            case NONE:
                switch (option){
                    case NONE:
                        switch (curScreen) {
                            case INTRO:
                            switch (buttonPressed) {
                                case 0:
                                    imageEventText.updateBodyText(DESCRIPTIONS[1]);
                                    imageEventText.clearRemainingOptions();
                                    imageEventText.updateDialogOption(0, OPTIONS[2]);

                                    if (AbstractDungeon.player.gold >= 100) {
                                        imageEventText.updateDialogOption(1, OPTIONS[3]);
                                    } else {
                                        imageEventText.updateDialogOption(1, OPTIONS[4],true);
                                    }

                                    if (Fruit[3] == true) {
                                        imageEventText.updateDialogOption(2, OPTIONS[5]);
                                    } else {
                                        imageEventText.updateDialogOption(2, OPTIONS[10],true);
                                    }

                                    imageEventText.updateDialogOption(3, OPTIONS[11]);

                                    option = OptionChosen.INTRO;
                                    break;
                                case 1:
                                    imageEventText.updateBodyText(DESCRIPTIONS[7]);
                                    imageEventText.updateDialogOption(0, OPTIONS[11]);
                                    imageEventText.clearRemainingOptions();
                                    option = OptionChosen.NONE;
                                    logMetricIgnored(ID);
                                    curScreen = CurrentScreen.DONE;
                                    break;
                            }
                                break;

                            case  DONE:
                                this.imageEventText.setDialogOption(OPTIONS[11]);
                                imageEventText.clearRemainingOptions();
                                if (buttonPressed == 0){}
                                openMap();
                                break;
                        }
                        break;

                    case INTRO:
                        switch (buttonPressed) {
                            case 0:
                                option = OptionChosen.STEAL;
                                imageEventText.updateBodyText(DESCRIPTIONS[2]);
                                imageEventText.clearRemainingOptions();
                                AbstractDungeon.player.heal(AbstractDungeon.player.currentHealth);
                                imageEventText.updateDialogOption(0, OPTIONS[11]);

                                AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), new TearsOfMaid());
                                break;

                            case 1:
                                option = OptionChosen.BUY;
                                imageEventText.updateBodyText(DESCRIPTIONS[3]);
                                imageEventText.clearRemainingOptions();
                                AbstractDungeon.player.loseGold(this.goldLoss);
                                AbstractDungeon.player.heal(AbstractDungeon.player.currentHealth/2);
                                imageEventText.updateDialogOption(0, OPTIONS[11]);
                                break;

                            case 2:
                                option = OptionChosen.SELL;
                                imageEventText.updateBodyText(DESCRIPTIONS[4]);
                                imageEventText.clearRemainingOptions();
                                if (Fruit[0]){imageEventText.updateDialogOption(0, OPTIONS[6] + PEARGold + OPTIONS[9]);}else{imageEventText.updateDialogOption(0, OPTIONS[10],true);}
                                if (Fruit[1]){imageEventText.updateDialogOption(1, OPTIONS[7] + MANGOGold + OPTIONS[9]);}else{imageEventText.updateDialogOption(1, OPTIONS[10],true);}
                                if (Fruit[2]){imageEventText.updateDialogOption(2, OPTIONS[8] + StrawberryGold+ OPTIONS[9]);}else{imageEventText.updateDialogOption(2, OPTIONS[10],true);}
                                imageEventText.updateDialogOption(3, OPTIONS[12]);

                                break;
                            case 3:
                                imageEventText.updateBodyText(DESCRIPTIONS[7]);
                                imageEventText.updateDialogOption(0, OPTIONS[11]);
                                imageEventText.clearRemainingOptions();
                                option = OptionChosen.NONE;
                                logMetricIgnored(ID);
                                curScreen = CurrentScreen.DONE;
                                break;

                        }

                        break;

                    case STEAL:
                        if (buttonPressed == 0){}
                        openMap();
                        break;

                    case BUY:
                        if (buttonPressed == 0){}
                        openMap();
                        break;

                    case SELL:
                        switch (buttonPressed){
                            case 0:
                                Fruit[0] = false;
                                if (Fruit[0] || Fruit[1] || Fruit[2]){ Fruit[3] = true;}else {Fruit[3] = false;}
                                AbstractDungeon.player.loseRelic("Pear");
                                imageEventText.updateBodyText(DESCRIPTIONS[6]);
                                AbstractDungeon.effectList.add(new RainingGoldEffect(this.PEARGold));
                                AbstractDungeon.player.gainGold(this.PEARGold);
                                imageEventText.updateDialogOption(0, OPTIONS[10],true);


                                break;
                            case 1:
                                Fruit[1] = false;
                                if (Fruit[0] || Fruit[1] || Fruit[2]){ Fruit[3] = true;}else {Fruit[3] = false;}
                                AbstractDungeon.player.loseRelic("Mango");
                                imageEventText.updateBodyText(DESCRIPTIONS[6]);
                                AbstractDungeon.effectList.add(new RainingGoldEffect(this.MANGOGold));
                                AbstractDungeon.player.gainGold(this.MANGOGold);
                                imageEventText.updateDialogOption(1, OPTIONS[10],true);
                                break;
                            case 2:
                                Fruit[2] = false;
                                if (Fruit[0] || Fruit[1] || Fruit[2]){ Fruit[3] = true;}else {Fruit[3] = false;}
                                imageEventText.updateBodyText(DESCRIPTIONS[5]);
                                AbstractDungeon.player.loseRelic("Strawberry");
                                AbstractDungeon.effectList.add(new RainingGoldEffect(this.StrawberryGold));
                                AbstractDungeon.player.gainGold(this.StrawberryGold);
                                imageEventText.updateDialogOption(2, OPTIONS[10],true);
                                break;
                            case 3:
                                imageEventText.updateBodyText(DESCRIPTIONS[1]);
                                imageEventText.updateDialogOption(0, OPTIONS[2]);

                                if (AbstractDungeon.player.gold >= 100) {
                                    imageEventText.updateDialogOption(1, OPTIONS[3]);
                                } else {
                                    imageEventText.updateDialogOption(1, OPTIONS[4]);
                                }

                                if (Fruit[3]) {
                                    imageEventText.updateDialogOption(2, OPTIONS[5]);
                                } else {
                                    imageEventText.updateDialogOption(2, OPTIONS[10],true);
                                }
                                imageEventText.updateDialogOption(3, OPTIONS[11]);
                                option = OptionChosen.INTRO;

                                break;


                        }
                        break;

                    case DONE:
                        logMetricIgnored(ID);
                        openMap();
                        break;
                }



        }
    }



    public void update() {
        super.update();

    }

}
