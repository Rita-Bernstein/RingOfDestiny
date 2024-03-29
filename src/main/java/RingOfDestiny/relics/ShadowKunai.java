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
    private static String imgName = "154.png";
    private static Texture texture = new Texture(RingOfDestiny.assetPath("img/relics/")+ imgName);
    private static Texture outline = new Texture(RingOfDestiny.assetPath("img/relics/outline/")+ imgName);

    public ShadowKunai() {
        super(ID, texture,outline, RelicTier.STARTER, CustomRelic.LandingSound.FLAT);
        this.counter = 0;
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
            int ten = this.counter / 10;
            addToBot(new HealAction(AbstractDungeon.player, null, ten));
            this.counter = this.counter % 10;
        }
    }

    public CustomRelic makeCopy() {
        return new ShadowKunai();
    }
}