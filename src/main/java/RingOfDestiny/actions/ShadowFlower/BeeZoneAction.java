package RingOfDestiny.actions.ShadowFlower;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;


public class BeeZoneAction
        extends AbstractGameAction {
    private AbstractPlayer p = AbstractDungeon.player;


    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST &&
                this.p.hasPower("Thorns")) {
            int strAmt = (this.p.getPower("Thorns")).amount;
            addToTop(new ApplyPowerAction(this.p, this.p, new ThornsPower(this.p, strAmt), strAmt));
        }


        tickDuration();
    }
}


