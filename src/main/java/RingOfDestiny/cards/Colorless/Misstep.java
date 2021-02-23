package RingOfDestiny.cards.Colorless;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.patches.CardColorEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DiscardAction;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Misstep extends CustomCard {
    public static final String ID = RingOfDestiny.makeID("Misstep");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Colorless/Misstep.png");
    private static final int COST = -2;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.STATUS;
    private static final CardColor COLOR = CardColorEnum.Status_LIME;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;


    public Misstep() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        setBackgroundTexture("RingOfDestiny/img/cardui/Colorless/512/bg_skill_lime.png","RingOfDestiny/img/cardui/Colorless/1024/bg_skill_lime.png");

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
    }


    public void triggerWhenDrawn() {
        addToBot(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, 1, true));
    }

    public AbstractCard makeCopy() {
        return new Misstep();
    }

    public void upgrade() {
    }
}
