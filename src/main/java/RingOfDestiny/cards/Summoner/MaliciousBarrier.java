package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.powers.Summoner.MaliciousBarrierPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MaliciousBarrier extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("MaliciousBarrier");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/18.png");
    private static final int COST = 2;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;



    public MaliciousBarrier() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseBlock = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new GainBlockAction(p, p, this.block));
        addToBot(new ApplyPowerAction(p,p,new MaliciousBarrierPower(p,this.magicNumber),this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new MaliciousBarrier();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(2);
        }
    }

}
