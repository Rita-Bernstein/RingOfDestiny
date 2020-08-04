package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.AddSoulStoneAction;
import RingOfDestiny.actions.Summoner.UseSoulStoneAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CustomTagsEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ConqueringBlade extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("ConqueringBlade");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/50.png");
    private static final int COST = -2;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public ConqueringBlade() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);

        this.baseDamage = 18;
        this.magicNumber = this.baseMagicNumber = 8;
        this.tags.add(CustomTagsEnum.Soul_Stone);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new UseSoulStoneAction(1));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

        if(getCurrentSummonDamage() > this.magicNumber){
            addToBot(new AddSoulStoneAction(1));
        }
    }

    public AbstractCard makeCopy() {
        return new ConqueringBlade();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
            upgradeMagicNumber(-2);
        }
    }
}