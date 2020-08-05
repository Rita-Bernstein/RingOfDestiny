package RingOfDestiny.modules;

import RingOfDestiny.RingOfDestiny;
import basemod.abstracts.CustomEnergyOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class EnergyOrbInherit extends CustomEnergyOrb {
    private static final float ORB_IMG_SCALE = 1.15F * Settings.scale;
    private static final Texture inheritOrbCover = ImageMaster.loadImage("RingOfDestiny/img/ui/topPanel/Inherit/ballBg.png");
    private float angle5;
    private float angle4;
    private float angle3;
    private float angle2;

    public EnergyOrbInherit(String[] orbTexturePaths, String orbVfxPath) {
        super(orbTexturePaths, orbVfxPath, null);
    }

    @Override
    public void updateOrb(int orbCount) {
        if (orbCount == 0) {
            this.angle5 += Gdx.graphics.getDeltaTime() * -5.0F;
            this.angle4 += Gdx.graphics.getDeltaTime() * 5.0F;
            this.angle3 += Gdx.graphics.getDeltaTime() * -8.0F;
            this.angle2 += Gdx.graphics.getDeltaTime() * 8.0F;
        } else {
            this.angle5 += Gdx.graphics.getDeltaTime() * -20.0F;
            this.angle4 += Gdx.graphics.getDeltaTime() * 20.0F;
            this.angle3 += Gdx.graphics.getDeltaTime() * -40.0F;
            this.angle2 += Gdx.graphics.getDeltaTime() * 40.0F;
        }
    }


    @Override
    public void renderOrb(SpriteBatch sb, boolean enabled, float current_x, float current_y) {
        sb.setColor(Color.WHITE);

        sb.draw(this.baseLayer, current_x - 80.0F, current_y - 80.0F, 80.0F, 80.0F, 160.0F, 160.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, 0.0f, 0, 0, 160, 160, false, false);

        if (enabled) {
            sb.draw(this.energyLayers[0], current_x - 80.0F, current_y - 80.0F, 80.0F, 80.0F, 160.0F, 160.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, 0.0f, 0, 0, 160, 160, false, false);
            sb.draw(this.energyLayers[1], current_x - 80.0F, current_y - 80.0F, 80.0F, 80.0F, 160.0F, 160.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle2, 0, 0, 160, 160, false, false);
            sb.draw(this.energyLayers[2], current_x - 80.0F, current_y - 80.0F, 80.0F, 80.0F, 160.0F, 160.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle3, 0, 0, 160, 160, false, false);
            sb.draw(this.energyLayers[3], current_x - 80.0F, current_y - 80.0F, 80.0F, 80.0F, 160.0F, 160.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle4, 0, 0, 160, 160, false, false);
            sb.draw(this.energyLayers[4], current_x - 80.0F, current_y - 80.0F, 80.0F, 80.0F, 160.0F, 160.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle5, 0, 0, 160, 160, false, false);
        } else {
            sb.draw(this.noEnergyLayers[0], current_x - 80.0F, current_y - 80.0F, 80.0F, 80.0F, 160.0F, 160.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, 0.0f, 0, 0, 160, 160, false, false);
            sb.draw(this.noEnergyLayers[1], current_x - 80.0F, current_y - 80.0F, 80.0F, 80.0F, 160.0F, 160.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle2, 0, 0, 160, 160, false, false);
            sb.draw(this.noEnergyLayers[2], current_x - 80.0F, current_y - 80.0F, 80.0F, 80.0F, 160.0F, 160.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle3, 0, 0, 160, 160, false, false);
            sb.draw(this.noEnergyLayers[3], current_x - 80.0F, current_y - 80.0F, 80.0F, 80.0F, 160.0F, 160.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle4, 0, 0, 160, 160, false, false);
            sb.draw(this.noEnergyLayers[4], current_x - 80.0F, current_y - 80.0F, 80.0F, 80.0F, 160.0F, 160.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, this.angle5, 0, 0, 160, 160, false, false);
        }

        sb.draw(inheritOrbCover, current_x - 80.0F, current_y - 80.0F, 80.0F, 80.0F, 160.0F, 160.0F, ORB_IMG_SCALE, ORB_IMG_SCALE, 0.0f, 0, 0, 160, 160, false, false);

    }
}
