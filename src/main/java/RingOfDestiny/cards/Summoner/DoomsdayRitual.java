package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.DoomsdayRitualAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DoomsdayRitual extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("DoomsdayRitual");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/23.png");
    private static final int COST = 2;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;



    public DoomsdayRitual() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.cardsToPreview = new DoomsdayMeteorite();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new DoomsdayRitualAction(this.upgraded));
    }


    public AbstractCard makeCopy() {
        return new DoomsdayRitual();
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
