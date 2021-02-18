package RingOfDestiny.actions.MagicBullet;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class MagicAbsorbAction extends AbstractGameAction {
    private boolean freeToPlayOnce;
    private int exEffect;

    public MagicAbsorbAction(AbstractPlayer p, AbstractMonster m, int exEffect, boolean freeToPlayOnce, int energyOnUse) {
        this.exEffect = 0;

        this.energyOnUse = -1;

        this.p = p;
        this.m = m;
        this.freeToPlayOnce = freeToPlayOnce;
        this.exEffect = exEffect;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
    }

    private AbstractPlayer p;
    private AbstractMonster m;
    private int energyOnUse;

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }


        effect += exEffect;


        if (effect > 0) {
            addToBot(new ApplyPowerAction(this.m, this.p, new StrengthPower(this.m, -effect), -effect));
            addToBot(new ApplyPowerAction(this.p, this.p, new StrengthPower(this.p, effect), effect));

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }
        this.isDone = true;
    }
}


