package RingOfDestiny.cards.Colorless;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractRingCard;
import RingOfDestiny.patches.CardColorEnum;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoDrawPower;

public class Blockade extends AbstractRingCard {
    public static final String ID = RingOfDestiny.makeID("Blockade");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Colorless/Blockade.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.STATUS;
    private static final CardColor COLOR = CardColorEnum.Status_LIME;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;


    public Blockade() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        setBackgroundTexture("RingOfDestiny/img/cardui/Colorless/512/bg_skill_lime.png","RingOfDestiny/img/cardui/Colorless/1024/bg_skill_lime.png");
        this.exhaust = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void triggerWhenDrawn() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new NoDrawPower(AbstractDungeon.player)));
    }

    public AbstractCard makeCopy() {
        return new Blockade();
    }

    public void upgrade() {
    }
}
