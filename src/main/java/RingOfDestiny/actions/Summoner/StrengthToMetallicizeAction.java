package RingOfDestiny.actions.Summoner;

import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.DiamondManager;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.patches.SummonPatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class StrengthToMetallicizeAction extends AbstractGameAction {
    private int amount;
    private AbstractPlayer p;

    public StrengthToMetallicizeAction() {
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hasPower(StrengthPower.POWER_ID)) {
                this.amount = this.p.getPower(StrengthPower.POWER_ID).amount;
                addToBot(new ApplyPowerAction(this.p, this.p, new MetallicizePower(this.p, this.amount), this.amount));
                addToBot(new RemoveSpecificPowerAction(this.p, this.p, StrengthPower.POWER_ID));
            }
        }
        tickDuration();
    }
}


