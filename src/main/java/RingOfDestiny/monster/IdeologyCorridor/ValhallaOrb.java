package RingOfDestiny.monster.IdeologyCorridor;

import RingOfDestiny.vfx.AbstractAtlasGameEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class ValhallaOrb {
    public float x, y, scale;
    public int index;
    private Texture main;

    public boolean active = false;
    private AbstractAtlasGameEffect vfxFg = new AbstractAtlasGameEffect("monster_10002_skill_1",
            0.0f, 0.0f, 100.0f, 100.0f, 0.8f * Settings.scale, true);

    private AbstractAtlasGameEffect vfxBg = new AbstractAtlasGameEffect("monster_10002_skill_2",
            0.0f, 0.0f, 100.0f, 100.0f, 0.8f * Settings.scale, true);

    private AbstractAtlasGameEffect evokeEffect = new AbstractAtlasGameEffect("monster_10002_skill_1",
            0.0f, 0.0f, 150.0f, 150.0f, 0.8f * Settings.scale, true);


    private boolean evokeDone = true;
    private float evokeDelayTimer = 0.0f;

    private boolean hide = false;

    public ValhallaOrb(int index) {
        this.index = index;

        this.main = ImageMaster.loadImage("RingOfDestiny/monsters/IdeologyCorridor/ValhallaChronicles/orbs/orb_" + this.index + ".png");
    }

    public void evoke() {
        this.evokeDelayTimer = 0.16f * this.index;
        this.evokeDone = false;
        this.evokeEffect.isDone = false;
    }

    public void hide() {
        this.hide = true;
    }


    public void update() {
        this.vfxFg.xPosition = x;
        this.vfxFg.yPosition = y;
        vfxFg.update();

        this.vfxBg.xPosition = x;
        this.vfxBg.yPosition = y;
        vfxBg.update();

        if (evokeDelayTimer > 0)
            this.evokeDelayTimer -= Gdx.graphics.getDeltaTime();

        if (!evokeDone && evokeDelayTimer < 0) {
            this.evokeDone = true;
            evokeEffect.update();
            this.active = false;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        if (this.active && !this.hide)
            vfxBg.render(sb);


        if (this.main != null && !this.hide)
            sb.draw(this.main,
                    this.x - this.main.getWidth() / 2.0f,
                    this.y - this.main.getHeight() / 2.0f,
                    this.main.getWidth() / 2.0f, this.main.getHeight() / 2.0f, this.main.getWidth(), this.main.getHeight(), Settings.scale / this.scale, Settings.scale / this.scale, 0.0f, 0, 0, this.main.getWidth(), this.main.getHeight(), false, false);


        if (this.active && !this.hide)
            vfxFg.render(sb);


        if (!evokeEffect.isDone && evokeDelayTimer < 0 && !this.hide)
            evokeEffect.render(sb);
    }
}

