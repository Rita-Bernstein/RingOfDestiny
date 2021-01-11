package RingOfDestiny.relics;

import RingOfDestiny.RingOfDestiny;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TearsOfMaid extends CustomRelic {
    public static final String ID = RingOfDestiny.makeID("TearsOfMaid");
    private static String imgName = "156.png";
    private static Texture texture = new Texture(RingOfDestiny.assetPath("img/relics/")+ imgName);
    private static Texture outline = new Texture(RingOfDestiny.assetPath("img/relics/outline/")+ imgName);


    private static final int HEAL_AMT = 25;


    public TearsOfMaid() {
        super(ID, texture,outline, RelicTier.STARTER, CustomRelic.LandingSound.FLAT);
    }


    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + HEAL_AMT + this.DESCRIPTIONS[1];
    }


    public void atBattleStart() {
        for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
            if (m.type == AbstractMonster.EnemyType.BOSS) {
                flash();
                AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                m.maxHealth = (int) (m.currentHealth * 1.25f);
                m.heal(m.maxHealth);
                m.healthHb.update();
                return;
            }
        }
    }


    public boolean canSpawn() {
        return (Settings.isEndless || AbstractDungeon.floorNum <= 52);
    }


    public CustomRelic makeCopy() {
        return new TearsOfMaid();
    }
}