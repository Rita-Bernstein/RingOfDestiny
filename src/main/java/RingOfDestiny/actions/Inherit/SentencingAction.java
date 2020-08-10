package RingOfDestiny.actions.Inherit;

import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.DiamondManager;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.Inherit.ConvictionPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SentencingAction extends AbstractGameAction {
    private int amount;
    private AbstractPlayer p;
    private DamageInfo info;

    public SentencingAction(int amount, AbstractMonster target,DamageInfo info) {
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_FAST;
        this.target = target;
        this.p = AbstractDungeon.player;
        this.info    = info;
    }



    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            int count = 1;

            if (this.target.hasPower(ConvictionPower.POWER_ID)) {
                if (this.target.getPower(ConvictionPower.POWER_ID).amount >= this.amount) {
                    count += 2;
                    addToBot(new ReducePowerAction(this.target, p, ConvictionPower.POWER_ID, this.amount));
                } else {
                    count += this.target.getPower(ConvictionPower.POWER_ID).amount;
                    addToBot(new RemoveSpecificPowerAction(this.target, p, ConvictionPower.POWER_ID));
                }
            }

            for (int i = 0; i < count; i++) {
                addToBot(new DamageAction(this.target, this.info, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
        }
        tickDuration();
    }
}


