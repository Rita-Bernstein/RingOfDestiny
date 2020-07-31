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


public class SummonSelectItemVampire extends AbstractSummonSelectItem{
    public static final String ID = RingOfDestiny.makeID("Vampire");

    public SummonSelectItemVampire(){
        super(ID);
        loadAnimation(RingOfDestiny.assetPath("characters/Summoner/summon/summon_10003.atlas"), RingOfDestiny.assetPath("characters/Summoner/summon/summon_10003.json"), 0.67f);

        AnimationState.TrackEntry e = this.state.setAnimation(0, "huxi", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        this.hb_x = 0.0f * Settings.scale;
        this.hb_y = -50.0f * Settings.scale;
        this.hb_w = 320.0f * Settings.scale;
        this.hb_h = 450.0f * Settings.scale;

        this.hb = new Hitbox(this.hb_w, this.hb_h);

        this.current_x = 450.0f * Settings.scale;
    }
}


