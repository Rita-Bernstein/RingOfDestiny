package RingOfDestiny.summon;

import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.vfx.DiamondFireEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractSummon {
    public float cX = 0.0F;
    public float cY = 0.0F;
    protected float current_x = 0.0f;
    protected float current_y = 0.0f;
    protected Color c;
    protected Color particleColor;
    protected float angle;
    protected float scale;
    protected boolean isSocket;
    protected float particleTimer;
    protected int index = 0;


    protected TextureAtlas atlas;
    protected Skeleton skeleton;
    public AnimationState state;
    protected AnimationStateData stateData;

    public AbstractSummon() {
        this.c = Settings.CREAM_COLOR.cpy();
        this.isSocket = false;
        this.particleTimer = 0.12f;
    }

    public void onEvoke() {
        this.isSocket = false;
    }

    public void onSocket() {
        System.out.println("最后了");
        this.isSocket = true;
    }



    public static void addDiamond() {
    }

    public static void evokeDiamond() {
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
        if (isSocket && !AbstractDungeon.player.isDead) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.12F;
                AbstractDungeon.topLevelEffectsQueue.add(new DiamondFireEffect(
                        this.cX + this.current_x ,//this.cX + this.current_x + 42.0f * Settings.scale,
                        this.cY + this.current_y ,//this.cY + this.current_y + 42.0f * Settings.scale,
                        this.scale * 0.55f,
                        this.particleColor));
            }
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.c);


    }
}


