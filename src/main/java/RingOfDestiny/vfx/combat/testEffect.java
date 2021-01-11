package RingOfDestiny.vfx.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.DamageImpactCurvyEffect;


public class testEffect
        extends AbstractGameEffect {
    private static final float EFFECT_DUR = 1.0F;
    private float x;
    private float y;
    private float targetY;
    private static TextureAtlas.AtlasRegion img;
    private boolean impactHook = false;

    public testEffect(float x, float y) {
        this(x, y, new Color(1.0F, 1.0F, 0.1F, 0.0F));
    }


    public testEffect(float x, float y, Color newColor) {
        if (img == null) {
            img = ImageMaster.vfxAtlas.findRegion("combat/weightyImpact");
        }


        this.scale = Settings.scale;
        this.x = x - img.packedWidth / 2.0F;
        this.y = Settings.HEIGHT - img.packedHeight / 2.0F;
        this.duration = 1.0F;
        this.targetY = y - 180.0F * Settings.scale;
        this.rotation = MathUtils.random(-1.0F, 1.0F);
        this.color = newColor;
    }

    public void update() {
        this.y = Interpolation.fade.apply(Settings.HEIGHT, this.targetY, 1.0F - this.duration / 1.0F);

        this.scale += Gdx.graphics.getDeltaTime();
        this.duration -= Gdx.graphics.getDeltaTime();

        if (this.duration < 0.0F) {
            this.isDone = true;
            CardCrawlGame.sound.playA("ATTACK_IRON_2", -0.5F);
        } else if (this.duration < 0.2F) {
            if (!this.impactHook) {
                this.impactHook = true;


                for (int i = 0; i < 5; i++) {
                    AbstractDungeon.effectsQueue.add(new DamageImpactCurvyEffect(this.x + img.packedWidth / 2.0F, this.y + img.packedWidth / 2.0F));
                }

                for (int i = 0; i < 30; i++) {


                }
            }
            this.color.a = Interpolation.fade.apply(0.0F, 0.5F, 0.2F / this.duration);
        } else {
            this.color.a = Interpolation.pow2Out.apply(0.6F, 0.0F, this.duration / 1.0F);
        }
    }


    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);


        this.color.g = 1.0F;
        sb.setColor(this.color);
        sb.draw(img, this.x, this.y + 140.0F * Settings.scale, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight * (this.duration + 0.2F) * 5.0F, this.scale *
                MathUtils.random(0.99F, 1.01F) * 0.3F, this.scale *
                MathUtils.random(0.99F, 1.01F) * 2.0F * (this.duration + 0.8F), this.rotation);

        this.color.g = 0.6F;
        sb.setColor(this.color);
        sb.draw(img, this.x, this.y + 40.0F * Settings.scale, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight * (this.duration + 0.2F) * 5.0F, this.scale *

                MathUtils.random(0.99F, 1.01F) * 0.7F, this.scale *
                MathUtils.random(0.99F, 1.01F) * 1.3F * (this.duration + 0.8F), this.rotation);

        this.color.g = 0.2F;
        sb.setColor(this.color);
        sb.draw(img, this.x, this.y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight * (this.duration + 0.2F) * 5.0F, this.scale *

                MathUtils.random(0.99F, 1.01F), this.scale *
                MathUtils.random(0.99F, 1.01F) * (this.duration + 0.8F), this.rotation);

        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}


