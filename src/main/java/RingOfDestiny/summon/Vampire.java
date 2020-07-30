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
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.TintEffect;

import java.util.Set;

import static com.megacrit.cardcrawl.core.AbstractCreature.sr;


public class Vampire extends AbstractSummon {
    public static final String ID = RingOfDestiny.makeID("Vampire");
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;

    public Vampire() {
        super(NAME);
        loadAnimation(RingOfDestiny.assetPath("characters/Summoner/summon/summon_10003.atlas"), RingOfDestiny.assetPath("characters/Summoner/summon/summon_10003.json"), 0.67f);

        AnimationState.TrackEntry e = this.state.setAnimation(0, "huxi", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        this.hb_x = 0.0f * Settings.scale;
        this.hb_y = -50.0f * Settings.scale;
        this.hb_w = 320.0f * Settings.scale;
        this.hb_h = 450.0f * Settings.scale;

        this.hb = new Hitbox(this.hb_w, this.hb_h);
    }
}


