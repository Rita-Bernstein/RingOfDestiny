package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.PowerOfGluttonyAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PowerOfGluttony extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("PowerOfGluttony");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/36.png");
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public PowerOfGluttony() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.misc = 6;
        this.magicNumber = this.baseMagicNumber = 2;
        this.baseDamage = this.misc;

        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new PowerOfGluttonyAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber, this.uuid, this));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new PowerOfGluttony();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.misc += 2;
            upgradeDamage(2);
            upgradeMagicNumber(1);
        }
    }
}