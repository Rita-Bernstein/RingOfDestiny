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
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.HashMap;
import java.util.Map;


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
    protected int index = 0;


    protected Texture slot = ImageMaster.loadImage("RingOfDestiny/img/diamonds/lightbg.png");
    protected Texture slot2 = ImageMaster.loadImage("RingOfDestiny/img/diamonds/light.png");

    private static Map<Integer, Float> diamondNewPos_X;
    private static Map<Integer, Float> diamondNewPos_Y;
    private static Map<Integer, Float> diamondNewAngle;

    static {
        diamondNewPos_X = new HashMap<>();
        diamondNewPos_Y = new HashMap<>();
        diamondNewAngle = new HashMap<>();

        diamondNewPos_X.put(0, -62.0f);
        diamondNewPos_X.put(1, -66.0f);
        diamondNewPos_X.put(2, -60.0f);
        diamondNewPos_X.put(3, -48.0f);
        diamondNewPos_X.put(4, -27.0f);
        diamondNewPos_X.put(5,  27.0f);
        diamondNewPos_X.put(6,  48.0f);
        diamondNewPos_X.put(7,  60.0f);
        diamondNewPos_X.put(8,  66.0f);
        diamondNewPos_X.put(9,  62.0f);

        diamondNewPos_Y.put(0,-22.0f);
        diamondNewPos_Y.put(1, 5.0f);
        diamondNewPos_Y.put(2, 28.0f);
        diamondNewPos_Y.put(3, 48.0f);
        diamondNewPos_Y.put(4, 63.0f);
        diamondNewPos_Y.put(5, 63.0f);
        diamondNewPos_Y.put(6, 48.0f);
        diamondNewPos_Y.put(7, 28.0f);
        diamondNewPos_Y.put(8, 5.0f);
        diamondNewPos_Y.put(9,-22.0f);

        diamondNewAngle.put(0, 0.0f);
        diamondNewAngle.put(1, 13.0f);
        diamondNewAngle.put(2, 26.0f);
        diamondNewAngle.put(3, 39.0f);
        diamondNewAngle.put(4, 52.0f);
        diamondNewAngle.put(5, -22.0f);
        diamondNewAngle.put(6,  9.0f);
        diamondNewAngle.put(7,  4.0f);
        diamondNewAngle.put(8,  17.0f);
        diamondNewAngle.put(9,  30.0f);

    }

    public AbstractDiamond() {
        this.c = Settings.CREAM_COLOR.cpy();
        this.isSocket = false;
        this.particleTimer = 0.12f;
    }

    public void onEvoke() {
        this.isSocket = false;
    }

    public void onSocket() {
        System.out.println("最后了");
        this.isSocket = true;
    }



    public static void addDiamond() {
        System.out.println("addDiamond运行了");
        for (AbstractDiamond di : EnergyPanelRenderPatches.PatchEnergyPanelField.diamonds.get(AbstractDungeon.overlayMenu.energyPanel)) {
            if (!di.isSocket) {
                di.onSocket();
                break;
            }
        }
    }

    public static void evokeDiamond() {
        int count = 0;
        for (AbstractDiamond di : EnergyPanelRenderPatches.PatchEnergyPanelField.diamonds.get(AbstractDungeon.overlayMenu.energyPanel)) {
            if (di.isSocket) {
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
                        this.cX + this.current_x ,//this.cX + this.current_x + 42.0f * Settings.scale,
                        this.cY + this.current_y ,//this.cY + this.current_y + 42.0f * Settings.scale,
                        this.scale * 0.55f,
                        this.particleColor));
            }
        }

        if(EnergyPanelRenderPatches.PatchEnergyPanelField.canUseSoulStone.get(AbstractDungeon.overlayMenu.energyPanel)){
            this.current_x = diamondNewPos_X.get(this.index) * Settings.scale;
            this.current_y = diamondNewPos_Y.get(this.index) * Settings.scale;
            this.scale = 0.5f * Settings.scale;
            this.angle = diamondNewAngle.get(this.index);
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.c);



        sb.draw(slot,
                this.cX + this.current_x - 27.0f,
                this.cY + this.current_y - 27.0f,
                27.0F, 27.0F, 54.0F, 54.0F, this.scale, this.scale, this.angle, 0, 0, 54, 54, false, false);

        if (this.isSocket) {
            sb.draw(slot2,
                    this.cX + this.current_x - 27.0f,
                    this.cY + this.current_y - 27.0f,
                    27.0F, 27.0F, 54.0F, 54.0F, this.scale, this.scale, this.angle, 0, 0, 54, 54, false, false);
        }
    }
}


