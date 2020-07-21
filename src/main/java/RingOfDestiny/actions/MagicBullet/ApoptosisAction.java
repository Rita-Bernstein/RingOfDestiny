package RingOfDestiny.actions.MagicBullet;

import RingOfDestiny.powers.EtchPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ApoptosisAction extends AbstractGameAction {
    private int times;
    private int healingAmount;
    private AbstractCreature target;
    private AbstractCreature source;

    public ApoptosisAction(AbstractCreature target,AbstractCreature source,int times,int healingAmount) {
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_FAST;
        this.times = times;
        this.healingAmount = healingAmount;
        this.target = target;
        this.source = source;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST && this.target != null && this.target.hasPower(EtchPower.POWER_ID)) {
            AbstractPower power = this.target.getPower(EtchPower.POWER_ID);
            int i = this.times -1;
            if(i < 1) i = 1;
            addToBot(new ApplyPowerAction(this.target, this.source, new EtchPower(this.target,power.amount * i),power.amount * i));

            if(power.amount * this.times >= 4)
                addToBot(new HealAction(AbstractDungeon.player,AbstractDungeon.player,this.healingAmount));
        }

        tickDuration();
    }
}


