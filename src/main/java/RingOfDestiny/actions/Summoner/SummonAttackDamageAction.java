package RingOfDestiny.actions.Summoner;

import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.DiamondManager;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.patches.SummonPatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SummonAttackDamageAction extends AbstractGameAction {
    private int amount;
    private int damage;
    private AbstractMonster m;

    public SummonAttackDamageAction(AbstractMonster m, int amount, int damage) {
        this.m = m;
        this.amount = amount;
        this.damage = damage;
        this.duration = Settings.ACTION_DUR_FAST;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            SummonPatches.AbstractPlayerSummonField.summon.get(AbstractDungeon.player).attackDamage(this.m, this.amount, this.damage);
        }
        tickDuration();
    }
}


