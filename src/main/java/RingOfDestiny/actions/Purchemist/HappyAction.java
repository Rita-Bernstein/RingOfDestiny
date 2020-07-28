package RingOfDestiny.actions.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.DiamondManager;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class HappyAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(RingOfDestiny.makeID("AttackIntent"));
    public static final String[] TEXT = uiStrings.TEXT;

    private int amount;
    private AbstractMonster targetMonster;

    public HappyAction(int amount, AbstractMonster m) {
        this.amount = amount;
        this.duration = 0.0F;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.targetMonster = m;
    }


    public void update() {
        if (this.targetMonster != null && this.targetMonster.getIntentBaseDmg() < 0) {
            addToBot(new AddDiamondAction(this.amount));
        } else {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, TEXT[1], true));
        }
        this.isDone = true;
    }
}


