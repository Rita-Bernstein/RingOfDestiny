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


public class DeathTrackAction extends AbstractGameAction {
    private int numTimes;
    private int amount;
    private DamageInfo info;
    private AttackEffect attackEffect;


    public DeathTrackAction(AbstractCreature target,DamageInfo info, AttackEffect effect, int amount, int numTimes) {
        this.target = target;
        this.info = info;
        setValues(target, info);
        this.attackEffect = effect;
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


            addToBot(new DeathTrackAction(randomMonster, this.info , this.attackEffect, this.amount, this.numTimes));

        }

        if (this.target.currentHealth > 0) {

            addToBot(new DamageAction(this.target, this.info, this.attackEffect));
            addToBot(new ApplyPowerAction(this.target, AbstractDungeon.player, new EtchPower(this.target, this.amount), this.amount, true, AttackEffect.NONE));
            addToBot(new WaitAction(0.1F));
        }
        this.isDone = true;

    }
}


