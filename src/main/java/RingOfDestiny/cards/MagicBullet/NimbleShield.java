package RingOfDestiny.cards.MagicBullet;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractRingCard;
import RingOfDestiny.patches.CardColorEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class NimbleShield extends AbstractRingCard {
    public static final String ID = RingOfDestiny.makeID("NimbleShield");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = RingOfDestiny.assetPath("img/cards/MagicBullet/22.png");
    private static final int COST = 2;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColorEnum.MagicBullet_LIME;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;


    public NimbleShield() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = 13;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
    }

    public AbstractCard makeCopy() {
        return new NimbleShield();
    }


    @Override
    protected void applyPowersToBlock() {
        AbstractPower dex = AbstractDungeon.player.getPower(DexterityPower.POWER_ID);
        if (dex != null) {
            dex.amount *= this.magicNumber;
        }
        super.applyPowersToBlock();
        if (dex != null) {
            dex.amount /= this.magicNumber;
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(2);
            upgradeMagicNumber(1);
        }
    }

}
