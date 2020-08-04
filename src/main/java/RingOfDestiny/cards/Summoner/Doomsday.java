package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.UseSoulStoneAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CustomTagsEnum;
import RingOfDestiny.powers.Summoner.DoomsdayImprintPower;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

public class Doomsday extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("Doomsday");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/53.png");
    private static final int COST = -2;
    public static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;


    public Doomsday() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = 5;
        this.magicNumber = this.baseMagicNumber = 3;
        this.secondaryM = this.baseSecondaryM = 2;
        this.isMultiDamage = true;
        this.tags.add(CustomTagsEnum.Soul_Stone);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new UseSoulStoneAction(1));
        for(int i =0;i < this.magicNumber;i++){
            addToBot(new SFXAction("ATTACK_HEAVY"));
            addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
            addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        }
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                if (!monster.isDead && !monster.isDying) {
                    addToBot(new ApplyPowerAction(monster, p, new DoomsdayImprintPower(monster, this.secondaryM), this.secondaryM));
                }
            }
        }
    }



    public AbstractCard makeCopy() {
        return new Doomsday();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(1);
            upgradeSecondM(1);
        }
    }

}
