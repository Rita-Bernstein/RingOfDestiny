package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.UseDiamondIfLostHPAction;
import RingOfDestiny.cards.AbstractPurchemistCard;
import RingOfDestiny.powers.BleedingPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Recommend extends AbstractPurchemistCard {
    public static final String ID = RingOfDestiny.makeID("Recommend");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/26.png");
    private static final int COST = 1;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;



    public Recommend() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 5;
        this.secondaryM = this.baseSecondaryM = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new UseDiamondIfLostHPAction(2,3));
       addToBot(new ApplyPowerAction(m,p,new BleedingPower(m,p,this.magicNumber),this.magicNumber));
       addToBot(new ApplyPowerAction(m,p,new WeakPower(m,this.secondaryM,false),this.secondaryM));
    }

    public AbstractCard makeCopy() {
        return new Recommend();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }

}
