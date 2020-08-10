package RingOfDestiny.actions.Inherit;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import java.util.ArrayList;

public class EvilBurnsAction extends AbstractGameAction {
    public int[] multiDamage;
    private DamageInfo.DamageType damageType;
    private AbstractPlayer p;

    public EvilBurnsAction(AbstractPlayer p, int[] multiDamage, DamageInfo.DamageType damageType) {
        this.p = p;
        this.multiDamage = multiDamage;
        this.damageType = damageType;
        this.duration = Settings.ACTION_DUR_FAST;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            int count = 0;

            ArrayList<AbstractCard> cardsToExhaust = new ArrayList<AbstractCard>();
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.type != AbstractCard.CardType.ATTACK) {
                    cardsToExhaust.add(c);
                }
            }

            for (AbstractCard c : cardsToExhaust) {
                addToBot(new SFXAction("ATTACK_HEAVY"));
                addToBot(new VFXAction(this.p, new CleaveEffect(), 0.0F));
                addToBot(new DamageAllEnemiesAction(this.p, this.multiDamage, this.damageType, AbstractGameAction.AttackEffect.NONE, true));
            }

            for (AbstractCard c : cardsToExhaust) {
                addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
            }

            this.isDone = true;
        }
    }
}


