package RingOfDestiny.relics;

import RingOfDestiny.RingOfDestiny;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class Flintlock extends CustomRelic {
    public static final String ID = RingOfDestiny.makeID("Flintlock");
    private static String imgName = "121.png";
    private static Texture texture = new Texture(RingOfDestiny.assetPath("img/relics/")+ imgName);
    private static Texture outline = new Texture(RingOfDestiny.assetPath("img/relics/outline/")+ imgName);
    private static final int firstDamage = 3;
    private static final int secondDamage = 5;

    public Flintlock() {
        super(ID, texture,outline, RelicTier.STARTER, CustomRelic.LandingSound.FLAT);

    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + firstDamage + this.DESCRIPTIONS[1] + secondDamage + DESCRIPTIONS[2];
    }


    public void atBattleStart() {
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new DamageRandomEnemyAction(new DamageInfo(null, firstDamage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new DamageRandomEnemyAction(new DamageInfo(null, secondDamage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.grayscale = true;
    }


    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    public CustomRelic makeCopy() {
        return new Flintlock();
    }
}