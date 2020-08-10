package RingOfDestiny.actions.Summoner;

import RingOfDestiny.cards.Summoner.DoomsdayMeteorite;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;


public class DoomsdayBladeAction extends AbstractGameAction {
    private DamageInfo info;
    private boolean upgraded;

    public DoomsdayBladeAction(AbstractCreature target, DamageInfo info, boolean upgraded) {
        this.info = info;
        setValues(target, info);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.upgraded = upgraded;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER &&
                this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.BLUNT_HEAVY));

            this.target.damage(this.info);

            if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead)  {
                AbstractCard c = new DoomsdayMeteorite();
                if(upgraded)c.upgrade();
                addToBot(new MakeTempCardInDrawPileAction(c,2,true,true));
            }

            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }


        tickDuration();
    }
}


