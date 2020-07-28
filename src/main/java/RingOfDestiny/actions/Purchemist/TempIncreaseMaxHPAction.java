package RingOfDestiny.actions.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.DiamondManager;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.LoseMaxHpPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;

import static RingOfDestiny.RingOfDestiny.logger;

public class TempIncreaseMaxHPAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(RingOfDestiny.makeID("TempIncreaseMaxHPAction"));
    public static final String[] TEXT = uiStrings.TEXT;
    private int amount;
    private AbstractPlayer p;

    public TempIncreaseMaxHPAction(int amount, AbstractPlayer p) {
        this.amount = amount;
        this.p = p;
        this.duration = Settings.ACTION_DUR_FAST;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (!Settings.isEndless || !AbstractDungeon.player.hasBlight("FullBelly")) {
                if (amount < 0) {
                    logger.info("Why are we decreasing health with increaseMaxHealth()?");
                }

                p.maxHealth += amount;
                AbstractDungeon.effectsQueue.add(new TextAboveCreatureEffect(p.hb.cX - p.animX, p.hb.cY, TEXT[0] + amount, Settings.GREEN_TEXT_COLOR));
                p.healthBarUpdatedEvent();
                AbstractPlayer p = AbstractDungeon.player;
                addToBot(new ApplyPowerAction(p,p,new LoseMaxHpPower(p,this.amount),this.amount));
            }
        }
        tickDuration();
    }
}


