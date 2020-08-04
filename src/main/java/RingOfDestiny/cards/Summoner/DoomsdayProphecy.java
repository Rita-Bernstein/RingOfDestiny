package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.UseSoulStoneAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CustomTagsEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DoomsdayProphecy extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("DoomsdayProphecy");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/51.png");
    private static final int COST = -2;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;



    public DoomsdayProphecy() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 8;
        this.tags.add(CustomTagsEnum.Soul_Stone);
        this.cardsToPreview = new DoomsdayMeteorite();
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new UseSoulStoneAction(1));
        AbstractCard s = (new DoomsdayMeteorite()).makeCopy();
        if(upgraded) s.upgrade();
        addToBot(new MakeTempCardInDrawPileAction(s, AbstractDungeon.cardRandomRng.random(5,this.magicNumber),true,true));
    }

    public AbstractCard makeCopy() {
        return new DoomsdayProphecy();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
