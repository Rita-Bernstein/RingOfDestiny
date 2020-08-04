package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.DoomsdayBladeAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

public class DoomsdayBlade extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("DoomsdayBlade");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/24.png");
    private static final int COST = 2;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public DoomsdayBlade() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);

        this.baseDamage = 16;
        this.magicNumber = this.baseMagicNumber = 4;
        this.cardsToPreview = new DoomsdayMeteorite();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
            addToBot(new WaitAction(0.8F));
        }

        addToBot(new DoomsdayBladeAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.upgraded));
    }

    public AbstractCard makeCopy() {
        return new DoomsdayBlade();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
            this.upgradeMagicNumber(1);
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}