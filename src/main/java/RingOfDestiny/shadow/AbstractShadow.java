package RingOfDestiny.shadow;

import RingOfDestiny.actions.ShadowFlower.ClearShadowAction;
import RingOfDestiny.patches.ShadowPatches;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class AbstractShadow {
    private static final Logger logger = LogManager.getLogger(AbstractShadow.class.getName());
    public String ID;


    protected Color c = Color.WHITE.cpy();
    protected Texture img = null;

    protected float angle;


    public void onCreateShadow() {
    }

    public void onExitShadow() {
    }

    public void onEndOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new ClearShadowAction());
    }


    public void onPlayAttackCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse) {
            AbstractMonster m = null;

            if (action.target != null) {
                m = (AbstractMonster) action.target;
            }

            AbstractCard tmp = card.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = Settings.HEIGHT / 2.0F;

            if (m != null) {
                tmp.calculateCardDamage(m);
            }

            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
        }
    }


    public void update() {
        updateAnimation();
    }


    public void updateAnimation() {
    }


    public void render(SpriteBatch sb) {
        if (this.img == null) {
            return;
        }
        sb.setColor(this.c);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, AbstractDungeon.player.drawX - 256.0F + AbstractDungeon.player.animX, AbstractDungeon.player.drawY - 256.0F + AbstractDungeon.player.animY + AbstractDungeon.player.hb_h / 2.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, -this.angle, 0, 0, 512, 512, false, false);

        sb.setBlendFunction(770, 771);
    }


    public static boolean hasShadow(String shadowName) {
        for (AbstractShadow s : ShadowPatches.AbstractPlayerShadowFieldPatch.shadow.get(AbstractDungeon.player)) {
            if (s.ID.equals(shadowName)) {
                return true;
            }
        }
        return false;
    }

    public static AbstractShadow getShadowFromID(String name) {
        if (name.equals(LeftShadow.SHADOW_ID))
            return new LeftShadow();
        if (name.equals(NullShadow.SHADOW_ID))
            return new NullShadow();
        if (name.equals(RightShadow.SHADOW_ID))
            return new RightShadow();


        logger.info("[ERROR] Unknown shadow: " + name + " called for in getStanceFromName()");
        return null;
    }
}


