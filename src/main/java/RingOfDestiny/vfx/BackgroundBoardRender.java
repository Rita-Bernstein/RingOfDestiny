package RingOfDestiny.vfx;


import RingOfDestiny.RingOfDestiny;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;


public class BackgroundBoardRender extends AbstractGameEffect {
    private static TextureAtlas.AtlasRegion img = null;
    private float x1, x2,y1, y2;


    public BackgroundBoardRender(float X1, float Y1, float X2, float Y2, float Scale) {
        img = new TextureAtlas.AtlasRegion(new Texture(RingOfDestiny.assetPath("img/stages/UI/Bottom.png")), 0, 0, 1280, 144);

        this.renderBehind = true;

        this.startingDuration = 2.0F;
        this.duration = this.startingDuration;
        this.scale = Settings.scale * Scale;
        this.x1 = Settings.WIDTH * 0.5F - img.packedWidth / 2.0F + Settings.WIDTH * X1;
        this.y1 = -img.packedHeight + Settings.HEIGHT * Y1;
        this.x2 = Settings.WIDTH * 0.5F - img.packedWidth / 2.0F + Settings.WIDTH * X2;
        this.y2 = -img.packedHeight / 2.0F + Settings.HEIGHT * Y2;
        this.color = Color.WHITE.cpy();

    }

    public void update() {
        super.update();
        this.isDone = false;
    }


    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        sb.draw(img, this.x1, this.y1, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale, -this.scale, 0.0f);
        sb.draw(img, this.x2, this.y2, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale, this.scale, 0.0f);
    }

    public void dispose() {
    }
}

