package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.AddDiamondAction;
import RingOfDestiny.cards.AbstractPurchemistCard;
import RingOfDestiny.powers.BloodmournePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Bloodmourne extends AbstractPurchemistCard {
    public static final String ID = RingOfDestiny.makeID("Bloodmourne");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/40.png");
    private static final int COST = 2;
    public static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;



    public Bloodmourne() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new ApplyPowerAction(p,p,new BloodmournePower(p,this.magicNumber)));
    }

    public AbstractCard makeCopy() {
        return new Bloodmourne();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);
        }
    }

}
