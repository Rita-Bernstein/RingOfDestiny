package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractSummonerCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DoomsdayEcho extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("DoomsdayEcho");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/21.png");
    private static final int COST = 2;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;



    public DoomsdayEcho() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
       this.magicNumber = this.baseMagicNumber = 2;
       this.cardsToPreview = new DoomsdayMeteorite();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard s = (new DoomsdayMeteorite()).makeCopy();
        if(upgraded) s.upgrade();
        addToBot(new MakeTempCardInHandAction(s, this.magicNumber));

        if(hasDoomsdayMeteorite()){
            addToBot(new DrawCardAction(p,1));
        }
    }

    private boolean hasDoomsdayMeteorite(){
        boolean has = false;
        for(AbstractCard card : AbstractDungeon.player.drawPile.group){
            if(card.cardID.equals(DoomsdayMeteorite.ID)){
                has = true;
                break;
            }
        }


        return has;
    }

    public AbstractCard makeCopy() {
        return new DoomsdayEcho();
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
