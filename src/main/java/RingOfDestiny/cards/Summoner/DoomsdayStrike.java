package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractSummonerCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DoomsdayStrike extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("DoomsdayStrike");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/26.png");
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public DoomsdayStrike() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = 7;
        this.magicNumber = this.baseMagicNumber = 1;
        this.tags.add(CardTags.STRIKE);
        this.cardsToPreview = new DoomsdayMeteorite();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

        AbstractCard s = (new DoomsdayMeteorite()).makeCopy();
        if(upgraded) s.upgrade();
        addToBot(new MakeTempCardInDrawPileAction(s, this.magicNumber,true,true));
    }

    public AbstractCard makeCopy() {
        return new DoomsdayStrike();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
       }
    }
}