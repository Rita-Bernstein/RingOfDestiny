package RingOfDestiny.screens;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.summon.Demon;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.Set;


public class SummonSelectItemSuccubus extends AbstractSummonSelectItem{
    public static final String ID = RingOfDestiny.makeID("Succubus");

    public SummonSelectItemSuccubus(){
        super(ID);

        loadAnimation(RingOfDestiny.assetPath("characters/Summoner/summon/summon_10001.atlas"), RingOfDestiny.assetPath("characters/Summoner/summon/summon_10001.json"), 1.4f);

        AnimationState.TrackEntry e = this.state.setAnimation(0, "huxi", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        this.hb_x = 0.0f * Settings.scale;
        this.hb_y = -50.0f * Settings.scale;
        this.hb_w = 280.0f * Settings.scale;
        this.hb_h = 420.0f * Settings.scale;

        this.hb = new Hitbox(this.hb_w, this.hb_h);

        this.current_x = -450.0f * Settings.scale;
    }
}


