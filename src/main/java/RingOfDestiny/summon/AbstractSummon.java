package RingOfDestiny.summon;

import RingOfDestiny.patches.SummonPatches;
import RingOfDestiny.relics.AbstractRingRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.*;
import com.evacipated.cardcrawl.mod.stslib.patches.HitboxRightClick;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.TintEffect;
import com.badlogic.gdx.math.Interpolation;
import org.apache.commons.lang3.ObjectUtils;

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
    protected float clickTimer = 0.0f;
    protected boolean longClicked = false;
    protected float hb_w;
    protected float hb_h;
    protected float hb_x;
    protected float hb_y;


    public String name;
    public String[] description;

    private float hoverTimer;
    private Color nameColor;
    private Color nameBgColor;

    public float hbAlpha = 0.0F;
    public float hbShowTimer = 0.0F;

    public int damage;
    public int baseDamage;
    public float damageUIScale = 1.0f * Settings.scale;
    private Texture damageUI = ImageMaster.loadImage("RingOfDestiny/img/ui/topPanel/Summoner/summonAtkShow.png");



    public boolean reticleRendered = false;
    private float reticleOffset = 0.0F;
    public float reticleAlpha = 0.0F;
    private float reticleAnimTimer = 0.0F;
    private static final float RETICLE_OFFSET_DIST = 15.0F * Settings.scale;
    private Color reticleShadowColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
    private Color reticleColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);

    public AbstractSummon(String id) {

        this.name =  CardCrawlGame.languagePack.getUIString(id).TEXT[0];
        this.description = CardCrawlGame.languagePack.getUIString(id).EXTRA_TEXT;
        this.c = Color.WHITE.cpy();
        this.damage = this.baseDamage = 3;

        this.hb = new Hitbox(this.hb_w, this.hb_h);

        this.hoverTimer = 0.0F;
        this.nameColor = new Color();
        this.nameBgColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
        showHealthBar();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
    }

    public void onAttacked(DamageInfo info, int damageAmount) {
    }

    public void onSacrifice() {
        if (AbstractDungeon.player != null) {
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                if(r instanceof AbstractRingRelic){
                    ((AbstractRingRelic) r).onSacrifice();
                }
            }

            SummonPatches.AbstractPlayerSummonField.summon.set(AbstractDungeon.player,new NullSummon());
        }
    }

    public void attackAnimation() {
        this.state.setAnimation(0, "gongji", true);
        this.state.addAnimation(0, "huxi", true, 0.0F);
    }

    public void randomAttack(int amount ){
        for(int i = 0 ;i < amount;i++){
            AbstractDungeon.actionManager.addToBottom(
                    new DamageRandomEnemyAction(new DamageInfo(null, this.damage, DamageInfo.DamageType.THORNS),
                            AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }

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

            this.hb.move(this.cX + this.current_x + this.hb_x, this.cY + this.current_y + this.hb_y + this.hb_h * 0.5f);
            this.hb.update();
            if (this.hb.hovered && !AbstractDungeon.player.isDraggingCard && AbstractDungeon.player.hoveredCard == null ) {
                TipHelper.renderGenericTip(this.hb.cX + this.hb_w * 0.5f, this.hb.cY + this.hb_h * 0.3f, name, this.description[0] + this.description[1]);
                this.reticleRendered = true;
            }

            updateHbPopInAnimation();
            updateLongClick();
            updateReticle();
        }
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

            renderReticle(sb,this.hb);
        }
    }

    private void renderDamage(SpriteBatch sb) {
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

        if (!AbstractDungeon.player.isDraggingCard && AbstractDungeon.player.hoveredCard == null) {

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

            float y = Interpolation.exp10Out.apply(this.hb.cY + 8.0F * Settings.scale, this.hb.cY, this.nameColor.a) - this.hb_h * 0.5f;
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

    protected void updateLongClick() {
        if (InputHelper.justClickedLeft && this.hb.hovered && !AbstractDungeon.player.isDraggingCard && AbstractDungeon.player.hoveredCard == null) {
            this.hb.clickStarted = true;
        }


        if(this.hb.clickStarted){
            this.clickTimer += Gdx.graphics.getDeltaTime();
        }else {
            this.clickTimer = 0.0f;
        }

        if(this.clickTimer > 0.5f ){
            this.longClicked = true;
            this.clickTimer = 0.0f;
        }

        if (this.longClicked) {
            onSacrifice();
        }
    }

    public static AbstractSummon getSummonForID(String id){
        AbstractSummon su = new NullSummon();
        switch (id){
            case "RingOfDestiny:Demon":
                su = new Demon();
                break;

            case "RingOfDestiny:Succubus":
                su = new Succubus();
                break;

            case  "RingOfDestiny:Vampire":
                su = new Vampire();
                break;

        }

        return su;
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


