package RingOfDestiny.relics;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.AddDiamondAction;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class DogEyes extends CustomRelic {
    public static final String ID = RingOfDestiny.makeID("DogEyes");
    private static String imgName = "125.png";
    private static Texture texture = new Texture(RingOfDestiny.assetPath("img/relics/")+ imgName);
    private static Texture outline = new Texture(RingOfDestiny.assetPath("img/relics/outline/")+ imgName);
    private static final int amount = 2;

    public DogEyes() {
        super(ID, texture,outline, RelicTier.STARTER, CustomRelic.LandingSound.FLAT);

    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + amount + this.DESCRIPTIONS[1] ;
    }


    public void atBattleStart() {
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new AddDiamondAction(amount));
        this.grayscale = true;
    }


    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    public CustomRelic makeCopy() {
        return new DogEyes();
    }
}