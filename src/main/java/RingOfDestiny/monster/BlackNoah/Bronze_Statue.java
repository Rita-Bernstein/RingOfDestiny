package RingOfDestiny.monster.BlackNoah;

import RingOfDestiny.RingOfDestiny;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;


import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;

import java.util.Iterator;


public class Bronze_Statue extends AbstractMonster {
    public static final String ID = RingOfDestiny.makeID("Bronze_Statue");
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(RingOfDestiny.makeID("Bronze_Statue"));
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;

    private static final int HP_MIN = 52;

    private static final int HP_MAX = 58;

    private static final int A_2_HP_MIN = 54;
    private static final int A_2_HP_MAX = 60;
    private static final int BEAM_DMG = 8;
    private static float stealBlock = 0.4F;

    private static final int BLOCK_AMT = 12;
    private static final byte BEAM = 1;
    private static final byte SUPPORT_BEAM = 2;
    private static final byte STASIS = 3;
    private boolean usedStasis = false;
    private int count;
    private int playerBlock = 100;
    private boolean revive = false;
    private boolean hadDie = false;

    public Bronze_Statue(float x, float y, int count) {
        super(NAME, ID, AbstractDungeon.monsterHpRng.random(52, 58), 0.0F, 0.0F, 200.0F, 320.0F, "RingOfDestiny/img/monsters/Guile.png", x, y);


        if (AbstractDungeon.ascensionLevel >= 9) {
            setHp(60);
        } else {
            setHp(50);
        }

        this.count = count;


        if (AbstractDungeon.ascensionLevel >= 4) {
            this.damage.add(new DamageInfo(this, 12));

        } else {
            this.damage.add(new DamageInfo(this, 8));
        }

        if (AbstractDungeon.ascensionLevel >= 19) {
            this.stealBlock = 0.45F;
            this.playerBlock = 80;
        } else {
            this.stealBlock = 0.4F;
            this.playerBlock = 100;
        }
    }


    public void usePreBattleAction() {

    }

    public void takeTurn() {
        switch (this.nextMove) {
            case 1:
                addToBot(new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
                addToBot(new VFXAction(new BorderFlashEffect(Color.SKY)));
                addToBot(new VFXAction(new SmallLaserEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.hb.cX, this.hb.cY + 85), 0.3F));


                addToBot(new DamageAction(AbstractDungeon.player, (DamageInfo) this.damage
                        .get(0), AbstractGameAction.AttackEffect.NONE));
                break;
            case 0:
                playerBlock = AbstractDungeon.player.currentBlock;
                addToBot(new GainBlockAction(AbstractDungeon.player, this, -(int) Math.floor(AbstractDungeon.player.currentBlock * stealBlock)));
                addToBot(new GainBlockAction(this, this, (int) Math.floor(AbstractDungeon.player.currentBlock * stealBlock * 0.5)));
                addToBot(new GainBlockAction(AbstractDungeon.getMonsters().getMonster(RingOfDestiny.makeID("Rita")), this, (int) Math.floor(AbstractDungeon.player.currentBlock * stealBlock * 0.5)));
                break;

            case 2:
                this.revive = true;
                break;

            case 3:
                addToBot(new HealAction(this, this, this.maxHealth));
                this.halfDead = false;
                this.revive = false;
                break;
        }
        addToBot(new RollMoveAction(this));
    }


    public void update() {
        super.update();


        if (this.nextMove != 0 && (AbstractDungeon.player.currentBlock >= playerBlock) && this.revive == false) {
            setMove((byte) 0, AbstractMonster.Intent.STRONG_DEBUFF);
            createIntent();
        }
        if (this.nextMove != 1 && (AbstractDungeon.player.currentBlock < playerBlock) && this.revive == false) {
            setMove((byte) 1, AbstractMonster.Intent.ATTACK, ((DamageInfo) this.damage.get(0)).base);
            createIntent();
        }


        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (m instanceof Rita) {
                Rita rita = (Rita) m;
                if (rita.killStatue) {
                    this.halfDead = false;
                    hadDie = true;
                    this.currentHealth = 0;
                    super.die();
                    this.healthBarUpdatedEvent();
                    this.hideHealthBar();
                }
            }

        }
    }


    protected void getMove(int num) {

        if (this.revive == false) {
            setMove((byte) 1, AbstractMonster.Intent.ATTACK, ((DamageInfo) this.damage.get(0)).base);
        } else {

        }


        if (this.revive == true) {
            setMove((byte) 3, AbstractMonster.Intent.BUFF);
        }
    }

    public void damage(DamageInfo info) {

        super.damage(info);
        if (this.currentHealth <= 0 && !this.halfDead) {

            if (!this.hadDie) {
                this.halfDead = true;
            }


            for (AbstractPower p : this.powers) {
                p.onDeath();
            }
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                r.onMonsterDeath(this);
            }
            AbstractDungeon.actionManager.addToTop(new ClearCardQueueAction());

            for (Iterator<AbstractPower> s = this.powers.iterator(); s.hasNext(); ) {
                AbstractPower p = (AbstractPower) s.next();
                if (p.type == AbstractPower.PowerType.DEBUFF) {
                    s.remove();
                }
            }
            setMove((byte) 2, AbstractMonster.Intent.UNKNOWN);
            this.revive = true;
            createIntent();
        }
    }


    public void die() {
        if (this.hadDie == true) {
            super.die();
            this.healthBarUpdatedEvent();
        }

    }

}
