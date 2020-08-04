package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.UseSoulStoneAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CustomTagsEnum;
import RingOfDestiny.powers.Summoner.SummonOfDoomsdayPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SummonOfDoomsday extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("SummonOfDoomsday");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/55.png");
    private static final int COST = -2;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;



    public SummonOfDoomsday() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
        this.tags.add(CustomTagsEnum.Soul_Stone);
        this.isDestructive = true;
        this.exhaust = false;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new UseSoulStoneAction(1));
       addToBot(new ApplyPowerAction(p,p,new SummonOfDoomsdayPower(p,this.magicNumber),this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new SummonOfDoomsday();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = true;
            this.isDestructive = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
