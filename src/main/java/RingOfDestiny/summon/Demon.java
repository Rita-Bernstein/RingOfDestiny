package RingOfDestiny.summon;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.powers.summon.HegemonyPower;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.TintEffect;

import java.util.Set;

import static com.megacrit.cardcrawl.core.AbstractCreature.sr;


public class Demon extends AbstractSummon {
    public static final String ID = RingOfDestiny.makeID("Demon");

    public Demon() {
        super(ID);
        loadAnimation(RingOfDestiny.assetPath("characters/Summoner/summon/summon_10002.atlas"), RingOfDestiny.assetPath("characters/Summoner/summon/summon_10002.json"), 0.67f);

        AnimationState.TrackEntry e = this.state.setAnimation(0, "huxi", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        this.hb_x = 0.0f * Settings.scale;
        this.hb_y = -50.0f * Settings.scale;
        this.hb_w = 340.0f * Settings.scale;
        this.hb_h = 460.0f * Settings.scale;

        this.hb = new Hitbox(this.hb_w, this.hb_h);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        super.onUseCard(card, action);
        if (card.type == AbstractCard.CardType.ATTACK){
            AbstractDungeon.actionManager.addToBottom(
                    new DamageRandomEnemyAction(new DamageInfo(null, this.damage, DamageInfo.DamageType.THORNS),
                            AbstractGameAction.AttackEffect.SLASH_HEAVY));
            this.attackAnimation();
        }
    }

    @Override
    public void onSacrifice() {
        super.onSacrifice();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new HegemonyPower(AbstractDungeon.player)));
    }
}


