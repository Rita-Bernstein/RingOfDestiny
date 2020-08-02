package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.cards.Purchemist.Repeat;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MemoryBlade extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("MemoryBlade");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/16.png");
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public MemoryBlade() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = 1;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int getDamage = 1;
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 1) {

            int size = AbstractDungeon.actionManager.cardsPlayedThisCombat.size();
            for(int i = size -1;i >= 0 ; i --){
                if(AbstractDungeon.actionManager.cardsPlayedThisCombat.get(i).type == CardType.ATTACK){
                    getDamage =  AbstractDungeon.actionManager.cardsPlayedThisCombat.get(i).baseDamage;
                    break;
                }
            }
        }

        if(getDamage <= 0 ){
            getDamage = 1;
        }
        baseDamage = getDamage;
        super.calculateCardDamage(mo);
    }

    @Override
    public void onMoveToDiscard() {
        this.damage = this.baseDamage = 1;
        super.onMoveToDiscard();
    }

    public AbstractCard makeCopy() {
        return new MemoryBlade();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}