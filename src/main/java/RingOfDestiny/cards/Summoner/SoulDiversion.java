package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.AddSoulStoneAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SoulDiversion extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("SoulDiversion");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/44.png");
    private static final int COST = 3;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;



    public SoulDiversion() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
        this.tags.add(CardTags.STARTER_DEFEND);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new AddSoulStoneAction(this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new SoulDiversion();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(2);
        }
    }

}
