package RingOfDestiny.actions.ShadowFlower;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.DaggerSprayEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

public class MethMudraAction extends AbstractGameAction {
    private boolean freeToPlayOnce;
    private DamageInfo.DamageType damageType;
    private AbstractPlayer p;
    private AbstractMonster m;
    private int damage;
    private int energyOnUse;
    private int extraEffect;

    public MethMudraAction(AbstractPlayer p, AbstractMonster target,int damage, DamageInfo.DamageType damageType, boolean freeToPlayOnce, int energyOnUse, int extraEffect) {
        this.energyOnUse = -1;
        this.damageType = damageType;
        this.p = p;
        this.m = target;
        this.damage = damage;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
        this.extraEffect = extraEffect;
    }


    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        effect += this.extraEffect;

        if (effect > 0) {
            for (int i = 0; i < effect; i++) {
//                if (i == 0) {
//                    addToBot(new SFXAction("ATTACK_WHIRLWIND"));
//                    addToBot(new VFXAction(new WhirlwindEffect(), 0.0F));
//                }
//
//                addToBot(new SFXAction("ATTACK_HEAVY"));
//                addToBot(new VFXAction(this.p, new CleaveEffect(), 0.0F));
//                addToBot(new DamageAllEnemiesAction(this.p, this.multiDamage, this.damageType, AbstractGameAction.AttackEffect.NONE, true));

                addToBot(new DamageAction(m,new DamageInfo(p, this.damage, this.damageType),AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

            }
            addToBot(new ApplyPowerAction(m,p,new PoisonPower(m,p,effect),effect));

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }
        this.isDone = true;
    }
}


