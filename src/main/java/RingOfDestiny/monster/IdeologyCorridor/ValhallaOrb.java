package RingOfDestiny.monster.IdeologyCorridor;

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

    private float timer;
    private Texture[] vfxFg = new Texture[12];
    private Texture[] vfxBg = new Texture[12];
    private Texture[] evokeEffect = new Texture[5];

    private int delay = 4;
    private int frame = 5;
    private int delayTimer = 0;
    private int frameNum = 0;
    private boolean evokeDone = true;
    private float evokeDelayTimer = 0.0f;


    public ValhallaOrb(int index) {
        this.index = index;

        this.main = ImageMaster.loadImage("RingOfDestiny/monsters/IdeologyCorridor/ValhallaChronicles/orbs/orb_" + this.index + ".png");

        for (int i = 0; i < 12; i++) {
            vfxFg[i] = ImageMaster.loadImage(String.format("RingOfDestiny/img/vfx/monster_10002_skill_1_unpack/monster_10002_skill_1-%05d.png", i));
            vfxBg[i] = ImageMaster.loadImage(String.format("RingOfDestiny/img/vfx/monster_10002_skill_2_unpack/monster_10002_skill_2-%05d.png", i));
        }
        for (int i = 0; i < 5; i++) {
            evokeEffect[i] = ImageMaster.loadImage(String.format("RingOfDestiny/img/vfx/monster_10002_atk_unpack/monster_10002_atk-%05d.png", i));
        }
    }

    public void evoke() {
        this.evokeDelayTimer = 0.16f * this.index;
        this.evokeDone = false;

    }


    public void update() {
        this.timer += Gdx.graphics.getDeltaTime() * 15.0f;

        if(evokeDelayTimer > 0)
        this.evokeDelayTimer -= Gdx.graphics.getDeltaTime();

        if (!evokeDone && evokeDelayTimer < 0) {
            if (this.delayTimer < this.delay) {
                delayTimer++;
            } else {
                delayTimer = 0;
                if (this.frameNum < this.frame - 1) {
                    frameNum++;
                } else {
                    this.evokeDone = true;
                    this.delayTimer = 0;
                    this.frameNum = 0;
                    this.active = false;
                }
            }
        }
    }

    public void render(SpriteBatch sb) {
        int frame = (int) (this.timer % 12);



        sb.setColor(Color.WHITE);
        if (this.vfxBg.length != 0 && this.active)
            sb.draw(this.vfxBg[frame],
                    this.x - 100.0f,
                    this.y - 100.0f,
                    100.0f,
                    100.0f,
                    200.0f,
                    200.0f,
                    Settings.scale / this.scale,
                    Settings.scale / this.scale,
                    0.0f, 0, 0,
                    200,
                    200,
                    false, false);


        if (this.main != null)
            sb.draw(this.main,
                    this.x - this.main.getWidth() / 2.0f,
                    this.y - this.main.getHeight() / 2.0f,
                    this.main.getWidth() / 2.0f, this.main.getHeight() / 2.0f, this.main.getWidth(), this.main.getHeight(), Settings.scale / this.scale, Settings.scale / this.scale, 0.0f, 0, 0, this.main.getWidth(), this.main.getHeight(), false, false);


        if (this.vfxFg.length != 0 && this.active)
            sb.draw(this.vfxFg[frame],
                    this.x - 100.0f,
                    this.y - 100.0f,
                    100.0f,
                    100.0f,
                    200.0f,
                    200.0f,
                    Settings.scale / this.scale,
                    Settings.scale / this.scale,
                    0.0f, 0, 0,
                    200,
                    200,
                    false, false);


        if (!evokeDone && evokeDelayTimer < 0)
            sb.draw(this.evokeEffect[frameNum],
                    this.x - 150.0f,
                    this.y - 150.0f,
                    150.0f,
                    150.0f,
                    300.0f,
                    300.0f,
                    Settings.scale / this.scale,
                    Settings.scale / this.scale,
                    0.0f, 0, 0,
                    300,
                    300,
                    false, false);

    }
}

