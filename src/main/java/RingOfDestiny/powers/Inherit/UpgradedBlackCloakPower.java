package RingOfDestiny.powers.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.Inherit.HolyJustice;
import RingOfDestiny.powers.AbstractRingPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class UpgradedBlackCloakPower extends AbstractRingPower {
    public static final String POWER_ID = RingOfDestiny.makeID("UpgradedBlackCloakPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public UpgradedBlackCloakPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        loadRegion("rupture");
    }

    @Override
    public void atStartOfTurn() {
        flash();
        AbstractCard c = new HolyJustice(true);
        c.upgrade();
        addToBot(new MakeTempCardInHandAction(c,this.amount));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}


