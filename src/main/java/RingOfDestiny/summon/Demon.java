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
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.vfx.TintEffect;

import java.util.Set;

import static com.megacrit.cardcrawl.core.AbstractCreature.sr;


public class Demon extends AbstractSummon {
    public static final String ID = RingOfDestiny.makeID("Demon");
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;

    public Demon() {
        super(NAME);
        loadAnimation(RingOfDestiny.assetPath("characters/Summoner/summon/summon_10002.atlas"), RingOfDestiny.assetPath("characters/Summoner/summon/summon_10002.json"), 0.67f);

        AnimationState.TrackEntry e = this.state.setAnimation(0, "huxi", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        this.hb_x = 50.0f * Settings.scale;
        this.hb_y = -50.0f * Settings.scale;
        this.hb_w = 420.0f * Settings.scale;
        this.hb_h = 460.0f * Settings.scale;

        this.hb = new Hitbox(this.hb_w, this.hb_h);
    }
}


