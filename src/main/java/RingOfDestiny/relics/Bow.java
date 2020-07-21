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

import static RingOfDestiny.RingOfDestiny.logger;

public class Bow extends CustomRelic {
    public static final String ID = RingOfDestiny.makeID("Bow");
    private static String imgName = "119.png";
    private static Texture texture = new Texture(RingOfDestiny.assetPath("img/relics/")+ imgName);
    private static Texture outline = new Texture(RingOfDestiny.assetPath("img/relics/outline/")+ imgName);

    public Bow() {
        super(ID, texture, outline,RelicTier.UNCOMMON, CustomRelic.LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    public void onMonsterDeath(AbstractMonster m) {
        if (m.hasPower(EtchPower.POWER_ID)) {
            int amount = (m.getPower(EtchPower.POWER_ID)).amount;

            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                flash();

                addToBot(new RelicAboveCreatureAction(m, this));
                addToBot(new ApplyPowerToRandomEnemyAction(AbstractDungeon.player, new EtchPower(null, amount), amount, false, AbstractGameAction.AttackEffect.NONE));

            } else {
                logger.info("no target for the specimen");
            }
        }
    }

    public CustomRelic makeCopy() {
        return new Bow();
    }
}