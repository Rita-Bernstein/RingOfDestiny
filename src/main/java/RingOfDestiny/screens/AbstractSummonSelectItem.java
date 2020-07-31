package RingOfDestiny.screens;

import RingOfDestiny.patches.SummonPatches;
import RingOfDestiny.summon.AbstractSummon;
import RingOfDestiny.summon.Demon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.TintEffect;

import static com.megacrit.cardcrawl.core.AbstractCreature.sr;


public class AbstractSummonSelectItem {
    public float cX = Settings.WIDTH  * 0.5f;
    public float cY = Settings.HEIGHT * 0.5f;
    protected float current_x = 0.0f * Settings.scale;
    protected float current_y = -250.0f * Settings.scale;

    public boolean flipHorizontal = false;
    public boolean flipVertical = false;

    protected Color c = Color.WHITE.cpy();
    protected TintEffect tint = new TintEffect();

    protected TextureAtlas atlas;
    protected Skeleton skeleton;
    public AnimationState state;
    protected AnimationStateData stateData;

    public Hitbox hb;
    protected float hb_w;
    protected float hb_h;
    protected float hb_x;
    protected float hb_y;

    public String id;
    public String name;
    public String[] description;
    public String[] extra_description;


    public boolean reticleRendered = false;
    private float reticleOffset = 0.0F;
    public float reticleAlpha = 0.0F;
    private float reticleAnimTimer = 0.0F;
    private static final float RETICLE_OFFSET_DIST = 15.0F * Settings.scale;
    private Color reticleShadowColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
    private Color reticleColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);

    public AbstractSummonSelectItem(String id){
        this.id = id;
        this.name =  CardCrawlGame.languagePack.getUIString(this.id).TEXT[0];
        this.description = CardCrawlGame.languagePack.getUIString(this.id).TEXT;
        this.extra_description = CardCrawlGame.languagePack.getUIString(this.id).EXTRA_TEXT;
    }


    public void update() {
        this.hb.move(this.cX + this.current_x + this.hb_x, this.cY + this.current_y + this.hb_y + this.hb_h * 0.5f);
        this.hb.update();


        if (this.hb.hovered && !AbstractDungeon.player.isDraggingCard && AbstractDungeon.player.hoveredCard == null) {
            TipHelper.renderGenericTip(this.hb.cX + this.hb_w * 0.5f, this.hb.cY + this.hb_h * 0.3f, name, this.extra_description[0] + this.extra_description[1]);
            this.reticleRendered = true;
        }

        updateClick();
        updateReticle();
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

            this.hb.render(sb);
            renderReticle(sb,this.hb);

            FontHelper.bannerFont.getData().setScale(1.0F);
            FontHelper.renderFontCentered(sb, FontHelper.bannerFont, name, this.hb.cX, this.hb.cY - this.hb_h /2 - 10.0f * Settings.scale, Settings.GOLD_COLOR.cpy());

            FontHelper.bannerFont.getData().setScale(0.7F);
            FontHelper.renderFontCentered(sb, FontHelper.bannerFont, description[1], this.hb.cX, this.hb.cY - this.hb_h /2 - 70.0f * Settings.scale, Color.WHITE.cpy());
            FontHelper.renderFontCentered(sb, FontHelper.bannerFont, description[2], this.hb.cX, this.hb.cY - this.hb_h /2 - 110.0f * Settings.scale, Color.WHITE.cpy());
        }
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

    protected void updateClick() {
        if (InputHelper.justClickedLeft && this.hb.hovered && !AbstractDungeon.player.isDraggingCard && AbstractDungeon.player.hoveredCard == null) {
            this.hb.clickStarted = true;
        }

        if (this.hb.clicked) {
            hb.clicked = false;
           onClicked();
        }
    }

    public void onClicked() {
        AbstractDungeon.closeCurrentScreen();
        SummonPatches.AbstractPlayerSummonField.summon.set(AbstractDungeon.player, AbstractSummon.getSummonForID(this.id));
    }


    public void renderReticle(SpriteBatch sb, Hitbox hb) {

        renderReticleCorner(sb, -hb.width / 2.0F + this.reticleOffset, hb.height / 2.0F - this.reticleOffset, hb, false, false);
        renderReticleCorner(sb, hb.width / 2.0F - this.reticleOffset, hb.height / 2.0F - this.reticleOffset, hb, true, false);
        renderReticleCorner(sb, -hb.width / 2.0F + this.reticleOffset, -hb.height / 2.0F + this.reticleOffset, hb, false, true);
        renderReticleCorner(sb, hb.width / 2.0F - this.reticleOffset, -hb.height / 2.0F + this.reticleOffset, hb, true, true);
    }

    protected void updateReticle() {
        if (this.reticleRendered) {
            this.reticleRendered = false;
            this.reticleAlpha += Gdx.graphics.getDeltaTime() * 3.0F;
            if (this.reticleAlpha > 1.0F) {
                this.reticleAlpha = 1.0F;
            }


            this.reticleAnimTimer += Gdx.graphics.getDeltaTime();
            if (this.reticleAnimTimer > 1.0F) {
                this.reticleAnimTimer = 1.0F;
            }
            this.reticleOffset = Interpolation.elasticOut.apply(RETICLE_OFFSET_DIST, 0.0F, this.reticleAnimTimer);
        }
        else {

            this.reticleAlpha = 0.0F;
            this.reticleAnimTimer = 0.0F;
            this.reticleOffset = RETICLE_OFFSET_DIST;
        }
    }

    private void renderReticleCorner(SpriteBatch sb, float x, float y, Hitbox hb, boolean flipX, boolean flipY) {
        this.reticleShadowColor.a = this.reticleAlpha / 4.0F;
        sb.setColor(this.reticleShadowColor);
        sb.draw(ImageMaster.RETICLE_CORNER, hb.cX + x - 18.0F + 4.0F * Settings.scale, hb.cY + y - 18.0F - 4.0F * Settings.scale, 18.0F, 18.0F, 36.0F, 36.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 36, 36, flipX, flipY);

        this.reticleColor.a = this.reticleAlpha;
        sb.setColor(this.reticleColor);
        sb.draw(ImageMaster.RETICLE_CORNER, hb.cX + x - 18.0F, hb.cY + y - 18.0F, 18.0F, 18.0F, 36.0F, 36.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 36, 36, flipX, flipY);
    }
}


