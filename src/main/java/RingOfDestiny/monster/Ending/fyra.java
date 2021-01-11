package RingOfDestiny.monster.Ending;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.unique.ChangeStageAction;
import RingOfDestiny.actions.unique.CustomWaitAction;
import RingOfDestiny.actions.unique.UpgardeStateAction;
import RingOfDestiny.vfx.combat.*;
import basemod.abstracts.CustomMonster;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.animations.AnimateJumpAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.*;

import com.esotericsoftware.spine.AnimationState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import java.util.Iterator;

import RingOfDestiny.vfx.HealVerticalLineButHorizontalEffect;
import RingOfDestiny.helpers.*;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;


public class fyra extends CustomMonster {
    public static final String ID = RingOfDestiny.makeID("fyra");
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;
    public static final String MURDER_ENCOUNTER_KEY = RingOfDestiny.makeID("fyra");
    private static final int HP_MIN = 48;
    private static final int HP_MAX = 54;
    private static final int A_2_HP_MIN = 50;
    private static final int A_2_HP_MAX = 56;
    private static final float HB_X = -8.0F;
    private static final float HB_Y = 10.0F;
    private static final float HB_W = 230.0F;
    private static final float HB_H = 240.0F;


    private boolean saidPower = false;
    private boolean talky = true;

    private int moveCount = 0;
    private int turnCounter = 0;
    private int formAmount = 0;
    private boolean isFirstMove = true;


    private int StirkerHitCount = 2;
    private int MirrorHitCount = 3;
    private int FlameHitCount = 4;
    private int WhiteHitCount = 8;


    private static final String[] PMTEXT = CardCrawlGame.languagePack.getUIString(RingOfDestiny.makeID("RitaPowermeter")).TEXT;
    public int powerMeter = 0;
    public int displayPowerMeter = 0;
    public final int maxPowerMeter = 1000;
    private Hitbox pmhb;
    private Texture pixel;

    public boolean superMoves = false;


    public fyra() {
        super(NAME, ID, 160, 0.0F, -15.0F, 300.0F, 350.0F, null, 0.0F, -10.0F);

        this.pixel = AssetLoader.loadImage(RingOfDestiny.assetPath("img/misc/filledpixel.png"));
        this.pmhb = new Hitbox(this.drawX - this.hb.width / 2F, this.hb.y + this.hb.height, this.hb.width, 20F * Settings.scale);

        if (AbstractDungeon.ascensionLevel >= 7) {
            setHp(180 + 125);
        } else {
            setHp(160 + 110);
        }


        if (AbstractDungeon.ascensionLevel >= 4) {
            this.damage.add(new DamageInfo(this, 15));
            this.damage.add(new DamageInfo(this, 6));
            this.damage.add(new DamageInfo(this, 12));
            this.damage.add(new DamageInfo(this, 6));
        } else {
            this.damage.add(new DamageInfo(this, 12));
            this.damage.add(new DamageInfo(this, 6));
            this.damage.add(new DamageInfo(this, 8));
            this.damage.add(new DamageInfo(this, 5));

        }

        if (AbstractDungeon.ascensionLevel >= 19) {
            MirrorHitCount = 6;
        } else {
            MirrorHitCount = 4;
        }


        this.type = AbstractMonster.EnemyType.ELITE;
        this.dialogX = -50.0F * Settings.scale;
        this.dialogY = 50.0F * Settings.scale;


        loadAnimation("RingOfDestiny/monsters/fyra/fyra.atlas", "RingOfDestiny/monsters/fyra/fyra.json", 3.4F);


        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.stateData.setMix("Hit", "Idle", 0.1F);
        this.flipHorizontal = false;


    }


    @Override
    public void refreshIntentHbLocation() {
        super.refreshIntentHbLocation();
        if (this.pmhb != null) {
            this.intentHb.y += this.pmhb.height * 1.5F;
            this.intentHb.cY += this.pmhb.height * 2.5F;

        }
    }


    public void usePreBattleAction() {
        AbstractDungeon.actionManager.addToBottom(new ChangeStageAction("ZERO"));


        CardCrawlGame.music.silenceTempBgmInstantly();
        CardCrawlGame.music.silenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        CardCrawlGame.music.playTempBGM(RingOfDestiny.makeID("music02"));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new ArtifactPower(this, 1)));
    }


    public void takeTurn() {
        int temp;


        switch (this.nextMove) {
            case 0:
                CardCrawlGame.sound.play(RingOfDestiny.makeID("VO_fyra_Stirker"));


                AbstractDungeon.actionManager.addToBottom(new VFXAction(new GluganEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.y), 0.0F));
                AbstractDungeon.actionManager.addToBottom((new CustomWaitAction(0.52f)));
                for (temp = 0; temp < this.StirkerHitCount; temp++) {
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new HitHeavyEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 0.0F));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo) this.damage.get(0), AbstractGameAction.AttackEffect.NONE, true));

                }

                if (AbstractDungeon.ascensionLevel >= 19) {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Wound(), 2, true, true, false, Settings.WIDTH * 0.2F, Settings.HEIGHT / 2.0F));
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Wound(), 1));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Dazed(), 2, true, true, false, Settings.WIDTH * 0.2F, Settings.HEIGHT / 2.0F));
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Dazed(), 1));
                }

                if (this.powerMeter > 1000) {
                    this.powerMeter = 1000;
                }
                break;

            case 1:
                CardCrawlGame.sound.play(RingOfDestiny.makeID("VO_fyra_Mirror"));
                CardCrawlGame.sound.play(RingOfDestiny.makeID("ZEROSkirt"));
                for (temp = 0; temp < this.MirrorHitCount; temp++) {
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new SlashEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 0.0F));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo) this.damage.get(1), AbstractGameAction.AttackEffect.NONE, true));
                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new ArtifactPower(this, 1)));
                if (AbstractDungeon.ascensionLevel >= 19) {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Burn(), 1, true, true, false, Settings.WIDTH * 0.2F, Settings.HEIGHT / 2.0F));
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Burn(), 2));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Wound(), 1, true, true, false, Settings.WIDTH * 0.2F, Settings.HEIGHT / 2.0F));
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Wound(), 2));
                }

                this.powerMeter += 333;
                if (this.powerMeter > 1000) {
                    this.powerMeter = 1000;
                }
                break;


            case 2:
                CardCrawlGame.sound.play(RingOfDestiny.makeID("VO_fyra_Flame"));


                CardCrawlGame.sound.play(RingOfDestiny.makeID("ZEROShadow"));
                for (temp = 0; temp < this.FlameHitCount; temp++) {
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new HitHeavyEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 0.0F));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo) this.damage.get(2), AbstractGameAction.AttackEffect.NONE, true));
                }

                if (AbstractDungeon.ascensionLevel >= 19) {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new VoidCard(), 1, true, true, false, Settings.WIDTH * 0.2F, Settings.HEIGHT / 2.0F));
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new VoidCard(), 1));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Slimed(), 1, true, true, false, Settings.WIDTH * 0.2F, Settings.HEIGHT / 2.0F));
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Slimed(), 1));
                }


                this.powerMeter += 250;
                if (this.powerMeter > 1000) {
                    this.powerMeter = 1000;
                }
                break;

            case 3:

                CardCrawlGame.sound.play(RingOfDestiny.makeID("VO_fyra_White"));


                AbstractDungeon.actionManager.addToBottom((new CustomWaitAction(0.38f)));
                for (temp = 0; temp < this.WhiteHitCount; temp++) {
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new HitHeavyEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 0.0F));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, (DamageInfo) this.damage.get(3), AbstractGameAction.AttackEffect.NONE, true));
                    AbstractDungeon.actionManager.addToBottom((new CustomWaitAction(0.25f)));
                }


                if (AbstractDungeon.ascensionLevel >= 19) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, 5)));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, 3)));
                }

                AbstractDungeon.actionManager.addToBottom(new UpgardeStateAction(0.5f));

                displayPowerMeter = 0;
                this.powerMeter = 0;
                this.superMoves = false;
                break;

        }

        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));

    }


    private void playDeathSfx() {
        CardCrawlGame.sound.play(RingOfDestiny.makeID("VO_fyra_Kill"));
    }


    public void die() {
        if (!(AbstractDungeon.getCurrRoom()).cannotLose) {

            playDeathSfx();
            CardCrawlGame.music.silenceTempBgmInstantly();
            CardCrawlGame.music.unsilenceBGM();

            super.die();

            useFastShakeAnimation(5.0F);
            CardCrawlGame.screenShake.rumble(4.0F);

        }

    }


    private float particletimer = 0;
    private RainbowColor color = new RainbowColor(Color.RED);

    private void resetColor() {
        color = new RainbowColor(Color.RED);
    }

    @Override
    public void update() {
        super.update();
        if (this.powerMeter > 0) {
            particletimer -= Gdx.graphics.getDeltaTime();
            if (particletimer < 0F) {
                particletimer = 0.05F + (maxPowerMeter - powerMeter) / 5000F;
                if (powerMeter < maxPowerMeter) {
                    AbstractDungeon.effectList.add(new HealVerticalLineButHorizontalEffect(this.pmhb.x, this.pmhb.y + MathUtils.random(this.pmhb.height), this.pmhb.width * 0.1F));
                } else {
                    AbstractGameEffect age = new LightningOrbPassiveEffect(this.pmhb.x + MathUtils.random(this.pmhb.width), this.pmhb.cY);
                    AbstractDungeon.effectList.add(age);
                }
            }
            if (this.powerMeter >= this.maxPowerMeter) {
                color.update();
            }
            this.pmhb.update();


            if (displayPowerMeter < powerMeter) {
                displayPowerMeter += maxPowerMeter / 100;
                if (displayPowerMeter > powerMeter) {
                    displayPowerMeter = powerMeter;
                }
            } else if (displayPowerMeter > powerMeter) {
                displayPowerMeter -= maxPowerMeter / 100;
                if (displayPowerMeter < powerMeter) {
                    displayPowerMeter = powerMeter;
                }
            }

            if (this.powerMeter >= this.maxPowerMeter && this.superMoves == false && this.halfDead == false) {
                this.superMoves = true;
            }


        }

    }


    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        if (this.displayPowerMeter < this.maxPowerMeter) {
            sb.setColor(Color.BLACK);
            sb.draw(pixel, this.pmhb.x, this.pmhb.y, 0, 0, this.pmhb.width, this.pmhb.height, Settings.scale, Settings.scale, 0, 0, 0, 1, 1, false, false);
        }
        if (this.powerMeter > 0) {
            float width = this.pmhb.width * Math.min(this.maxPowerMeter, this.displayPowerMeter) / this.maxPowerMeter;
            sb.setColor(color);
            sb.draw(pixel, this.pmhb.x, this.pmhb.y, 0, 0, width, this.pmhb.height, Settings.scale, Settings.scale, 0, 0, 0, 1, 1, false, false);
            if (this.pmhb.hovered) {
                TipHelper.renderGenericTip(this.pmhb.x, this.pmhb.y - this.pmhb.height, PMTEXT[0], PMTEXT[1]);
            }
        }
        sb.setColor(Color.WHITE.cpy());

        FontHelper.renderFontCentered(sb, FontHelper.healthInfoFont, this.powerMeter + "/" + this.maxPowerMeter, this.hb.cX, this.hb.y + this.hb.height + 8F, Color.WHITE);

    }


    protected void getMove(int num) {


        if (this.superMoves == true && this.halfDead == false) {
            setMove(MOVES[3], (byte) 3, AbstractMonster.Intent.ATTACK_DEBUFF, ((DamageInfo) this.damage.get(3)).base, this.WhiteHitCount, true);
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new IntangiblePlayerPower(this, 2)));
            return;
        }


        if (this.isFirstMove) {
            setMove(MOVES[0], (byte) 0, AbstractMonster.Intent.ATTACK_DEBUFF, ((DamageInfo) this.damage.get(0)).base, this.StirkerHitCount, true);
            this.isFirstMove = false;
            return;
        }

        switch (this.moveCount % 3) {
            case 0:
                if (AbstractDungeon.aiRng.randomBoolean()) {
                    setMove(MOVES[2], (byte) 2, AbstractMonster.Intent.ATTACK_DEBUFF, ((DamageInfo) this.damage.get(2)).base, this.FlameHitCount, true);
                    break;
                }
                setMove(MOVES[1], (byte) 1, AbstractMonster.Intent.ATTACK_DEBUFF, ((DamageInfo) this.damage.get(1)).base, this.MirrorHitCount, true);

                break;

            case 1:
                if (!lastMove((byte) 1)) {
                    setMove(MOVES[1], (byte) 1, AbstractMonster.Intent.ATTACK_DEBUFF, ((DamageInfo) this.damage.get(1)).base, this.MirrorHitCount, true);
                    break;
                }
                setMove(MOVES[2], (byte) 2, AbstractMonster.Intent.ATTACK_DEBUFF, ((DamageInfo) this.damage.get(2)).base, this.FlameHitCount, true);

                break;


            default:
                setMove(MOVES[0], (byte) 0, AbstractMonster.Intent.ATTACK_DEBUFF, ((DamageInfo) this.damage.get(0)).base, this.StirkerHitCount, true);
                break;
        }

        this.moveCount++;
    }


    public void damage(DamageInfo info) {

        if (info.output > 0 && hasPower("Intangible")) {
            info.output = 1;
        }


        int prevhp = this.currentHealth;

        super.damage(info);

        if (prevhp > this.currentHealth) {
            this.state.setAnimation(0, "Hit", false);
            this.state.addAnimation(0, "Idle", true, 0.0F);

            this.powerMeter += Math.ceil(2500 * ((prevhp - this.currentHealth) / (float) this.maxHealth));

            if (this.powerMeter > 1000) {
                this.powerMeter = 1000;
            }
            if (this.powerMeter < 0) {
                this.powerMeter = 0;
            }

            if (this.powerMeter >= this.maxPowerMeter && this.superMoves == false && this.halfDead == false) {
                this.superMoves = true;
            }


            if (MathUtils.random(3) == 0) {
                CardCrawlGame.sound.play(RingOfDestiny.makeID("VO_fyra_Hit" + MathUtils.random(2)));
            }
        }


        if (this.currentHealth <= 0 && !this.halfDead) {


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


        }
    }
}

