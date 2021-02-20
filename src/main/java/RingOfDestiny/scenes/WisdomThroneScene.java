package RingOfDestiny.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import com.megacrit.cardcrawl.rooms.TreasureRoom;
import com.megacrit.cardcrawl.rooms.TreasureRoomBoss;
import com.megacrit.cardcrawl.scenes.AbstractScene;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.scene.ShinySparkleEffect;
import com.megacrit.cardcrawl.vfx.scene.WobblyCircleEffect;

import java.util.ArrayList;
import java.util.Iterator;


public class WisdomThroneScene extends AbstractScene {

    private ArrayList<AbstractGameEffect> circles = new ArrayList();
    private TextureAtlas.AtlasRegion bg;
    private TextureAtlas.AtlasRegion campBg;
    private float ySceneScale = 1.3f;


    public WisdomThroneScene() {
        super("RingOfDestiny/img/scene/scene.atlas");
        this.campBg = this.atlas.findRegion("bg_camp");

        this.ambianceName = "AMBIANCE_CITY";
        fadeInAmbiance();
    }


    public void update() {
        super.update();
//        if (!(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.RestRoom) &&
//                !(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.EventRoom)) {
//            updateParticles();
//        }
    }

    private void updateParticles() {
        for (Iterator<AbstractGameEffect> e = this.circles.iterator(); e.hasNext(); ) {
            AbstractGameEffect effect = (AbstractGameEffect) e.next();
            effect.update();
            if (effect.isDone) {
                e.remove();
            }
        }

        if (!(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.TrueVictoryRoom) &&
                this.circles.size() < 72 && !Settings.DISABLE_EFFECTS) {
            if (MathUtils.randomBoolean(0.2F)) {
                this.circles.add(new ShinySparkleEffect());
            } else {
                this.circles.add(new WobblyCircleEffect());
            }
        }
    }


    @Override
    public void randomizeScene() {
    }


    @Override
    public void nextRoom(AbstractRoom room) {
        super.nextRoom(room);
        randomizeScene();

        if (room instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss) {
            CardCrawlGame.music.silenceBGM();
        }

        boolean isBoss = false;

        if (room.monsters != null) {
            for (AbstractMonster mo : room.monsters.monsters) {
                if (mo.type == AbstractMonster.EnemyType.BOSS) {
                    isBoss = true;
                }
            }
        }

        if (isBoss) {
            this.bg = this.atlas.findRegion("bg_3_boss_1");
        }else if(room instanceof TreasureRoomBoss){
            this.bg = this.atlas.findRegion("bg_gift");
        }else if(room instanceof ShopRoom){
            this.bg = this.atlas.findRegion("bg_shop");
        }
        else {
            this.bg = this.atlas.findRegion("bg_3_" + MathUtils.random(2));
        }


        fadeInAmbiance();
    }


    @Override
    public void renderCombatRoomBg(SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        this.renderRingBg(sb, this.bg);
    }

    public void renderRingBg(SpriteBatch sb, TextureAtlas.AtlasRegion region) {
        if (Settings.isFourByThree) {
            sb.draw(region.getTexture(), region.offsetX * Settings.scale, region.offsetY * Settings.yScale, 0.0F, 0.0F, (float) region.packedWidth, (float) region.packedHeight, Settings.scale, Settings.yScale * ySceneScale, 0.0F, region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight(), false, false);
        } else if (Settings.isLetterbox) {
            sb.draw(region.getTexture(), region.offsetX * Settings.xScale, region.offsetY * Settings.xScale, 0.0F, 0.0F, (float) region.packedWidth, (float) region.packedHeight, Settings.xScale, Settings.scale * ySceneScale, 0.0F, region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight(), false, false);
        } else {
            sb.draw(region.getTexture(), region.offsetX * Settings.scale, region.offsetY * Settings.scale, 0.0F, 0.0F, (float) region.packedWidth, (float) region.packedHeight, Settings.scale, Settings.scale * ySceneScale, 0.0F, region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight(), false, false);
        }

    }

    @Override
    public void renderCombatRoomFg(SpriteBatch sb) {
    }


    @Override
    public void renderCampfireRoom(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        this.renderRingBg(sb, this.campBg);
    }
}


