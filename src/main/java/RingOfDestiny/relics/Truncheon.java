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

public class Truncheon extends CustomRelic {
    public static final String ID = RingOfDestiny.makeID("Truncheon");
    private static String imgName = "128.png";
    private static Texture texture = new Texture(RingOfDestiny.assetPath("img/relics/")+ imgName);
    private static Texture outline = new Texture(RingOfDestiny.assetPath("img/relics/outline/")+ imgName);
    private static final int amount = 2;

    public Truncheon() {
        super(ID, texture,outline, RelicTier.RARE, CustomRelic.LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + amount + this.DESCRIPTIONS[1] ;
    }



    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    public CustomRelic makeCopy() {
        return new Truncheon();
    }
}