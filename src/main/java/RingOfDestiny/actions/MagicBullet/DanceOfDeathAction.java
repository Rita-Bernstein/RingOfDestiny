package RingOfDestiny.actions.MagicBullet;


import RingOfDestiny.powers.EtchPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import javax.swing.*;


public class DanceOfDeathAction extends AbstractGameAction {
    private static final float DURATION = 0.01F;
    private static final float POST_ATTACK_WAIT_DUR = 0.1F;
    private int numTimes;
    private int amount;


    public DanceOfDeathAction(AbstractCreature target, int amount, int numTimes) {
        this.target = target;
        this.actionType = AbstractGameAction.ActionType.DEBUFF;
        this.duration = 0.01F;
        this.numTimes = numTimes;
        this.amount = amount;
    }


    public void update() {
        if (this.target == null) {
            this.isDone = true;
            return;
        }


        if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
            this.isDone = true;

            return;
        }

        if (this.numTimes > 1 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.numTimes--;
            AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);


            addToBot(new DanceOfDeathAction(randomMonster, this.amount, this.numTimes));

        }

        if (this.target.currentHealth > 0) {
            addToBot(new ApplyPowerAction(this.target, AbstractDungeon.player, new EtchPower(this.target, this.amount), this.amount, true, AttackEffect.NONE));
            addToBot(new WaitAction(0.1F));
        }
        this.isDone = true;

    }
}


