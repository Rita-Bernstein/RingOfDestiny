package RingOfDestiny.diamonds;


import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.vfx.FlashTextureEffect;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DarkOrbEvokeAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.UIStrings;

public class DiamondManager {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(RingOfDestiny.makeID("DiamondManager"));
    public static final String name = uiStrings.EXTRA_TEXT[0];
    public static final String[] desc = uiStrings.TEXT;
    public String description;

    public float tX;
    public float tY;

    public Hitbox hb = new Hitbox(180.0F * Settings.scale, 96.0F * Settings.scale);
    public Hitbox hb2 = new Hitbox(64.0F * Settings.scale, 96.0F * Settings.scale);
    protected float fontScale = 0.7F;
    private Color c = Color.WHITE.cpy();//new Color(0.2F, 1.0F, 1.0F, 1.0f);
    private Color colorW = Color.WHITE.cpy();
    private static final float fontFix_X = -110.0f;
    private static final float fontFix_Y = 70.0f;
    private static final float icon_space = 80.0f;

    private Texture aoeImg = ImageMaster.loadImage("RingOfDestiny/img/diamonds/intent/aoe.png");
    private Texture evokeImg = ImageMaster.loadImage("RingOfDestiny/img/diamonds/intent/evoke.png");
    private Texture cardDrawImg = ImageMaster.loadImage("RingOfDestiny/img/diamonds/intent/carddraw.png");
    private Texture defendImg = ImageMaster.loadImage("RingOfDestiny/img/diamonds/intent/defend.png");
    private static final float imgScale = 1.0f * Settings.scale;
    private static final float imgFix_X = 24.0f + fontFix_X;
    private static final float imgFix_Y = 24.0f + fontFix_Y;

    protected int evokeAmount;
    protected int baseEvokeAmount;

    protected int basePassiveAmount;
    protected int passiveAmount;

    protected int baseCardDrawAmount;
    protected int cardDrawAmount;

    protected int baseBlockAmount;
    protected int blockAmount;

    public DiamondManager() {
        this.evokeAmount = this.baseEvokeAmount = 6;
        this.passiveAmount = this.basePassiveAmount = 3;
        this.cardDrawAmount = this.baseCardDrawAmount = 1;
        this.blockAmount = this.baseBlockAmount = 6;
        updateDescription();
    }

    public void updateDescription() {
        this.description = desc[0] + this.evokeAmount + desc[1] + this.passiveAmount + desc[2] + this.cardDrawAmount + desc[3] + this.blockAmount + desc[4];
    }

    public void update() {
        this.hb.move(this.tX, this.tY);
        this.hb2.move(this.tX + 56 * Settings.scale, this.tY - 64.0F * Settings.scale);
        this.hb.update();
        this.hb2.update();
        if (this.hb.hovered || this.hb2.hovered) {
            TipHelper.renderGenericTip(this.tX - 140.0F * Settings.scale, this.tY + 500.0F * Settings.scale, name, this.description);
        }
        this.fontScale = MathHelper.scaleLerpSnap(this.fontScale, 0.7F);
    }

    public void render(SpriteBatch sb) {
        this.renderText(sb);
        this.hb.render(sb);
        this.hb2.render(sb);
    }

    protected void renderText(SpriteBatch sb) {
        int num = 0;
        int haloAmount = getHaloAmount();

        sb.setColor(this.colorW);

        sb.draw(evokeImg,
                this.tX + imgFix_X * Settings.scale - 32.0f,
                this.tY + imgFix_Y * Settings.scale - 32.0f,
                32.0F, 32.0F, 64.0F, 64.0F, imgScale, imgScale, 0.0f, 0, 0, 64, 64, false, false);

        FontHelper.renderFontCentered(sb,
                FontHelper.cardEnergyFont_L,
                Integer.toString(this.evokeAmount),
                this.tX + fontFix_X * Settings.scale,
                this.tY + fontFix_Y * Settings.scale,
                this.c, this.fontScale);

        if (haloAmount >= 1) {
            sb.draw(aoeImg,
                    this.tX + imgFix_X * Settings.scale + icon_space * num * Settings.scale - 32.0f,
                    this.tY + imgFix_Y * Settings.scale + icon_space * Settings.scale - 32.0f,
                    32.0F, 32.0F, 64.0F, 64.0F, imgScale, imgScale, 0.0f, 0, 0, 64, 64, false, false);

            FontHelper.renderFontCentered(sb,
                    FontHelper.cardEnergyFont_L,
                    Integer.toString(this.passiveAmount),
                    this.tX + fontFix_X * Settings.scale + icon_space * num * Settings.scale,
                    this.tY + fontFix_Y * Settings.scale + icon_space * Settings.scale,
                    this.c, this.fontScale);
            num++;
        }

        if (haloAmount >= 2) {
            sb.draw(cardDrawImg,
                    this.tX + imgFix_X * Settings.scale + icon_space * num * Settings.scale - 32.0f,
                    this.tY + imgFix_Y * Settings.scale + icon_space * Settings.scale - 32.0f,
                    32.0F, 32.0F, 64.0F, 64.0F, imgScale, imgScale, 0.0f, 0, 0, 64, 64, false, false);

            FontHelper.renderFontCentered(sb,
                    FontHelper.cardEnergyFont_L,
                    Integer.toString(this.cardDrawAmount),
                    this.tX + fontFix_X * Settings.scale + icon_space * num * Settings.scale,
                    this.tY + fontFix_Y * Settings.scale + icon_space * Settings.scale,
                    this.c, this.fontScale);
            num++;
        }

        if (haloAmount >= 3) {
            sb.draw(defendImg,
                    this.tX + imgFix_X * Settings.scale + icon_space * num * Settings.scale - 32.0f,
                    this.tY + imgFix_Y * Settings.scale + icon_space * Settings.scale - 32.0f,
                    32.0F, 32.0F, 64.0F, 64.0F, imgScale, imgScale, 0.0f, 0, 0, 64, 64, false, false);

            FontHelper.renderFontCentered(sb,
                    FontHelper.cardEnergyFont_L,
                    Integer.toString(this.blockAmount),
                    this.tX + fontFix_X * Settings.scale + icon_space * num * Settings.scale,
                    this.tY + fontFix_Y * Settings.scale + icon_space * Settings.scale,
                    this.c, this.fontScale);
        }


    }


    //    =====================工具函数
    public void evokeDiamond(int amount) {
        int diamondsNum = getCurrentDiamond();
        if (diamondsNum <= 10 || amount <= 0) {
            return;
        }

        if (diamondsNum < amount) {
            amount = diamondsNum;
        }

        for (int i = 0; i < amount; i++) {
            AbstractDiamond.evokeDiamond();
        }
    }


    public void createDiamond(int amount) {
        System.out.println("createDiamond运行了");
        int diamondsNum = getCurrentDiamond();
        int extraDia = 0;
        if (diamondsNum >= 10 || amount <= 0) {
            return;
        }

        if (diamondsNum + amount > 10) {
            extraDia = diamondsNum + amount - 10;
            amount = 10 - diamondsNum;
        }

        for (int i = 0; i < amount; i++) {
            AbstractDiamond.addDiamond();
        }

        if (extraDia > 0) {
            for (int i = 0; i < extraDia; i++) {
                AbstractDungeon.actionManager.addToTop(new DarkOrbEvokeAction(
                        new DamageInfo(AbstractDungeon.player, this.evokeAmount, DamageInfo.DamageType.THORNS),
                        AbstractGameAction.AttackEffect.FIRE));
                AbstractDungeon.topLevelEffectsQueue.add(new FlashTextureEffect(this.evokeImg,
                        this.tX + imgFix_X * Settings.scale ,
                        this.tY + imgFix_Y * Settings.scale,
                        imgScale
                ));
            }
        }
    }

    public void onStartOfTurn() {
        if (getHaloAmount() >= 2) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.cardDrawAmount));
            AbstractDungeon.topLevelEffectsQueue.add(new FlashTextureEffect(this.cardDrawImg,
                    this.tX + imgFix_X * Settings.scale + icon_space  * 1 * Settings.scale,
                    this.tY + imgFix_Y * Settings.scale + icon_space * Settings.scale,
                    imgScale
            ));
        }
        if (getHaloAmount() >= 3) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.blockAmount));
            AbstractDungeon.topLevelEffectsQueue.add(new FlashTextureEffect(this.defendImg,
                    this.tX + imgFix_X * Settings.scale + icon_space * 2 * Settings.scale,
                    this.tY + imgFix_Y * Settings.scale + icon_space * Settings.scale,
                    imgScale
            ));
        }
    }

    public void onEndOfTurn() {
        if (getHaloAmount() >= 1) {
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(null,
                    DamageInfo.createDamageMatrix(this.passiveAmount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_HEAVY));

            AbstractDungeon.topLevelEffectsQueue.add(new FlashTextureEffect(this.aoeImg,
                    this.tX + imgFix_X * Settings.scale + icon_space * 0 * Settings.scale ,
                    this.tY + imgFix_Y * Settings.scale + icon_space * Settings.scale ,
                    imgScale
            ));
        }
    }


    public int getCurrentDiamond() {
        int count = 0;
        for (AbstractDiamond di : EnergyPanelRenderPatches.PatchEnergyPanelField.diamonds.get(AbstractDungeon.overlayMenu.energyPanel)) {
            if (di.isSocket) {
                count++;
            }
        }
        return count;
    }

    public int getHaloAmount() {
        int halo = 0;
        int count = getCurrentDiamond();

        if (count >= 10) {
            halo = 3;
        } else if (count >= 6) {
            halo = 2;
        } else if (count >= 3) {
            halo = 1;
        }

        return halo;
    }


    public boolean enoughDiamond(int amount) {
        boolean enough = false;
        if(EnergyPanelRenderPatches.PatchEnergyPanelField.canUseDiamond.get(AbstractDungeon.overlayMenu.energyPanel)){
            if (getCurrentDiamond() >= amount) {
                enough = true;
            }
        }
        return enough;
    }
}


