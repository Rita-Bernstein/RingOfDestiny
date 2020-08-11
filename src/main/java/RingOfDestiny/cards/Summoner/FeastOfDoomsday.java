package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.FeastOfDoomsdayAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.powers.Summoner.DoomsdayImprintPower;
import RingOfDestiny.powers.Summoner.ExtractPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FeastOfDoomsday extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("FeastOfDoomsday");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/25.png");
    private static final int COST = 1;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;


    public FeastOfDoomsday() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseBlock = 6;
        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                if (!monster.isDead && !monster.isDying) {
                    addToBot(new ApplyPowerAction(monster, p, new DoomsdayImprintPower(monster, this.magicNumber), this.magicNumber));
                    addToBot(new FeastOfDoomsdayAction(monster));
                }
            }
        }


    }

    public AbstractCard makeCopy() {
        return new FeastOfDoomsday();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
