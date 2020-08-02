package RingOfDestiny.helpers;

import RingOfDestiny.cards.AbstractRingCard;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import basemod.abstracts.CustomSavable;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SoulStoneCustomSavable implements CustomSavable<Integer> {
    public int soulStoneSaved = 0;

    @Override
    public Integer onSave() {
        System.out.println("保存存储数值");
        int amount = 0;
        if (AbstractDungeon.overlayMenu.energyPanel != null) {
            amount = EnergyPanelRenderPatches.PatchEnergyPanelField.soulStone.get(AbstractDungeon.overlayMenu.energyPanel).soulStoneAmount;
        }

        return amount;
    }

    @Override
    public void onLoad(Integer integer) {
        System.out.println("载入存储数值");
        soulStoneSaved = integer;
    }

}