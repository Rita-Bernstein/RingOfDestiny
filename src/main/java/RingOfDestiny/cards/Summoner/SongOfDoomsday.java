package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.cards.MagicBullet.DoomsdayMark;
import RingOfDestiny.powers.DoomsdayMarkPower;
import RingOfDestiny.powers.Summoner.DoomsdayImprintPower;
import RingOfDestiny.powers.Summoner.ExtractPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SongOfDoomsday extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("SongOfDoomsday");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/22.png");
    private static final int COST = 2;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;



    public SongOfDoomsday() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 3;
        this.secondaryM = this.baseSecondaryM = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                if (!monster.isDead && !monster.isDying) {
                    addToBot(new ApplyPowerAction(monster, p, new DoomsdayImprintPower(monster,this.magicNumber),this.magicNumber));
                }
            }
        }

        addToBot(new DrawCardAction(this.secondaryM));
    }

    public AbstractCard makeCopy() {
        return new SongOfDoomsday();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

}
