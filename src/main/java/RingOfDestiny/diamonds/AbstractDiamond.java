package RingOfDestiny.diamonds;

import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.vfx.DiamondFireEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;


public abstract class AbstractDiamond {
    public float cX = 0.0F;
    public float cY = 0.0F;
    protected float current_x = 0.0f;
    protected float current_y = 0.0f;
    protected Color c;
    protected Color particleColor;
    protected float angle;
    protected float scale;
    protected boolean isSocket;
    protected float particleTimer;


    protected Texture slot = ImageMaster.loadImage("RingOfDestiny/img/diamonds/lightbg.png");
    protected Texture slot2 = ImageMaster.loadImage("RingOfDestiny/img/diamonds/light.png");

    public AbstractDiamond() {
        this.c = Settings.CREAM_COLOR.cpy();
        this.isSocket = false;
        this.particleTimer = 0.12f;
    }

    public void onEvoke() {
        this.isSocket = false;
    }

    public void onSocket() {
        this.isSocket = true;
    }



    public static void addDiamond() {
        for (AbstractDiamond di : EnergyPanelRenderPatches.PatchEnergyPanelField.diamonds.get(AbstractDungeon.overlayMenu.energyPanel)) {
            if (di.isSocket = false) {
                di.onSocket();
            }
        }
    }

    public static void evokeDiamond() {
        int count = 0;
        for (AbstractDiamond di : EnergyPanelRenderPatches.PatchEnergyPanelField.diamonds.get(AbstractDungeon.overlayMenu.energyPanel)) {
            if (di.isSocket = true) {
                count++;
            }
        }
        if (count > 0) {
            EnergyPanelRenderPatches.PatchEnergyPanelField.diamonds.get(AbstractDungeon.overlayMenu.energyPanel)[count - 1].onEvoke();
        }
    }

    public void update() {
        if (isSocket && !AbstractDungeon.player.isDead) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.12F;
                AbstractDungeon.topLevelEffectsQueue.add(new DiamondFireEffect(
                        this.cX + this.current_x + 42.0f * Settings.scale,
                        this.cY + this.current_y + 42.0f * Settings.scale,
                        this.scale * 0.55f,
                        this.particleColor));
            }
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.c);

        sb.draw(slot,
                this.cX + this.current_x,
                this.cY + this.current_y,
                27.0F, 27.0F, 54.0F, 54.0F, this.scale, this.scale, this.angle, 0, 0, 54, 54, false, false);

        if (this.isSocket) {
            sb.draw(slot2,
                    this.cX + this.current_x,
                    this.cY + this.current_y,
                    27.0F, 27.0F, 54.0F, 54.0F, this.scale, this.scale, this.angle, 0, 0, 54, 54, false, false);
        }
    }
}


