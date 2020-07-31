package RingOfDestiny.patches;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.character.MagicBullet;
import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.ShowCardAndPoofAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.lang.reflect.Constructor;

import static basemod.BaseMod.getModdedCharacters;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;


public class PurgeOnUseCardPatches {

    @SpirePatch(
            clz = UseCardAction.class,
            method = "update"
    )
    public static class UseCardActionUpdatePatch {
        @SpireInsertPatch(rloc = 17, localvars = {"targetCard"})
        public static SpireReturn<Void> Insert(UseCardAction _instance, AbstractCard targetCard) {
            if (targetCard.hasTag(CustomTagsEnum.PurgeOnUse)) {
                AbstractDungeon.actionManager.addToTop(new ShowCardAndPoofAction(targetCard));
                _instance.isDone = true;
                AbstractDungeon.player.cardInUse = null;

                return SpireReturn.Return(null);
            }


            return SpireReturn.Continue();
        }
    }


}


