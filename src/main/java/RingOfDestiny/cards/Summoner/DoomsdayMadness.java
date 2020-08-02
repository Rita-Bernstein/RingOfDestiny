package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.DoomsdayMadnessAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DoomsdayMadness extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("DoomsdayMadness");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/20.png");
    private static final int COST = 0;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;



    public DoomsdayMadness() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
        this.secondaryM = this.baseSecondaryM = 3;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new LoseHPAction(p,p,this.secondaryM));
        addToBot(new DrawCardAction(this.magicNumber, new DoomsdayMadnessAction(this.magicNumber,3)));
    }

    public AbstractCard makeCopy() {
        return new DoomsdayMadness();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
