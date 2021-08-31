package RingOfDestiny.monster.IdeologyCorridor;

import RingOfDestiny.vfx.AbstractAtlasGameEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class ValhallaOrb implements Disposable {
    public float x, y;
    public int index;
    private Texture main;

    public boolean active = false;
    private AbstractAtlasGameEffect vfxFg;
    private AbstractAtlasGameEffect vfxBg;
    private AbstractAtlasGameEffect evokeEffect;

    private float evokeDelayTimer = 0.0f;

    private boolean hide = false;

    public ValhallaOrb(int index) {
        this.index = index;
        this.main = ImageMaster.loadImage("RingOfDestiny/monsters/IdeologyCorridor/ValhallaChronicles/orbs/orb_" + this.index + ".png");
        vfxFg = new AbstractAtlasGameEffect("monster_10002_skill_1", x, y, 100.0f, 100.0f, 0.6f * Settings.scale, true);
        vfxBg = new AbstractAtlasGameEffect("monster_10002_skill_2", x, y, 100.0f, 100.0f, 0.6f * Settings.scale, true);
        evokeEffect = new AbstractAtlasGameEffect("monster_10002_atk", x, y, 150.0f, 150.0f, 0.6f * Settings.scale, false);
    }


    public void evoke() {
        this.evokeDelayTimer = 0.16f * this.index;
        evokeEffect.resetAnimation();
    }

    public void hide() {
        this.hide = true;
    }


    public void update() {
        vfxFg.xPosition = x;
        vfxFg.yPosition = y;
        vfxFg.update();

        vfxBg.xPosition = x;
        vfxBg.yPosition = y;
        vfxBg.update();


        if (evokeDelayTimer > 0)
            this.evokeDelayTimer -= Gdx.graphics.getDeltaTime();

        evokeEffect.xPosition = x;
        evokeEffect.yPosition = y;

        if (!evokeEffect.isDone && evokeDelayTimer < 0) {
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
                    this.main.getWidth() / 2.0f, this.main.getHeight() / 2.0f, this.main.getWidth(), this.main.getHeight(), 0.6f * Settings.scale, 0.6f * Settings.scale, 0.0f, 0, 0, this.main.getWidth(), this.main.getHeight(), false, false);


        if (this.active && !this.hide)
            vfxFg.render(sb);


        if (!evokeEffect.isDone && evokeDelayTimer < 0 && !this.hide)
            evokeEffect.render(sb);
    }

    @Override
    public void dispose() {
        vfxFg.dispose();
        vfxBg.dispose();
        evokeEffect.dispose();
        main.dispose();
    }
}

