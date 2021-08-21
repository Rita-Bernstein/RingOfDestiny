package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.UseSoulStoneAction;
import RingOfDestiny.actions.unique.CustomWaitAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.character.AbstractRingCharacter;
import RingOfDestiny.character.Summoner;
import RingOfDestiny.patches.CustomTagsEnum;
import RingOfDestiny.powers.Summoner.SummonOfDoomsdayPower;
import RingOfDestiny.vfx.AbstractAtlasGameEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
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
        if(p instanceof AbstractRingCharacter){
            addToBot(new SFXAction(RingOfDestiny.makeID("monster_10044_skill")));
            addToBot(new VFXAction(new AbstractAtlasGameEffect("buff_morbiaoji", p.hb.cX - p.animX, p.hb.cY - p.animY,
                    250.0f, 235.0f, 1.5f * Settings.scale)));
        }

        addToBot(new ApplyPowerAction(p, p, new SummonOfDoomsdayPower(p, this.magicNumber), this.magicNumber));
        if (p instanceof Summoner){
            addToBot(new CustomWaitAction(0.08f));
            addToBot(new VFXAction(new AbstractAtlasGameEffect("job_6_common_skill_1",
                    p.hb.cX - p.animX + 18.0f * Settings.scale, p.hb.cY - p.animY + 70.0f * Settings.scale,
                    250.0f, 250.0f, 2.1f * Settings.scale)));
        }

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
