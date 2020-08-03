package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.powers.Summoner.AngerSwordPower;
import RingOfDestiny.powers.Summoner.UpgradedAngerSwordPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AngerSword extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("AngerSword");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/41.png");
    private static final int COST = 1;
    public static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;



    public AngerSword() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(upgraded){
            addToBot(new ApplyPowerAction(p,p,new UpgradedAngerSwordPower(p,this.magicNumber),this.magicNumber));
        }else {
            addToBot(new ApplyPowerAction(p,p,new AngerSwordPower(p,this.magicNumber),this.magicNumber));
        }

    }

    public AbstractCard makeCopy() {
        return new AngerSword();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
