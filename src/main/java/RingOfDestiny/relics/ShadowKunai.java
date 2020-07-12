package RingOfDestiny.relics;

import RingOfDestiny.RingOfDestiny;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ShadowKunai extends CustomRelic {
    public static final String ID = RingOfDestiny.makeID("ShadowKunai");
    private static Texture texture = new Texture(RingOfDestiny.assetPath("img/relics/ShadowKunai.png"));

    public ShadowKunai() {
        super(ID, texture, RelicTier.STARTER, CustomRelic.LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    @Override
    public void update() {
        super.update();
        if (this.counter >= 10) {
            this.flash();
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            int ten = (this.counter - this.counter % 10) / 10;
            addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, ten));
            this.counter = this.counter % 10;
        }
    }

    public CustomRelic makeCopy() {
        return new ShadowKunai();
    }
}