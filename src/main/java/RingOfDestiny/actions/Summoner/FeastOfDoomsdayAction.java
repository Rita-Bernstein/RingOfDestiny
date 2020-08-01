package RingOfDestiny.actions.Summoner;

import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.DiamondManager;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.Summoner.DoomsdayImprintPower;
import RingOfDestiny.powers.Summoner.ExtractPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FeastOfDoomsdayAction extends AbstractGameAction {

    public FeastOfDoomsdayAction(AbstractMonster m) {
        this.target = m;
        setValues(m,AbstractDungeon.player);
        this.actionType = ActionType.POWER;
        this.duration = Settings.ACTION_DUR_FAST;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if(this.target.hasPower(DoomsdayImprintPower.POWER_ID)){
                int amount = this.target.getPower(DoomsdayImprintPower.POWER_ID).amount;
                addToBot(new ApplyPowerAction(this.target,AbstractDungeon.player,new ExtractPower(this.target,this.source,amount),amount));
                addToBot(new RemoveSpecificPowerAction(this.target,this.source,DoomsdayImprintPower.POWER_ID));
            }
        }
        tickDuration();
    }
}


