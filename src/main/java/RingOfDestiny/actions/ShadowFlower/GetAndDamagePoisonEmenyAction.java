package RingOfDestiny.actions.ShadowFlower;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class GetAndDamagePoisonEmenyAction extends AbstractGameAction {
    private AbstractMonster m;
    private int damage;
    private AbstractPlayer p;
    private DamageInfo.DamageType damageTypeForTurn;

    public GetAndDamagePoisonEmenyAction(AbstractMonster target, DamageInfo.DamageType damageTypeForTurn) {
        this.p = AbstractDungeon.player;
        this.m = target;
        this.damage = 0;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.attackEffect = AbstractGameAction.AttackEffect.SLASH_VERTICAL;
        this.damageTypeForTurn = damageTypeForTurn;
    }

    public int calculateDamage(AbstractMonster mo, int baseDamage) {
        if (mo != null) {
            float tmp = baseDamage;

            for (AbstractRelic r : p.relics) {
                tmp = r.atDamageModify(tmp, null);
            }

            for (AbstractPower p : p.powers) {
                tmp = p.atDamageGive(tmp, this.damageTypeForTurn, null);
            }

            tmp = p.stance.atDamageGive(tmp, this.damageTypeForTurn, null);


            for (AbstractPower p : mo.powers) {
                tmp = p.atDamageReceive(tmp, this.damageTypeForTurn, null);
            }

            for (AbstractPower p : p.powers) {
                tmp = p.atDamageFinalGive(tmp, this.damageTypeForTurn, null);
            }

            for (AbstractPower p : mo.powers) {
                tmp = p.atDamageFinalReceive(tmp, this.damageTypeForTurn, null);
            }

            if (tmp < 0.0F) {
                tmp = 0.0F;
            }

            return (int) MathUtils.floor(tmp);
        } else {
            return 0;
        }
    }


    public void update() {
        if (this.m == null) {
            this.isDone = true;
            return;
        }
        if (this.m.hasPower(PoisonPower.POWER_ID)) {
            this.damage = this.m.getPower(PoisonPower.POWER_ID).amount;
            this.damage = calculateDamage(this.m, this.damage);
            addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }

        this.isDone = true;

    }
}


