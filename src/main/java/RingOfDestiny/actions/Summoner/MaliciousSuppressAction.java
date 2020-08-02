package RingOfDestiny.actions.Summoner;

import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.DiamondManager;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import java.util.ArrayList;

public class MaliciousSuppressAction extends AbstractGameAction {
    private int[] multiDamage;
    private DamageInfo.DamageType damageTypeForTurn;

    public MaliciousSuppressAction(int[] multiDamage, DamageInfo.DamageType damageTypeForTurn) {
        this.multiDamage = multiDamage;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.damageTypeForTurn = damageTypeForTurn;
    }

    public void update() {
        addToBot(new SFXAction("ATTACK_HEAVY"));
        addToBot(new VFXAction(AbstractDungeon.player, new CleaveEffect(), 0.1F));
        addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));

        this.isDone = true;

    }
}


