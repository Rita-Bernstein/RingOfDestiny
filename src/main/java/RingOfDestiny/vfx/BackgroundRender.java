package RingOfDestiny.vfx;


import RingOfDestiny.RingOfDestiny;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;


public class BackgroundRender extends AbstractGameEffect {
    private float x;
    private static TextureAtlas.AtlasRegion img = null;
    private float y;
    private static Texture[] ZERO;
    private int time = 0;
    private float timer = 0;
    private float playSpeed = 0;
    private int gifLength = 0;
    private int gifWidth = 0;
    private int gifHeight = 0;


    public BackgroundRender(String id, int gifFrame, float fixX, float fixY, int imageWidth, int imageHeight, float gifScale, float Frameduration) {
        for (int i = 0; i < gifFrame; i++) {
            ZERO[i] = new Texture(String.format(RingOfDestiny.assetPath("img/stages/") + id + "/" + id + "_" + "%02d.png", i));
        }

        this.renderBehind = true;
        img = new TextureAtlas.AtlasRegion(ZERO[0], 0, 0, imageWidth, imageHeight);
        this.startingDuration = 2.0F;
        this.duration = this.startingDuration;
        this.scale = Settings.scale * gifScale;
        this.x = Settings.WIDTH * 0.5F - img.packedWidth / 2.0F + Settings.WIDTH * fixX;
        this.y = -img.packedHeight / 2.0F + Settings.HEIGHT * fixY;
        this.color = Color.WHITE.cpy();
        this.playSpeed = Frameduration;
        this.gifLength = gifFrame;
        this.gifWidth = imageWidth;
        this.gifHeight = imageHeight;

    }

    public void update() {
        timer += Gdx.graphics.getDeltaTime() * this.playSpeed;

        if (this.gifLength - 1 < 0) {
            time = 0;
        } else {
            time = (int) timer % (this.gifLength);
        }


        img = new TextureAtlas.AtlasRegion(ZERO[time], 0, 0, this.gifWidth, this.gifHeight);
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(img, this.x, this.y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale, this.scale, 0.0f);
    }

    public void dispose() {
    }
}

