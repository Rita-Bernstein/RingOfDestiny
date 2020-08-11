package RingOfDestiny.relics;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.unique.IncreaseMaxHpAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class TwinWings extends CustomRelic {
    public static final String ID = RingOfDestiny.makeID("TwinWings");
    private static String imgName = "156.png";
    private static Texture texture = new Texture(RingOfDestiny.assetPath("img/relics/")+ imgName);
    private static Texture outline = new Texture(RingOfDestiny.assetPath("img/relics/outline/")+ imgName);

    public TwinWings() {
        super(ID, texture,outline, RelicTier.STARTER, CustomRelic.LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    @Override
    public void onVictory() {
        if(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDark.get(AbstractDungeon.overlayMenu.energyPanel)){
            AbstractDungeon.player.increaseMaxHp(1, true);
        }else {
            AbstractDungeon.player.heal(3);
        }
    }

    public CustomRelic makeCopy() {
        return new TwinWings();
    }
}