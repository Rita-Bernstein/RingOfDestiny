package RingOfDestiny.cards.Colorless;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractRingCard;
import RingOfDestiny.patches.CardColorEnum;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;

public class NotRegret extends AbstractRingCard {
    public static final String ID = RingOfDestiny.makeID("NotRegret");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Colorless/good_instincts.png");
    private static final int COST = 0;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;


    public NotRegret() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = 5;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new BorderLongFlashEffect(Color.FIREBRICK, true)));
        addToBot(new VFXAction(p, new InflameEffect(p), 1.0F));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 999),999));

        AbstractDungeon.effectList.add(new RainingGoldEffect(1000, true));
        AbstractDungeon.effectsQueue.add(new SpotlightPlayerEffect());
        addToBot(new GainGoldAction(1000));


        addToBot(new VFXAction(new BorderFlashEffect(Color.CHARTREUSE, true)));
        addToBot(new VFXAction(p, new MiracleEffect(Color.CHARTREUSE, Color.LIME, "BLOCK_GAIN_1"), 1.0F));
        addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, 999), 999));

    }


    public AbstractCard makeCopy() {
        return new NotRegret();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }
}
