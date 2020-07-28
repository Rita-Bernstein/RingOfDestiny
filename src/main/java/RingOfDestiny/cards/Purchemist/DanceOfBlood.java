package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.AllCostAndTypeToHandAction;
import RingOfDestiny.cards.AbstractPurchemistCard;
import RingOfDestiny.powers.BleedingPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DanceOfBlood extends AbstractPurchemistCard {
    public static final String ID = RingOfDestiny.makeID("DanceOfBlood");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/61.png");
    private static final int COST = 2;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;


    public DanceOfBlood() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new BleedingPower(m, p, this.magicNumber), this.magicNumber));
        addToBot(new AllCostAndTypeToHandAction(0, CardType.ATTACK));
    }

    public AbstractCard makeCopy() {
        return new DanceOfBlood();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);
        }
    }

}
