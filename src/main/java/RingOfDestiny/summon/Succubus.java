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


public class Succubus extends AbstractSummon {
    public static final String ID = RingOfDestiny.makeID("Succubus");
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;

    public Succubus() {
        super(NAME);
        loadAnimation(RingOfDestiny.assetPath("characters/Summoner/summon/summon_10001.atlas"), RingOfDestiny.assetPath("characters/Summoner/summon/summon_10001.json"), 1.4f);

        AnimationState.TrackEntry e = this.state.setAnimation(0, "huxi", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        this.hb_x = 0.0f * Settings.scale;
        this.hb_y = -50.0f * Settings.scale;
        this.hb_w = 280.0f * Settings.scale;
        this.hb_h = 420.0f * Settings.scale;

        this.hb = new Hitbox(this.hb_w, this.hb_h);
    }
}


