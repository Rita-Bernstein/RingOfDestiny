package RingOfDestiny.vfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.vfx.GhostlyFireEffect;


public class DiamondFireEffect extends GhostlyFireEffect {
    private TextureAtlas.AtlasRegion img;
    private float x;
    private float y;
    private float vX;
    private float vY;
    private static final float DUR = 1.0F;

    public DiamondFireEffect(float x, float y, float scale,Color color) {
        super(x, y);
        this.vX *= scale;
        this.vY *= scale;
        this.scale *= scale;

        this.color = color;
        this.color.a = 0.0F;
    }


}


