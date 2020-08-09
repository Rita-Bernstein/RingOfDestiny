package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.MaliciousSuppressAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.powers.Summoner.ExtractPower;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

public class MaliciousSuppress extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("MaliciousSuppress");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/15.png");
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int realDamage = 3;

    public MaliciousSuppress() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);

        this.baseDamage = 5;
        this.secondaryM = this.baseSecondaryM = 3;
        this.isSingleAndAOE = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (m != null && m.getIntentBaseDmg() >= 0) {
            addToBot(new MaliciousSuppressAction(this.isSingleAndAOEDamage,this.damageTypeForTurn));

            if (upgraded) {
                if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                    for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                        if (!monster.isDead && !monster.isDying) {
                            addToBot(new ApplyPowerAction(monster, p, new ExtractPower(monster, p, 1), 1));
                        }
                    }
                }
            }

        }
    }

    public int calculateDamage(AbstractMonster mo, AbstractPlayer player, int baseDamage, AbstractCard card) {
        if (mo != null) {
            float tmp = baseDamage;

            for (AbstractRelic r : player.relics) {
                tmp = r.atDamageModify(tmp, card);
            }

            for (AbstractPower p : player.powers) {
                tmp = p.atDamageGive(tmp, this.damageTypeForTurn, card);
            }

            tmp = player.stance.atDamageGive(tmp, this.damageTypeForTurn, card);


            for (AbstractPower p : mo.powers) {
                tmp = p.atDamageReceive(tmp, this.damageTypeForTurn, card);
            }

            for (AbstractPower p : player.powers) {
                tmp = p.atDamageFinalGive(tmp, this.damageTypeForTurn, card);
            }

            for (AbstractPower p : mo.powers) {
                tmp = p.atDamageFinalReceive(tmp, this.damageTypeForTurn, card);
            }

            if (tmp < 0.0F) {
                tmp = 0.0F;
            }

            return (int) MathUtils.floor(tmp);
        } else {
            return 0;
        }
    }

    public AbstractCard makeCopy() {
        return new MaliciousSuppress();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
        }
    }
}