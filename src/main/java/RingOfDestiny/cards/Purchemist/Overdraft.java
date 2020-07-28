package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.UseDiamondIfLostHPAction;
import RingOfDestiny.cards.AbstractPurchemistCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Overdraft extends AbstractPurchemistCard {
    public static final String ID = RingOfDestiny.makeID("Overdraft");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/22.png");
    private static final int COST = 1;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;



    public Overdraft() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 2;
        this.secondaryM = this.baseSecondaryM = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       if(upgraded){
           addToBot(new GainEnergyAction(3));
       }else {
           addToBot(new GainEnergyAction(2));
       }
       addToBot(new UseDiamondIfLostHPAction(this.magicNumber,this.secondaryM));

    }

    public AbstractCard makeCopy() {
        return new Overdraft();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
