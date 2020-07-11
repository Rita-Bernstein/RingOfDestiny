package RingOfDestiny.actions.ShadowFlower;

import RingOfDestiny.cards.AbstractIntentChangingCard;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;

import java.util.function.Predicate;


public class ForceIntentAction
        extends AbstractGameAction {
    private AbstractIntentChangingCard.IntentTypes intentType;
    public static Predicate<AbstractMonster> attackTest = mo -> (mo.intent == AbstractMonster.Intent.ATTACK || mo.intent == AbstractMonster.Intent.ATTACK_DEFEND || mo.intent == AbstractMonster.Intent.ATTACK_DEBUFF || mo.intent == AbstractMonster.Intent.ATTACK_BUFF);
    public static Predicate<AbstractMonster> notAttackTest = mo -> (mo.intent != AbstractMonster.Intent.ATTACK && mo.intent != AbstractMonster.Intent.ATTACK_DEFEND && mo.intent != AbstractMonster.Intent.ATTACK_DEBUFF && mo.intent != AbstractMonster.Intent.ATTACK_BUFF);

    private AbstractPlayer p;
    private AbstractMonster m;

    public ForceIntentAction(AbstractPlayer p, AbstractMonster m, AbstractIntentChangingCard.IntentTypes type) {
        this.p = p;
        this.m = m;
        this.intentType = type;
    }


    public void update() {
        this.isDone = newIntent(this.m, this.intentType);
    }


    public boolean newIntent(AbstractMonster m, AbstractIntentChangingCard.IntentTypes type) {
        Predicate<AbstractMonster> test;
        if (m.id.equals("GiantHead") || m.id.equals("Maw") || m.id.equals("BookOfStabbing") || m.id.equals("CorruptHeart")) {
            return true;
        }


        if (type == AbstractIntentChangingCard.IntentTypes.ATTACK) {
            test = attackTest;
        } else {
            test = notAttackTest;
        }

        if (test.test(m)) {
            return true;
        }

        EnemyMoveInfo originalMove = (EnemyMoveInfo) ReflectionHacks.getPrivate(m, AbstractMonster.class, "move");
        int tries = 0;
        while (tries < 10) {
            m.rollMove();
            m.createIntent();
            if (test.test(m)) {
                return true;
            }
            tries++;
        }

        m.setMove(originalMove.nextMove, originalMove.intent, originalMove.baseDamage, originalMove.multiplier, originalMove.isMultiDamage);
        m.createIntent();
        return true;
    }

    public static boolean previewNewIntent(AbstractMonster m, AbstractIntentChangingCard.IntentTypes type) {
        Predicate<AbstractMonster> test;
        if (m.id.equals("GiantHead") || m.id.equals("Maw") || m.id.equals("BookOfStabbing") || m.id.equals("CorruptHeart")) {
            return true;
        }


        if (type == AbstractIntentChangingCard.IntentTypes.ATTACK) {
            test = attackTest;
        } else {
            test = notAttackTest;
        }

        if (test.test(m)) {
            return true;
        }

        EnemyMoveInfo originalMove = (EnemyMoveInfo) ReflectionHacks.getPrivate(m, AbstractMonster.class, "move");
        int tries = 0;
        while (tries < 10) {
            m.rollMove();
            m.createIntent();
            if (test.test(m)) {
                return true;
            }
            tries++;
        }

        m.setMove(originalMove.nextMove, originalMove.intent, originalMove.baseDamage, originalMove.multiplier, originalMove.isMultiDamage);
        m.createIntent();
        return true;
    }
}


