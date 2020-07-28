package RingOfDestiny.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.select.HandCardSelectScreen;


public class BaseGameFixPatches {


    @SpirePatch(
            clz = HandCardSelectScreen.class,
            method= "refreshSelectedCards"
    )
    public static class HandCardSelectScreenPatch {
        @SpireInsertPatch(rloc = 99, localvars = {"anyNumber"})
        public static SpireReturn<Void> Insert(HandCardSelectScreen _instance,boolean anyNumber) {
            if (_instance.selectedCards.size() >= 1 && anyNumber && !_instance.canPickZero){
                _instance.button.enable();
            }
            System.out.println("patch手牌选择界面");
            return SpireReturn.Continue();
        }
    }
}


