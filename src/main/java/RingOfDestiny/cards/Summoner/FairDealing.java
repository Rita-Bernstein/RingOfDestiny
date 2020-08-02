package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractSummonerCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FairDealing extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("FairDealing");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/29.png");
    private static final int COST = 0;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;



    public FairDealing() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.secondaryM = this.baseSecondaryM = 2;
        this.magicNumber = this.baseMagicNumber = 2;
        this.baseBlock = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i=0;i< this.magicNumber;i++){
            addToBot(new LoseHPAction(p,p,this.secondaryM));
        }
        addToBot(new GainEnergyAction(1));
        addToBot(new GainBlockAction(p, p, this.block));
    }

    public AbstractCard makeCopy() {
        return new FairDealing();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            upgradeSecondM(-1);
        }
    }

}
