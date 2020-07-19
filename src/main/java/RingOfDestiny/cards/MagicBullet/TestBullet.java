package RingOfDestiny.cards.MagicBullet;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.MagicBullet.ChangeCostAction;
import RingOfDestiny.cards.AbstractRingCard;
import RingOfDestiny.patches.CardColorEnum;
import RingOfDestiny.patches.CustomTagsEnum;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TestBullet extends AbstractRingCard {
    public static final String ID = RingOfDestiny.makeID("TestBullet");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = RingOfDestiny.assetPath("img/cards/MagicBullet/46.png");
    private static final int COST = 0;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = CardColorEnum.MagicBullet_LIME;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public TestBullet() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 14;
        this.magicNumber = this.baseMagicNumber = 1;
        this.tags.add(CustomTagsEnum.MagicBullet);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new ChangeCostAction(this.uuid, this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new TestBullet();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(4);
        }
    }
}