package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.ShadowFlower.ForceIntentAction;
import RingOfDestiny.cards.AbstractIntentChangingCard;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CardColorEnum;
import basemod.helpers.CardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;

public class DesireMastery extends AbstractIntentChangingCard {
    public static final String ID = RingOfDestiny.makeID("DesireMastery");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/10.png");
    private static final int COST = 2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColorEnum.Summoner_LIME;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public DesireMastery() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, AbstractIntentChangingCard.IntentTypes.ATTACK);
        this.magicNumber = this.baseMagicNumber = 3;
        this.tags.add(CardTags.HEALING);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HealAction(p,p,this.magicNumber));

        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                if (!monster.isDead && !monster.isDying) {
                    AbstractDungeon.actionManager.addToTop(new ForceIntentAction(p, monster, this.intentType));
                }
            }
        }
    }

    public AbstractCard makeCopy() {
        return new DesireMastery();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeMagicNumber(1);
        }
    }
}