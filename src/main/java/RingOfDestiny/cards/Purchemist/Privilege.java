package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.UseDiamondIfLostHPAction;
import RingOfDestiny.cards.AbstractPurchemistCard;
import RingOfDestiny.powers.PrivilegePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Privilege extends AbstractPurchemistCard {
    public static final String ID = RingOfDestiny.makeID("Privilege");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/51.png");
    private static final int COST = 2;
    public static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;



    public Privilege() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new UseDiamondIfLostHPAction(2,3));
       addToBot(new ApplyPowerAction(p,p,new PrivilegePower(p,this.magicNumber)));
    }

    public AbstractCard makeCopy() {
        return new Privilege();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
