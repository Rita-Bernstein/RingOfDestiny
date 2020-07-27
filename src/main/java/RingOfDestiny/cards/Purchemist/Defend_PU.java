package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractPurchemistCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Defend_PU extends AbstractPurchemistCard {
    public static final String ID = RingOfDestiny.makeID("Defend_PU");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/00.png");
    private static final int COST = 1;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;



    public Defend_PU() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseBlock = 6;
        this.tags.add(CardTags.STARTER_DEFEND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new GainBlockAction(p, p, this.block));
    }

    public AbstractCard makeCopy() {
        return new Defend_PU();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

}
