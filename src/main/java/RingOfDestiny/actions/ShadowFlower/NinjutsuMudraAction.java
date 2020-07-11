package RingOfDestiny.actions.ShadowFlower;
 
 import RingOfDestiny.patches.CustomTagsEnum;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import java.util.ArrayList;
 
 public class NinjutsuMudraAction extends AbstractGameAction {
   private boolean upgraded;


   public NinjutsuMudraAction(boolean upgraded) {
       this.upgraded = upgraded;
     setValues(AbstractDungeon.player, AbstractDungeon.player);
   }
 
 
   
   public void update() {
     ArrayList<AbstractCard> cardsToExhaust = new ArrayList<AbstractCard>();
     for (AbstractCard c : AbstractDungeon.player.hand.group) {
         cardsToExhaust.add(c);
     } 
 
 
     
     for (AbstractCard c : cardsToExhaust) {
         AbstractCard mu = returnRamdonMudraCard();
         if(this.upgraded) mu.upgrade();
         addToBot(new MakeTempCardInHandAction( mu, 1));
     }
     
     for (AbstractCard c : cardsToExhaust) {
       addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
     }
     this.isDone = true;
   }


   private static AbstractCard returnRamdonMudraCard(){
       ArrayList<AbstractCard> list = new ArrayList<AbstractCard>();
       for (AbstractCard c : AbstractDungeon.srcCommonCardPool.group) {
           if (c.hasTag(CustomTagsEnum.Mudra)  && !c.hasTag(AbstractCard.CardTags.HEALING)) {
               list.add(c);
           }
       }
       for (AbstractCard c : AbstractDungeon.srcUncommonCardPool.group) {
           if (c.hasTag(CustomTagsEnum.Mudra)  && !c.hasTag(AbstractCard.CardTags.HEALING)) {
               list.add(c);
           }
       }
       for (AbstractCard c : AbstractDungeon.srcRareCardPool.group) {
           if (c.hasTag(CustomTagsEnum.Mudra)  && !c.hasTag(AbstractCard.CardTags.HEALING)) {
               list.add(c);
           }
       }

       return list.get(AbstractDungeon.cardRng.random(list.size() - 1));
   }
 }


