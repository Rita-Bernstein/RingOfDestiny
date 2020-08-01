package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.MaliciousBladeAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MaliciousBlade extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("MaliciousBlade");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/12.png");
    private static final int COST = 2;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public MaliciousBlade(int upgrades) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = 6;
        this.magicNumber = this.baseMagicNumber = 2;
        this.timesUpgraded = upgrades;
    }

    public MaliciousBlade() {
        this(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);

        addToBot(new MaliciousBladeAction(randomMonster, 3, this.magicNumber, this.damage, this.damageTypeForTurn));
    }

    public AbstractCard makeCopy() {
        return new MaliciousBlade();
    }

    public boolean canUpgrade() {
        return true;
    }

    public void upgrade() {
        upgradeMagicNumber(1);
        this.timesUpgraded++;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        initializeTitle();
    }

}