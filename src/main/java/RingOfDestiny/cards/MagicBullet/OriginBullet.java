package RingOfDestiny.cards.MagicBullet;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractRingCard;
import RingOfDestiny.patches.CardColorEnum;
import RingOfDestiny.patches.CustomTagsEnum;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

public class OriginBullet extends AbstractRingCard {
    public static final String ID = RingOfDestiny.makeID("OriginBullet");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = RingOfDestiny.assetPath("img/cards/MagicBullet/50.png");
    private static final int COST = 0;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = CardColorEnum.MagicBullet_LIME;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public OriginBullet() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 4;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    	int num = 1;
    	if(AbstractDungeon.player.hasPower(DexterityPower.POWER_ID))
    		num += (int)Math.floor(AbstractDungeon.player.getPower(DexterityPower.POWER_ID).amount/ (float)this.magicNumber);

    	for(int i = 0;i < num;i++){
            addToBot(new DamageAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn),AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
    }

    public AbstractCard makeCopy() {
        return new OriginBullet();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(1);
        }
    }
}