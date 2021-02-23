package RingOfDestiny.actions.unique;

import RingOfDestiny.monster.IdeologyCorridor.SpiderChildG;
import RingOfDestiny.powers.Monster.IdeologyCorridor.VenomPower;
import RingOfDestiny.vfx.BackgroundBoardRender;
import RingOfDestiny.vfx.BackgroundRender;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IncreaseCostForTurnAction extends AbstractGameAction {


    public IncreaseCostForTurnAction() {

    }

    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.costForTurn >= 0) {
                c.costForTurn += 1;
                if (c.costForTurn < 0) {
                    c.costForTurn = 1;
                }

                if (c.costForTurn != c.cost) {
                    c.isCostModifiedForTurn = true;
                }
            }
        }
        this.isDone = true;
    }
}