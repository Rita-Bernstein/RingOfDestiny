package RingOfDestiny.screens;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.patches.CustomCurrentScreenEnum;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;



public class SummonSelectScreen {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(RingOfDestiny.makeID("SummonSelectScreen"));
    public static final String[] TEXT = uiStrings.TEXT;

    private String header = TEXT[0];

    private AbstractSummonSelectItem[] items = new AbstractSummonSelectItem[]{
            new SummonSelectItemSuccubus(),
            new SummonSelectItemDemon(),
            new SummonSelectItemVampire()};


    public void update() {
        for(AbstractSummonSelectItem suItem : items){
            suItem.update();
        }
    }


    public void render(SpriteBatch sb) {
        for(AbstractSummonSelectItem suItem : items){
            suItem.render(sb);
        }
    }


    public void open() {
        AbstractDungeon.topPanel.unhoverHitboxes();

        AbstractDungeon.isScreenUp = true;
        AbstractDungeon.screen = CustomCurrentScreenEnum.SummonSelect;

        AbstractDungeon.dynamicBanner.appear(header);
        AbstractDungeon.overlayMenu.proceedButton.hide();
        AbstractDungeon.overlayMenu.showBlackScreen();

    }
}


