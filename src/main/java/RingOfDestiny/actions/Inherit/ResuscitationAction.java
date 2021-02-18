package RingOfDestiny.actions.Inherit;

import RingOfDestiny.patches.EnergyPanelRenderPatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ResuscitationAction extends AbstractGameAction {
    private boolean freeToPlayOnce;
    private AbstractPlayer p;
    private int subEnergyOnUse;
    private int extraEffect;


    public ResuscitationAction(AbstractPlayer p, boolean freeToPlayOnce, int subEnergyOnUse, int extraEffect) {
        this.subEnergyOnUse = -1;
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.subEnergyOnUse = subEnergyOnUse;
        this.extraEffect = extraEffect;
    }


    public void update() {
        int effect = EnergyPanelRenderPatches.PatchEnergyPanelField.subEnergy.get(AbstractDungeon.overlayMenu.energyPanel).totalCount;
        if (this.subEnergyOnUse != -1) {
            effect = this.subEnergyOnUse;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        effect += this.extraEffect;

        if (effect > 0) {
            addToBot(new HealAction(this.p, this.p, effect));
            addToBot(new UseSubEnergyAction(effect));
        }
        this.isDone = true;
    }
}


