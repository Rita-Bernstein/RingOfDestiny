package RingOfDestiny.relics;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.AddDiamondAction;
import RingOfDestiny.actions.Summoner.AddSoulStoneAction;
import RingOfDestiny.summon.AbstractSummon;
import RingOfDestiny.summon.Demon;
import RingOfDestiny.summon.Succubus;
import RingOfDestiny.summon.Vampire;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class RingOfSoul extends AbstractRingRelic {
    public static final String ID = RingOfDestiny.makeID("RingOfSoul");
    private static String imgName = "161.png";
    private static Texture texture = new Texture(RingOfDestiny.assetPath("img/relics/") + imgName);
    private static Texture outline = new Texture(RingOfDestiny.assetPath("img/relics/outline/") + imgName);

    public RingOfSoul() {
        super(ID, texture, outline, RelicTier.COMMON, CustomRelic.LandingSound.FLAT);

    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] ;
    }


    public void atBattleStart() {
        this.grayscale = false;
    }

    @Override
    public void onSacrifice(AbstractSummon summon) {
        flash();
        this.grayscale = true;
        addToBot(new DrawCardAction(2));
    }


    public CustomRelic makeCopy() {
        return new RingOfSoul();
    }
}