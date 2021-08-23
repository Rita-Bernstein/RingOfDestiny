package RingOfDestiny.skinCharacters.skins;

import RingOfDestiny.RingOfDestiny;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;

import static com.megacrit.cardcrawl.core.AbstractCreature.sr;

public abstract class AbstractSkin {
    public Texture portraitStatic_IMG;
    public Texture portraitAnimation_IMG;

    public TextureAtlas portraitAtlas = null;
    public Skeleton portraitSkeleton;
    public AnimationState portraitState;
    public AnimationStateData portraitStateData;
    public SkeletonData portraitData;

    public String portraitAtlasPath = null;

    public String NAME;

    public String SHOULDER1;
    public String SHOULDER2;
    public String CORPSE;

    public String atlasURL;
    public String jsonURL;
    public float renderscale;

    public String DESCRIPTION = null;

    public AbstractSkin() {
    }

    public void loadPortraitAnimation() {
        if (hasAnimation()) {
            loadAnimation();
            setAnimation();
            InitializeStaticPortraitVar();
        }
    }

    public Boolean hasAnimation() {
        return this.portraitAtlasPath != null;
    }

    public void loadAnimation() {
        portraitAtlas = new TextureAtlas(Gdx.files.internal(portraitAtlasPath + ".atlas"));
        SkeletonJson json = new SkeletonJson(portraitAtlas);
        json.setScale(Settings.scale / 1.0F);
        portraitData = json.readSkeletonData(Gdx.files.internal(portraitAtlasPath + ".json"));

        portraitSkeleton = new Skeleton(portraitData);
        portraitSkeleton.setColor(Color.WHITE);
        portraitStateData = new AnimationStateData(portraitData);
        portraitState = new AnimationState(portraitStateData);
        portraitStateData.setDefaultMix(0.2F);

        portraitState.setTimeScale(1.0f);
    }


    public void setAnimation() {
        portraitState.setAnimation(1, "fade_in", false);
        portraitState.addAnimation(0, "idle", true, 0.0f);
        InitializeStaticPortraitVar();
    }

    public void InitializeStaticPortraitVar() {
    }

    public Texture updateBgImg() {
        RingOfDestiny.saveSkins();
        if (!hasAnimation()) {
            return portraitStatic_IMG;
        } else {
            return portraitAnimation_IMG;
        }
    }

    public void render(SpriteBatch sb) {
        if (hasAnimation()) {
            portraitState.update(Gdx.graphics.getDeltaTime());
            portraitState.apply(portraitSkeleton);
            portraitSkeleton.updateWorldTransform();

            setPos();
            skeletonRenderUpdate(sb);
            portraitSkeleton.setColor(Color.WHITE.cpy());
            portraitSkeleton.setFlip(false, false);

            sb.end();
            CardCrawlGame.psb.begin();
            skeletonRender(sb);
        }
    }

    public void setPos() {
    }

    public void skeletonRenderUpdate(SpriteBatch sb) {
    }

    public void skeletonRender(SpriteBatch sb) {
        if (hasAnimation()) {
            sr.draw(CardCrawlGame.psb, portraitSkeleton);

            CardCrawlGame.psb.end();
            sb.begin();
        }
    }

    public void update() {
    }

    public void clearWhenClick() {
    }

    public void extraHitboxRender(SpriteBatch sb) {
    }

    public Boolean extraHitboxClickCheck() {
        return false;
    }

    public String getNewCharDescription() {
        if (DESCRIPTION != null)
            return DESCRIPTION;
        else {
            return "";
        }
    }

    public String getAtlasURL() {
        return atlasURL;
    }

    public String getJsonURL() {
        return jsonURL;
    }

    public String getSHOULDER1() {
        return SHOULDER1;
    }

    public String getSHOULDER2() {
        return SHOULDER2;
    }

    public String getCORPSE() {
        return CORPSE;
    }

    public void dispose() {
        if (this.portraitAtlas != null) this.portraitAtlas.dispose();
    }
}


