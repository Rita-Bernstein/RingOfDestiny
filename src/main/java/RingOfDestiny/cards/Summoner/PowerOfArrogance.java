package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.PowerOfArroganceAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CustomTagsEnum;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PowerOfArrogance extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("PowerOfArrogance");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/60.png");
    private static final int COST = 2;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;



    public PowerOfArrogance() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 2;
        this.tags.add(CustomTagsEnum.Original_Sin);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new DrawCardAction(this.magicNumber,new PowerOfArroganceAction(this.upgraded)));
    }

    public AbstractCard makeCopy() {
        return new PowerOfArrogance();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
