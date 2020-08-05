package RingOfDestiny.relics;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.AddDiamondAction;
import RingOfDestiny.actions.Summoner.AddSoulStoneAction;
import RingOfDestiny.summon.AbstractSummon;
import RingOfDestiny.summon.Demon;
import RingOfDestiny.summon.Succubus;
import RingOfDestiny.summon.Vampire;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class DemonicContract extends AbstractRingRelic {
    public static final String ID = RingOfDestiny.makeID("DemonicContract");
    private static String imgName = "158.png";
    private static Texture texture = new Texture(RingOfDestiny.assetPath("img/relics/") + imgName);
    private static Texture outline = new Texture(RingOfDestiny.assetPath("img/relics/outline/") + imgName);

    public DemonicContract() {
        super(ID, texture, outline, RelicTier.STARTER, CustomRelic.LandingSound.FLAT);

    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] ;
    }


    public void atBattleStart() {
        this.grayscale = false;
    }

    @Override
    public void onSacrifice(AbstractSummon summon) {
        flash();
        this.grayscale = true;

        if (summon instanceof Succubus) {
            addToBot(new AddSoulStoneAction(1));
        }

        if (summon instanceof Demon) {
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new MetallicizePower(AbstractDungeon.player, 4), 4));
        }

        if (summon instanceof Vampire) {
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                    if (!monster.isDead && !monster.isDying) {
                        addToBot(new ApplyPowerAction(monster, AbstractDungeon.player, new WeakPower(monster, 3, false), 3));
                    }
                }
            }
        }

    }


    public CustomRelic makeCopy() {
        return new DemonicContract();
    }
}