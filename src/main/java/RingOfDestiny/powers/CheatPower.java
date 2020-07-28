package RingOfDestiny.powers;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.ShadowFlower.ShadowRose;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

public class CheatPower extends AbstractPower {
    public static final String POWER_ID = RingOfDestiny.makeID("CheatPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CheatPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        loadRegion("doubleTap");
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            if (!card.purgeOnUse && card.target == AbstractCard.CardTarget.ENEMY) {
                AbstractMonster m = null;

                if (action.target != null) {
                    m = (AbstractMonster) action.target;
                }

                if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                    for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                        if (!monster.isDead && !monster.isDying && monster != m) {
                            AbstractCard tmp = card.makeSameInstanceOf();
                            AbstractDungeon.player.limbo.addToBottom(tmp);
                            tmp.current_x = card.current_x;
                            tmp.current_y = card.current_y;
                            tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
                            tmp.target_y = Settings.HEIGHT / 2.0F;


                            tmp.calculateCardDamage(monster);


                            tmp.purgeOnUse = true;
                            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, monster, card.energyOnUse, true, true), true);
                        }
                    }
                }
            }

            addToBot(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, CheatPower.POWER_ID, 1));
        }

    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, CheatPower.POWER_ID));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}


