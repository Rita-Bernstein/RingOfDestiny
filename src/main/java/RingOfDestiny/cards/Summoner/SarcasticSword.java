package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.ShadowFlower.ForceIntentAction;
import RingOfDestiny.cards.AbstractIntentChangingCard;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CardColorEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SarcasticSword extends AbstractIntentChangingCard {
    public static final String ID = RingOfDestiny.makeID("SarcasticSword");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/03.png");
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = CardColorEnum.Summoner_LIME;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public SarcasticSword() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, AbstractIntentChangingCard.IntentTypes.ATTACK);
        this.baseDamage = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractDungeon.actionManager.addToTop(new ForceIntentAction(p, m, this.intentType));
    }

    public AbstractCard makeCopy() {
        return new SarcasticSword();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
        }
    }
}