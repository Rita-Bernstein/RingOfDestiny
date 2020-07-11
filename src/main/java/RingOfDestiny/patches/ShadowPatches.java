package RingOfDestiny.patches;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.shadow.AbstractShadow;
import RingOfDestiny.shadow.LeftShadow;
import RingOfDestiny.shadow.NullShadow;
import RingOfDestiny.shadow.RightShadow;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.Skeleton;
import com.evacipated.cardcrawl.modthespire.lib.*;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.TheSilent;
import com.megacrit.cardcrawl.characters.Watcher;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.exordium.Hexaghost;
import com.megacrit.cardcrawl.powers.AbstractPower;


import java.lang.reflect.Field;

import static com.megacrit.cardcrawl.core.AbstractCreature.sr;

public class ShadowPatches {


    @SpirePatch(
            clz = AbstractPlayer.class,
            method=SpirePatch.CLASS
    )
    public static class AbstractPlayerShadowFieldPatch {
        public static SpireField<AbstractShadow[]> shadow = new SpireField<>(() -> new AbstractShadow[]{new NullShadow(),new NullShadow()});

    }
//
//    @SpirePatch(
//            clz = AbstractPlayer.class,
//            method = SpirePatch.CONSTRUCTOR,
//            paramtypez = {String.class,AbstractPlayer.PlayerClass.class}
//
//    )
//    public static class AbstractPlayerShadowPatch {
//        @SpireInsertPatch(rloc = 0)
//        public static SpireReturn<Void> Insert(AbstractPlayer _instance,String name, AbstractPlayer.PlayerClass setClass) {
//
//            AbstractPlayerShadowFieldPatch.shadow.get(_instance)[0] = new NullShadow();
//            AbstractPlayerShadowFieldPatch.shadow.get(_instance)[1] = new NullShadow();
//
//            return SpireReturn.Continue();
//        }
//    }

        @SpirePatch(
            clz = AbstractPlayer.class,
            method = "combatUpdate"

    )
    public static class AbstractPlayerCombatUpdatePatch {
        @SpirePostfixPatch
        public static SpireReturn<Void> Postfix(AbstractPlayer _instance) {
           for(AbstractShadow s : AbstractPlayerShadowFieldPatch.shadow.get(_instance)) {
               s.update();
           }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "render"

    )
    public static class AbstractPlayerRenderPatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(AbstractPlayer _instance, SpriteBatch sb) {
            for(AbstractShadow s : AbstractPlayerShadowFieldPatch.shadow.get(_instance)) {
                    s.render(sb);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = GameActionManager.class,
            method = "callEndOfTurnActions"

    )
    public static class GameActionManagerCallEndOfTurnActionsPatch {
        @SpirePostfixPatch
        public static SpireReturn<Void> Postfix(GameActionManager _instance) {
            for(AbstractShadow s : AbstractPlayerShadowFieldPatch.shadow.get(AbstractDungeon.player)) {
                    s.onEndOfTurn();
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = UseCardAction.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {AbstractCard.class,AbstractCreature.class}
    )
    public static class UseCardActionPatch {
        @SpireInsertPatch(rloc = 22)
        public static SpireReturn<Void> Insert(UseCardAction _instance, AbstractCard card, AbstractCreature target) {

            for(AbstractShadow s : AbstractPlayerShadowFieldPatch.shadow.get(AbstractDungeon.player)) {
                if(!card.dontTriggerOnUseCard && card.type == AbstractCard.CardType.ATTACK)
                    s.onPlayAttackCard(card,_instance);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "resetPlayer"
    )
    public static class AbstractDungeonResetPlayerPatch {
        @SpireInsertPatch(rloc = 13)
        public static SpireReturn<Void> Insert() {
                ShadowPatches.AbstractPlayerShadowFieldPatch.shadow.get(AbstractDungeon.player)[0] = new NullShadow();
                ShadowPatches.AbstractPlayerShadowFieldPatch.shadow.get(AbstractDungeon.player)[1] = new NullShadow();
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "renderPlayerImage"
    )
    public static class AbstractPlayerRenderPlayerImagePatch {
        @SpireInsertPatch(rloc = 11)
        public static SpireReturn<Void> Insert(AbstractPlayer _instance, SpriteBatch sb) {
            try {
                Field stateDataField = AbstractCreature.class.getDeclaredField("skeleton");
                stateDataField.setAccessible(true);
                Skeleton sk = ((Skeleton)stateDataField.get(_instance));

                float playerRootPosX = sk.findBone("root").getX();
                float playerRootPosY = sk.findBone("root").getY();

                sk.setColor(RingOfDestiny.shadowColorFix);


                if(AbstractShadow.hasShadow(LeftShadow.SHADOW_ID)){
                    sk.findBone("root").setPosition(
                            playerRootPosX + LeftShadow.pos_x,
                            playerRootPosY + LeftShadow.pos_y
                    );

                    sk.updateWorldTransform();
                    sr.draw(CardCrawlGame.psb, sk);
                }


                if(AbstractShadow.hasShadow(RightShadow.SHADOW_ID)){
                    sk.findBone("root").setPosition(
                            playerRootPosX + RightShadow.pos_x,
                            playerRootPosY + RightShadow.pos_y
                    );

                    sk.updateWorldTransform();
                    sr.draw(CardCrawlGame.psb, sk);
                }


                sk.setColor(_instance.tint.color);
                sk.findBone("root").setPosition(playerRootPosX , playerRootPosY);
                sk.updateWorldTransform();


            } catch (Exception e) {
                e.printStackTrace();
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = Watcher.class,
            method = "renderPlayerImage"
    )
    public static class WatcherRenderPlayerImagePatch {
        @SpireInsertPatch(rloc = 17)
        public static SpireReturn<Void> Insert(Watcher _instance, SpriteBatch sb) {
            try {
                Field stateDataField = AbstractCreature.class.getDeclaredField("skeleton");
                stateDataField.setAccessible(true);
                Skeleton sk = ((Skeleton)stateDataField.get(_instance));

                float playerRootPosX = sk.findBone("root").getX();
                float playerRootPosY = sk.findBone("root").getY();

                sk.setColor(RingOfDestiny.shadowColorFix);


                if(AbstractShadow.hasShadow(LeftShadow.SHADOW_ID)){
                    sk.findBone("root").setPosition(
                            playerRootPosX + LeftShadow.pos_x,
                            playerRootPosY + LeftShadow.pos_y
                    );

                    sk.updateWorldTransform();
                    sr.draw(CardCrawlGame.psb, sk);
                }


                if(AbstractShadow.hasShadow(RightShadow.SHADOW_ID)){
                    sk.findBone("root").setPosition(
                            playerRootPosX + RightShadow.pos_x,
                            playerRootPosY + RightShadow.pos_y
                    );

                    sk.updateWorldTransform();
                    sr.draw(CardCrawlGame.psb, sk);
                }


                sk.setColor(_instance.tint.color);
                sk.findBone("root").setPosition(playerRootPosX , playerRootPosY);
                sk.updateWorldTransform();


            } catch (Exception e) {
                e.printStackTrace();
            }
            return SpireReturn.Continue();
        }
    }

}


