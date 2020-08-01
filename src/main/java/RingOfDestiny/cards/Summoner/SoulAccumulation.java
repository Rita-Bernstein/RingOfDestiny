package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.SoulAccumulationAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SoulAccumulation extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("SoulAccumulation");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/59.png");
    private static final int COST = 2;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;



    public SoulAccumulation() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(this.magicNumber, new SoulAccumulationAction()));
    }

    public AbstractCard makeCopy() {
        return new SoulAccumulation();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(-1);
            upgradeBaseCost(1);
        }
    }

}
