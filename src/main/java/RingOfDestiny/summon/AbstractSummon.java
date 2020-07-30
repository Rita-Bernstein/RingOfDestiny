package RingOfDestiny.summon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.vfx.TintEffect;
import com.badlogic.gdx.math.Interpolation;

import static com.megacrit.cardcrawl.core.AbstractCreature.sr;


public abstract class AbstractSummon {
    public float cX = 0.0F;
    public float cY = 0.0F;
    protected float current_x = 250.0f * Settings.scale;
    protected float current_y = 50.0f * Settings.scale;
    protected Color c;

    protected TextureAtlas atlas;
    protected Skeleton skeleton;
    public AnimationState state;
    protected AnimationStateData stateData;

    protected TintEffect tint = new TintEffect();

    public boolean flipHorizontal = false;
    public boolean flipVertical = false;

    public Hitbox hb;
    protected float hb_w;
    protected float hb_h;
    protected float hb_x;
    protected float hb_y;


    public String name;

    private float hoverTimer;
    private Color nameColor;
    private Color nameBgColor;

    public float hbAlpha = 0.0F;
    public float hbShowTimer = 0.0F;

    public int damage ;
    public int baseDamage;
    public float damageUIScale = 1.0f * Settings.scale;
    private Texture damageUI = ImageMaster.loadImage("RingOfDestiny/img/ui/topPanel/Summoner/summonAtkShow.png");

    public AbstractSummon(String name) {
        this.name = name;
        this.c = Color.WHITE.cpy();
        this.damage = this.baseDamage = 3;

        this.hb = new Hitbox(this.hb_w, this.hb_h);

        this.hoverTimer = 0.0F;
        this.nameColor = new Color();
        this.nameBgColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
        showHealthBar();
    }

    protected void attackAnimation() {
        this.state.setAnimation(0, "gongji", true);
        this.state.addAnimation(0, "huxi", true, 0.0F);
    }


    protected void loadAnimation(String atlasUrl, String skeletonUrl, float scale) {
        this.atlas = new TextureAtlas(Gdx.files.internal(atlasUrl));
        SkeletonJson json = new SkeletonJson(this.atlas);

        json.setScale(Settings.scale / scale);
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(skeletonUrl));
        this.skeleton = new Skeleton(skeletonData);
        this.skeleton.setColor(Color.WHITE);
        this.stateData = new AnimationStateData(skeletonData);
        this.state = new AnimationState(this.stateData);
    }

    public void update() {
        if (AbstractDungeon.player != null && this.atlas != null) {
            this.cX = AbstractDungeon.player.drawX;
            this.cY = AbstractDungeon.player.drawY;
            this.flipHorizontal = AbstractDungeon.player.flipHorizontal;
            this.flipVertical = AbstractDungeon.player.flipVertical;
        }

        this.hb.move(this.cX + this.current_x + this.hb_x, this.cY + this.current_y + this.hb_y + this.hb_h * 0.5f);
        this.hb.update();



        updateHbPopInAnimation();
    }

    public void render(SpriteBatch sb) {

        if (this.atlas != null) {
            sb.setColor(this.c);
            this.state.update(Gdx.graphics.getDeltaTime());
            this.state.apply(this.skeleton);
            this.skeleton.updateWorldTransform();
            this.skeleton.setPosition(this.cX + this.current_x, this.cY + this.current_y);
            this.skeleton.setColor(this.tint.color);
            this.skeleton.setFlip(this.flipHorizontal, this.flipVertical);

            sb.end();
            CardCrawlGame.psb.begin();
            sr.draw(CardCrawlGame.psb, this.skeleton);
            CardCrawlGame.psb.end();
            sb.begin();

            sb.setColor(this.c);
            renderDamage(sb);

            if (!AbstractDungeon.player.isDead) {
                renderName(sb);
            }



            this.hb.render(sb);
        }
    }

    private void renderDamage(SpriteBatch sb){
        sb.draw(damageUI,
                this.hb.cX + this.hb_w * 0.5f - 62.0f,
                this.hb.cY + this.hb_h * 0.5f - 47.0f,
                62.0F, 47.0F, 124.0F, 94.0F, damageUIScale, damageUIScale, 0.0f, 0, 0, 124, 94, false, false);

        FontHelper.renderFontCentered(sb,
                FontHelper.cardEnergyFont_L,
                Integer.toString(this.damage),
                this.hb.cX + this.hb_w * 0.5f,
                this.hb.cY + this.hb_h * 0.5f,
                this.c, damageUIScale * 1.0f);

    }

    private void renderName(SpriteBatch sb) {
        if (!this.hb.hovered) {
            this.hoverTimer = MathHelper.fadeLerpSnap(this.hoverTimer, 0.0F);
        } else {
            this.hoverTimer += Gdx.graphics.getDeltaTime();
        }

        if (!AbstractDungeon.player.isDraggingCard || AbstractDungeon.player.hoveredCard == null) {

            if (this.hoverTimer != 0.0F) {
                if (this.hoverTimer * 2.0F > 1.0F) {
                    this.nameColor.a = 1.0F;
                } else {
                    this.nameColor.a = this.hoverTimer * 2.0F;
                }
            } else {
                this.nameColor.a = MathHelper.slowColorLerpSnap(this.nameColor.a, 0.0F);
            }

            float tmp = Interpolation.exp5Out.apply(1.5F, 2.0F, this.hoverTimer);
            this.nameColor.r = Interpolation.fade.apply(Color.DARK_GRAY.r, Settings.CREAM_COLOR.r, this.hoverTimer * 10.0F);
            this.nameColor.g = Interpolation.fade.apply(Color.DARK_GRAY.g, Settings.CREAM_COLOR.g, this.hoverTimer * 3.0F);
            this.nameColor.b = Interpolation.fade.apply(Color.DARK_GRAY.b, Settings.CREAM_COLOR.b, this.hoverTimer * 3.0F);

            float y = Interpolation.exp10Out.apply(this.hb.cY + 8.0F * Settings.scale, this.hb.cY , this.nameColor.a) - this.hb_h * 0.5f;
            float x = this.hb.cX;

            this.nameBgColor.a = this.nameColor.a / 2.0F * this.hbAlpha;
            sb.setColor(this.nameBgColor);
            TextureAtlas.AtlasRegion img = ImageMaster.MOVE_NAME_BG;
            sb.draw(img, x - img.packedWidth / 2.0F, y - img.packedHeight / 2.0F, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, Settings.scale * tmp, Settings.scale * 2.0F, 0.0F);


            this.nameColor.a *= this.hbAlpha;
            FontHelper.renderFontCentered(sb, FontHelper.tipHeaderFont, this.name, x, y, this.nameColor);
        }
    }

    private void updateHbPopInAnimation() {
        if (this.hbShowTimer > 0.0F) {
            this.hbShowTimer -= Gdx.graphics.getDeltaTime();
            if (this.hbShowTimer < 0.0F) {
                this.hbShowTimer = 0.0F;
            }

            this.hbAlpha = Interpolation.fade.apply(0.0F, 1.0F, 1.0F - this.hbShowTimer / 0.7F);
        }
    }

    public void showHealthBar() {
        this.hbShowTimer = 0.7F;
        this.hbAlpha = 0.0F;
    }
}


