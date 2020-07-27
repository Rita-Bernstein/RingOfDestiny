package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.GoldFingerAction;
import RingOfDestiny.cards.AbstractPurchemistCard;
import basemod.devcommands.gold.Gold;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GoldFinger extends AbstractPurchemistCard {
    public static final String ID = RingOfDestiny.makeID("GoldFinger");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/15.png");
    private static final int COST = 2;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public GoldFinger() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);

        this.baseDamage = 15;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GoldFingerAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new GoldFinger();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }
}