package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.SummonAttackAllEnemyAction;
import RingOfDestiny.actions.Summoner.UseSoulStoneAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CustomTagsEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DevilKing extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("DevilKing");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/57.png");
    private static final int COST = -2;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;



    public DevilKing() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 3;
        this.tags.add(CustomTagsEnum.Soul_Stone);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new UseSoulStoneAction(1));
       addToBot(new SummonAttackAllEnemyAction(this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new DevilKing();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

}
