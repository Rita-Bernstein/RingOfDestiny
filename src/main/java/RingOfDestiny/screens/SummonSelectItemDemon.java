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


public class SummonSelectItemDemon extends AbstractSummonSelectItem{
    public static final String ID = RingOfDestiny.makeID("Demon");

    public SummonSelectItemDemon(){
        super(ID);
        loadAnimation(RingOfDestiny.assetPath("characters/Summoner/summon/summon_10002.atlas"), RingOfDestiny.assetPath("characters/Summoner/summon/summon_10002.json"), 0.67f);

        AnimationState.TrackEntry e = this.state.setAnimation(0, "huxi", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        this.hb_x = 0.0f * Settings.scale;
        this.hb_y = -50.0f * Settings.scale;
        this.hb_w = 340.0f * Settings.scale;
        this.hb_h = 460.0f * Settings.scale;

        this.hb = new Hitbox(this.hb_w, this.hb_h);
    }
}


