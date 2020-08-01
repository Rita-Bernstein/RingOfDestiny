package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CustomTagsEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PowerOfGreed extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("PowerOfGreed");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/34.png");
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public PowerOfGreed() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = 7;
        this.magicNumber = this.baseMagicNumber = 1;
        this.secondaryM = this.baseSecondaryM = 2;
        this.tags.add(CustomTagsEnum.Original_Sin);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }


    public static int countCards() {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cardID.equals(PowerOfGreed.ID)) {
                count++;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.cardID.equals(PowerOfGreed.ID)) {
                count++;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.cardID.equals(PowerOfGreed.ID)) {
                count++;
            }
        }
        return count;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        int amount = this.secondaryM;
        if(amount < 1)amount = 1;
        this.baseDamage +=  (countCards() / amount) * this.magicNumber;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }


    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        int amount = this.secondaryM;
        if(amount < 1)amount = 1;
        this.baseDamage +=  (countCards() / amount) * this.magicNumber;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }



    public AbstractCard makeCopy() {
        return new PowerOfGreed();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeSecondM(-1);
        }
    }
}