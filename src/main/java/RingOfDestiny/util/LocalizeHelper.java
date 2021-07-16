package RingOfDestiny.util;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

public class LocalizeHelper {
    public static final UIStrings RunHistoryMonsterNames ;

    static {
        RunHistoryMonsterNames = CardCrawlGame.languagePack.getUIString("RingOfDestiny:RunHistoryMonsterNames");
    }

    public LocalizeHelper() {

    }
}