package RingOfDestiny.diamonds;


import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class DiamondManager {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(RingOfDestiny.makeID("DiamondManager"));
    public static final String name = uiStrings.EXTRA_TEXT[0];
    public static final String[] desc = uiStrings.TEXT;

    public String description;

    public DiamondManager() {
    }

    public void updateDescription(){
        this.description = desc[0];
    };

    public void evokeDiamond(int amount) {
        int diamondsNum = getCurrentDiamond();
        if (diamondsNum <= 10 || amount <= 0) {
            return;
        }

        if (diamondsNum < amount) {
            amount = diamondsNum;
        }

        for (int i = 0; i < amount; i++) {
            AbstractDiamond.evokeDiamond();
        }
    }



    public void createDiamond(int amount) {
        int diamondsNum = getCurrentDiamond();
        if (diamondsNum >= 10 || amount <= 0) {
            return;
        }

        if (diamondsNum + amount > 10) {
            amount = 10 - diamondsNum;
        }

        for (int i = 0; i < amount; i++) {
            AbstractDiamond.addDiamond();
        }
    }

    public static int getCurrentDiamond() {
        int count = 0;
        for (AbstractDiamond di : EnergyPanelRenderPatches.PatchEnergyPanelField.diamonds.get(AbstractDungeon.overlayMenu.energyPanel)) {
            if (di.isSocket = true) {
                count++;
            }
        }
        return count;
    }

    public static boolean enoughDiamond(int amount) {
        boolean enough = false;
        int count = getCurrentDiamond();
        if(count >= amount){
            enough = true;
        }
        return enough;
    }
}


