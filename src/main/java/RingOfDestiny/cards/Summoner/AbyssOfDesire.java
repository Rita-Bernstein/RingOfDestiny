package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.UseSoulStoneAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CustomTagsEnum;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AbyssOfDesire extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("AbyssOfDesire");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/54.png");
    private static final int COST = -2;
    public static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;


    public AbyssOfDesire() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = 0;
        this.tags.add(CustomTagsEnum.Soul_Stone);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new UseSoulStoneAction(1));
        this.baseDamage = p.currentBlock;
        calculateCardDamage(m);
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        if(!upgraded){
            this.rawDescription = DESCRIPTION;
        }else {
            this.rawDescription = UPGRADE_DESCRIPTION;
        }

        initializeDescription();

        if (!upgraded){
            addToBot(new RemoveAllBlockAction(p, p));
        }
    }

    public void applyPowers() {
        this.baseDamage = AbstractDungeon.player.currentBlock;
        super.applyPowers();

        if(!upgraded){
            this.rawDescription = DESCRIPTION;
        }else {
            this.rawDescription = UPGRADE_DESCRIPTION;
        }
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }


    public void onMoveToDiscard() {
        if(!upgraded){
            this.rawDescription = DESCRIPTION;
        }else {
            this.rawDescription = UPGRADE_DESCRIPTION;
        }
        initializeDescription();
    }


    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if(!upgraded){
            this.rawDescription = DESCRIPTION;
        }else {
            this.rawDescription = UPGRADE_DESCRIPTION;
        }
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @SpireOverride
    protected void renderEnergy(SpriteBatch sb){
        soulStoneRenderHelper(sb, this.soulStoneOrbRenderColor, this.soulStoneOrb, this.current_x, this.current_y);
//        SpireSuper.call(sb);
    }

    public AbstractCard makeCopy() {
        return new AbyssOfDesire();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
