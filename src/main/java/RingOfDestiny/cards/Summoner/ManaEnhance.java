package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.powers.Summoner.ManaEnhancePower;
import RingOfDestiny.powers.Summoner.UpgradedManaEnhancePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ManaEnhance extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("ManaEnhance");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/13.png");
    private static final int COST = 0;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;


    public ManaEnhance() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
        this.secondaryM = this.baseSecondaryM = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseHPAction(p,p,this.magicNumber));
        if (upgraded) {
            addToBot(new ApplyPowerAction(p, p, new UpgradedManaEnhancePower(p, this.magicNumber), this.magicNumber));
        } else {
            addToBot(new ApplyPowerAction(p, p, new ManaEnhancePower(p, this.magicNumber), this.magicNumber));
        }

    }

    public AbstractCard makeCopy() {
        return new ManaEnhance();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
