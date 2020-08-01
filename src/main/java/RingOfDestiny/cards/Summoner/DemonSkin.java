package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.powers.Summoner.DemonSkinPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DemonSkin extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("DemonSkin");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/08.png");
    private static final int COST = 2;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;



    public DemonSkin() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseBlock = 7;
        this.magicNumber = this.baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new ApplyPowerAction(p,p,new DemonSkinPower(p,this.magicNumber)));
    }

    public AbstractCard makeCopy() {
        return new DemonSkin();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

}
