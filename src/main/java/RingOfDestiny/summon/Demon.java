package RingOfDestiny.summon;

import RingOfDestiny.RingOfDestiny;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.TintEffect;

import java.util.Set;

import static com.megacrit.cardcrawl.core.AbstractCreature.sr;


public class Demon extends AbstractSummon {

    public Demon() {
        super(340.0f, 340.0f);
        loadAnimation(RingOfDestiny.assetPath("characters/Summoner/summon/summon_10002.atlas"), RingOfDestiny.assetPath("characters/Summoner/summon/summon_10002.json"), 0.67f);

        AnimationState.TrackEntry e = this.state.setAnimation(0, "huxi", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        this.hb_x = 50.0f * Settings.scale;
        this.hb_y = -50.0f * Settings.scale;
    }
}


