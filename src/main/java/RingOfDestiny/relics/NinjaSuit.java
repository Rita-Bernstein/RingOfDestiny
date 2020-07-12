package RingOfDestiny.relics;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.ShadowFlower.WristBlade;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class NinjaSuit extends AbstractRingRelic {
    public static final String ID = RingOfDestiny.makeID("NinjaSuit");
    private static Texture texture = new Texture(RingOfDestiny.assetPath("img/relics/NinjaSuit.png"));

    public NinjaSuit() {
        super(ID, texture, RelicTier.RARE, CustomRelic.LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onCreateShadow() {
        addToBot(new MakeTempCardInHandAction(new WristBlade(), 2));
    }

    public CustomRelic makeCopy() {
        return new NinjaSuit();
    }
}