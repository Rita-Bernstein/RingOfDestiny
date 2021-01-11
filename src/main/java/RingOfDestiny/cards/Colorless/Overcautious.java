package RingOfDestiny.cards.Colorless;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractRingCard;
import RingOfDestiny.patches.CardColorEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoDrawPower;


public class Overcautious extends AbstractRingCard {

    public static final String ID = RingOfDestiny.makeID("Overcautious");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Colorless/Overcautious.png");
    private static final int COST = -2;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.STATUS;
    private static final CardColor COLOR = CardColorEnum.Status_LIME;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;


    private static final int actualDamage = 4;

    public Overcautious() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.isEthereal = true;

        setBackgroundTexture("RingOfDestiny/img/cardui/Colorless/512/bg_skill_lime.png","RingOfDestiny/img/cardui/Colorless/1024/bg_skill_lime.png");

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
    }


    public void triggerOnExhaust() {
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, false));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Overcautious(), 1, true, true, false, Settings.WIDTH * 0.2F, Settings.HEIGHT / 2.0F));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, this.actualDamage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
    }

    public void triggerOnManualDiscard() {
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, false));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Overcautious(), 1, true, true, false, Settings.WIDTH * 0.2F, Settings.HEIGHT / 2.0F));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, this.actualDamage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
    }


    public AbstractCard makeCopy() {
        return new Overcautious();
    }

    public void upgrade() {
    }
}
