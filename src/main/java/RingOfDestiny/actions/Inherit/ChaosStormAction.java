package RingOfDestiny.actions.Inherit;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

public class ChaosStormAction extends AbstractGameAction {
    public int[] multiDamage;
    private boolean freeToPlayOnce;

    public ChaosStormAction(AbstractPlayer p, int[] multiDamage, DamageInfo.DamageType damageType, boolean freeToPlayOnce, int energyOnUse) {
        this.freeToPlayOnce = false;


        this.energyOnUse = -1;


        this.multiDamage = multiDamage;
        this.damageType = damageType;
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
    }

    private DamageInfo.DamageType damageType;
    private AbstractPlayer p;
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

        if (effect > 0) {
            for (int i = 0; i < effect; i++) {
                if (i == 0) {
                    addToBot(new SFXAction("ATTACK_WHIRLWIND"));
                    addToBot(new VFXAction(new WhirlwindEffect(), 0.0F));
                }

                addToBot(new SFXAction("ATTACK_HEAVY"));
                addToBot(new VFXAction(this.p, new CleaveEffect(), 0.0F));
                addToBot(new DamageAllEnemiesAction(this.p, this.multiDamage, this.damageType, AbstractGameAction.AttackEffect.NONE, true));
            }

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }
        this.isDone = true;
    }
}


