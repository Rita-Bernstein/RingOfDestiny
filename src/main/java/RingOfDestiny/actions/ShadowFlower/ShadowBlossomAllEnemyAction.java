package RingOfDestiny.actions.ShadowFlower;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;


public class ShadowBlossomAllEnemyAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int cardAmt;
    private DamageInfo info;
    private boolean upgraded;

    public ShadowBlossomAllEnemyAction(DamageInfo info, int cardAmt, boolean upgraded) {
        this.actionType = ActionType.DAMAGE;
        this.p = AbstractDungeon.player;
        this.info = info;
        setValues(target, info);
        this.cardAmt = cardAmt;
        this.upgraded = upgraded;
    }

    public void update() {
        addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        addToBot(new WaitAction(0.2F));


        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                if (!monster.isDead && !monster.isDying && !monster.halfDead) {
                    addToBot(new ShadowBlossomAction(monster, this.info, this.cardAmt, this.upgraded));
                }
            }
        }

        isDone = true;
    }
}


