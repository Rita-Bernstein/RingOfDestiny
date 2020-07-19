package RingOfDestiny.cards.MagicBullet;


import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.patches.CardColorEnum;
import RingOfDestiny.powers.EtchPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class DeathLeakage extends CustomCard {
    public static final String ID = RingOfDestiny.makeID("DeathLeakage");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = RingOfDestiny.assetPath("img/cards/MagicBullet/23.png");
    private static final int COST = 0;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColorEnum.MagicBullet_LIME;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;


    public DeathLeakage() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new EtchPower(mo, this.magicNumber), this.magicNumber));
        }

    }


    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        for (AbstractCard c : p.hand.group) {
            if (c.type != AbstractCard.CardType.SKILL) {
                canUse = false;
                this.cantUseMessage = CardCrawlGame.languagePack.getUIString(RingOfDestiny.makeID("SkillCardOnly")).TEXT[0];
            }
        }

        return canUse;
    }


    public AbstractCard makeCopy() {
        return new DeathLeakage();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(1);
        }
    }
}
