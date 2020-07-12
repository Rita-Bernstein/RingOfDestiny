package RingOfDestiny.relics;

import RingOfDestiny.RingOfDestiny;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public abstract class AbstractRingRelic extends CustomRelic {


    public AbstractRingRelic(String id, Texture texture, RelicTier tier, LandingSound sfx) {
        super(id, texture, tier, sfx);
    }

    public void onCreateShadow(){
    }
}