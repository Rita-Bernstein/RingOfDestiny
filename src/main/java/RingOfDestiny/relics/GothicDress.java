package RingOfDestiny.relics;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.powers.EtchPower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class GothicDress extends CustomRelic {
    public static final String ID = RingOfDestiny.makeID("GothicDress");
    private static String imgName = "117.png";
    private static Texture texture = new Texture(RingOfDestiny.assetPath("img/relics/")+ imgName);
    private static Texture outline = new Texture(RingOfDestiny.assetPath("img/relics/outline/")+ imgName);

    public GothicDress() {
        super(ID, texture,outline, RelicTier.COMMON, CustomRelic.LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    public void atBattleStart() {
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new EtchPower(mo, 1), 1));
        }
        this.grayscale = true;
    }


    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    public CustomRelic makeCopy() {
        return new GothicDress();
    }
}