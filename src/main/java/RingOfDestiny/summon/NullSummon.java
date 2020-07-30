package RingOfDestiny.summon;

import RingOfDestiny.RingOfDestiny;
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


public class NullSummon extends AbstractSummon {

    public NullSummon() {
        super("");
        this.hb_x = 0.0f * Settings.scale;
        this.hb_y = 0.0f * Settings.scale;
        this.hb_w = 160.0f;
        this.hb_h = 160.0f;

        this.hb = new Hitbox(this.hb_w, this.hb_h);
    }
}


