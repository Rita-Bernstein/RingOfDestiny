package RingOfDestiny.shadow;

 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 
 public class LeftShadow extends AbstractShadow {
   public static final String SHADOW_ID = "LeftShadow";
   public static final float pos_x = - 100.0f * Settings.scale;
   public static final float pos_y = + 30.0f * Settings.scale;

   
   public LeftShadow() {
     this.ID = SHADOW_ID;
   }



 }


