package RingOfDestiny.summon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.vfx.TintEffect;

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
    protected float hb_w = 0.0f;
    protected float hb_h = 0.0f;
    protected float hb_x = 0.0f;
    protected float hb_y = 0.0f;


    public AbstractSummon(float hb_w,float hb_h) {
        this.c = Color.WHITE.cpy();
        this.hb_w = hb_w;
        this.hb_h = hb_h;

        this.hb = new Hitbox(this.hb_w,this.hb_h);

    }

    protected void attackAnimation(){
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
        if (AbstractDungeon.player != null) {
            this.cX = AbstractDungeon.player.drawX;
            this.cY = AbstractDungeon.player.drawY;
            this.flipHorizontal = AbstractDungeon.player.flipHorizontal;
            this.flipVertical = AbstractDungeon.player.flipVertical;
        }

        this.hb.move(this.cX + this.current_x + this.hb_x, this.cY + this.current_y + this.hb_y + this.hb_h * 0.5f);
        this.hb.update();

    }

    public void render(SpriteBatch sb) {

        if(this.atlas != null){
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
        }

        this.hb.render(sb);
    }
}


