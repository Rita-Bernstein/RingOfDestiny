package RingOfDestiny.subEnergy;


import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.SwitchFormAction;
import RingOfDestiny.actions.Purchemist.UseDiamondAction;
import RingOfDestiny.actions.Summoner.StrengthToMetallicizeAction;
import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.cards.Purchemist.DoubleInvest;
import RingOfDestiny.cards.Purchemist.NoInvest;
import RingOfDestiny.character.Inherit;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.patches.SubEnergyVfxPatches;
import RingOfDestiny.powers.*;
import RingOfDestiny.relics.HolyStarSeal;
import RingOfDestiny.relics.Truncheon;
import RingOfDestiny.vfx.FlashTextureEffect;
import RingOfDestiny.vfx.RefreshEnergyBetterEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class SubEnergy {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(RingOfDestiny.makeID("SubEnergy"));
    public static final String NAME = uiStrings.TEXT[0];
    public static final String DESCRIPTION = uiStrings.EXTRA_TEXT[0];
    public static final String DESCRIPTION2 = uiStrings.EXTRA_TEXT[1];

    public float tX;
    public float tY;
    public static float orbFix_x = -50.0f * Settings.scale;
    public static float orbFix_y = -50.0f * Settings.scale;

    public Hitbox hb = new Hitbox(60.0F * Settings.scale, 60.0F * Settings.scale);
    protected float fontScale = 0.7F;
    protected float defaultFontScale = 0.7F;

    private Color colorW = Color.WHITE.cpy();
    private static final Color ENERGY_TEXT_COLOR = new Color(1.0F, 1.0F, 0.86F, 1.0F);

    private Texture energyBg = ImageMaster.loadImage("RingOfDestiny/img/ui/topPanel/Inherit/energyBg.png");
    private Texture tsundereBg = ImageMaster.loadImage("RingOfDestiny/img/ui/topPanel/Inherit/tsundereBg.png");
    private Texture gainEnergyImg = AbstractDungeon.player.getEnergyImage();
    private Texture gainEnergyImg2 = ImageMaster.loadImage("RingOfDestiny/img/ui/topPanel/Purchemist/energyVFX.png");

    public static final float imgScale = 0.40f * Settings.scale;

    private float energyVfxAngle = 0.0F;
    private float energyVfxScale = Settings.scale;
    private Color energyVfxColor = Color.WHITE.cpy();
    public static float energyVfxTimer = 0.0F;

    public int totalCount = 0;
    public static int maxCount = 10;

    public SubEnergy() {

    }


    public void update() {
        updateVfx();

        if (EnergyPanelRenderPatches.PatchEnergyPanelField.canUseSoulStone.get(AbstractDungeon.overlayMenu.energyPanel)) {
            orbFix_x = -2.0f * Settings.scale;
            orbFix_y = -60.0f * Settings.scale;
        } else {
            orbFix_x = orbFix_y = -50.0f * Settings.scale;
        }

        this.tX = AbstractDungeon.overlayMenu.energyPanel.current_x + orbFix_x;
        this.tY = AbstractDungeon.overlayMenu.energyPanel.current_y + orbFix_y;

        this.hb.move(this.tX, this.tY);
        this.hb.update();

        if (this.hb.hovered) {
            if (getCurrentForm()) {
                TipHelper.renderGenericTip(this.tX - 140.0F * Settings.scale, this.tY + 220.0F * Settings.scale, EnergyPanel.LABEL[0], EnergyPanel.MSG[0]);
            } else {
                TipHelper.renderGenericTip(this.tX - 140.0F * Settings.scale, this.tY + 220.0F * Settings.scale, NAME, DESCRIPTION + DESCRIPTION2);
            }
        }


        updateClick();
    }

    public void render(SpriteBatch sb) {
        renderOrb(sb);

        this.hb.render(sb);

        String energyMsg = totalCount + "";
        AbstractDungeon.player.getEnergyNumFont().getData().setScale(defaultFontScale);

        if (getCurrentForm()) {
            this.renderOriginalVfx(sb);
            AbstractDungeon.player.getEnergyNumFont().getData().setScale(EnergyPanel.fontScale * defaultFontScale);
            FontHelper.renderFontCentered(sb, AbstractDungeon.player.getEnergyNumFont(), EnergyPanel.totalCount + "", this.tX, this.tY, ENERGY_TEXT_COLOR);
        } else {
            if (!EnergyPanelRenderPatches.PatchEnergyPanelField.isInDark.get(AbstractDungeon.overlayMenu.energyPanel))
                renderVfx(sb);
            FontHelper.renderFontCentered(sb, AbstractDungeon.player.getEnergyNumFont(), energyMsg, this.tX, this.tY, ENERGY_TEXT_COLOR);
        }


    }

    public void bottonRender(SpriteBatch sb) {
        if (EnergyPanelRenderPatches.PatchEnergyPanelField.isInDark.get(AbstractDungeon.overlayMenu.energyPanel))
            renderVfx(sb);
    }

    private void updateVfx() {
        if (energyVfxTimer != 0.0F) {
            this.energyVfxColor.a = Interpolation.exp10In.apply(0.5F, 0.0F, 1.0F - energyVfxTimer / 2.0F);
            this.energyVfxAngle += Gdx.graphics.getDeltaTime() * -30.0F;
            this.energyVfxScale = Settings.scale * Interpolation.exp10In.apply(1.0F, 0.1F, 1.0F - energyVfxTimer / 2.0F);


            energyVfxTimer -= Gdx.graphics.getDeltaTime();
            if (energyVfxTimer < 0.0F) {
                energyVfxTimer = 0.0F;
                this.energyVfxColor.a = 0.0F;
            }
        }
    }

    protected void renderOrb(SpriteBatch sb) {

        sb.setColor(this.colorW);

        if (getCurrentForm()) {
            sb.draw(energyBg,
                    this.tX - 78.0f,
                    this.tY - 78.0f,
                    78.0F, 78.0F, 156.0F, 156.0F, imgScale, imgScale, 0.0f, 0, 0, 156, 156, false, false);
        } else {
            sb.draw(tsundereBg,
                    this.tX - 78.0f,
                    this.tY - 78.0f,
                    78.0F, 78.0F, 156.0F, 156.0F, imgScale, imgScale, 0.0f, 0, 0, 156, 156, false, false);
        }


    }

    private void renderVfx(SpriteBatch sb) {
        if (energyVfxTimer != 0.0F) {
            sb.setBlendFunction(770, 1);
            sb.setColor(this.energyVfxColor);
            if (getCurrentForm()) {
                float scale = energyVfxScale;
                sb.draw(gainEnergyImg2, AbstractDungeon.overlayMenu.energyPanel.current_x - 128.0F, AbstractDungeon.overlayMenu.energyPanel.current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, scale, scale, -energyVfxAngle + 50.0F, 0, 0, 256, 256, true, false);
                sb.draw(gainEnergyImg2, AbstractDungeon.overlayMenu.energyPanel.current_x - 128.0F, AbstractDungeon.overlayMenu.energyPanel.current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, scale, scale, energyVfxAngle, 0, 0, 256, 256, false, false);

            } else {
                float scale = energyVfxScale * imgScale * 2.0f;
                sb.draw(gainEnergyImg2, this.tX - 128.0F, this.tY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, scale, scale, -energyVfxAngle + 50.0F, 0, 0, 256, 256, true, false);
                sb.draw(gainEnergyImg2, this.tX - 128.0F, this.tY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, scale, scale, energyVfxAngle, 0, 0, 256, 256, false, false);
            }

            sb.setBlendFunction(770, 771);
        }
    }

    private void renderOriginalVfx(SpriteBatch sb) {
        if (EnergyPanel.energyVfxTimer != 0.0F) {
            sb.setBlendFunction(770, 1);
            sb.setColor(SubEnergyVfxPatches.ExtraEnergyPanelField.energyVfxColor.get(AbstractDungeon.overlayMenu.energyPanel));

            float oriEnergyVfxAngle = SubEnergyVfxPatches.ExtraEnergyPanelField.energyVfxAngle.get(AbstractDungeon.overlayMenu.energyPanel);
            float oriEnergyVfxScale = SubEnergyVfxPatches.ExtraEnergyPanelField.energyVfxScale.get(AbstractDungeon.overlayMenu.energyPanel) * imgScale * 2.0f;
            sb.draw(gainEnergyImg, AbstractDungeon.overlayMenu.energyPanel.current_x + orbFix_x - 128.0F, AbstractDungeon.overlayMenu.energyPanel.current_y + orbFix_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, oriEnergyVfxScale, oriEnergyVfxScale, -oriEnergyVfxAngle + 50.0F, 0, 0, 256, 256, true, false);

            sb.draw(gainEnergyImg, AbstractDungeon.overlayMenu.energyPanel.current_x + orbFix_x - 128.0F, AbstractDungeon.overlayMenu.energyPanel.current_y + orbFix_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, oriEnergyVfxScale, oriEnergyVfxScale, oriEnergyVfxAngle, 0, 0, 256, 256, false, false);

            sb.setBlendFunction(770, 771);
        }
    }


    //    =====================工具函数
    public void setEnergy(int energy) {
        totalCount = energy;
        if (getCurrentForm()) {
            AbstractDungeon.effectsQueue.add(new RefreshEnergyBetterEffect(
                    AbstractDungeon.overlayMenu.energyPanel.current_x,
                    AbstractDungeon.overlayMenu.energyPanel.current_y,
                    1.0f
            ));
        } else {
            AbstractDungeon.effectsQueue.add(
                    new RefreshEnergyBetterEffect(
                            AbstractDungeon.overlayMenu.energyPanel.current_x + orbFix_x,
                            AbstractDungeon.overlayMenu.energyPanel.current_y + orbFix_y,
                            SubEnergy.imgScale * 2.0f));
        }

        energyVfxTimer = 2.0F;
        fontScale = 2.0F * defaultFontScale;
    }


    public void addSubEnergy(int amount, boolean isRelic) {
        if (totalCount == maxCount || amount <= 0) {
            return;
        }

        if (totalCount + amount > maxCount) {
            totalCount = 10;
        } else {
            totalCount += amount;
        }

        if (getCurrentForm()) {
            AbstractDungeon.effectsQueue.add(new RefreshEnergyBetterEffect(
                    AbstractDungeon.overlayMenu.energyPanel.current_x,
                    AbstractDungeon.overlayMenu.energyPanel.current_y,
                    1.0f
            ));
        } else {
            AbstractDungeon.effectsQueue.add(
                    new RefreshEnergyBetterEffect(
                            AbstractDungeon.overlayMenu.energyPanel.current_x + orbFix_x,
                            AbstractDungeon.overlayMenu.energyPanel.current_y + orbFix_y,
                            SubEnergy.imgScale * 2.0f));
        }

        fontScale = 2.0F * defaultFontScale;
        energyVfxTimer = 2.0F;
    }

    public void useSubEnergy(int amount) {
        if (totalCount == 0 || amount <= 0) {
            return;
        }
        fontScale = 2.0F * defaultFontScale;

        if (totalCount < amount) {
            totalCount = 0;
        } else {
            totalCount -= amount;
        }

    }


    public void useEnergy(int e) {
        totalCount -= e;

        if (totalCount < 0) {
            totalCount = 0;
        }


    }


    public void reset() {
        totalCount = 0;
        EnergyPanelRenderPatches.PatchEnergyPanelField.isInDark.set(AbstractDungeon.overlayMenu.energyPanel, false);
        switchForm(false, true,true);
    }


    public boolean enoughSubEnergy(int amount) {
        boolean enough = false;
        if (EnergyPanelRenderPatches.PatchEnergyPanelField.canUseSubEnergy.get(AbstractDungeon.overlayMenu.energyPanel)) {
            if (amount <= totalCount) {
                enough = true;
            }
        }
        return enough;
    }


    public void updateClick() {
        if (InputHelper.justClickedLeft && this.hb.hovered && !AbstractDungeon.player.isDraggingCard && AbstractDungeon.player.hoveredCard == null) {
            this.hb.clickStarted = true;
        }

        if (hb.clicked) {
            hb.clicked = false;
            if (!AbstractDungeon.actionManager.turnHasEnded)
                AbstractDungeon.actionManager.addToBottom(new SwitchFormAction());
        }
    }


    public void switchForm(boolean isInDark) {
        switchForm(isInDark, false, false);
    }

    public void switchForm(boolean isInDark, boolean changeForFree) {
        switchForm(isInDark, changeForFree, false);
    }

    public void switchForm(boolean switchToDark, boolean changeForFree, boolean reset) {
        if (switchToDark == EnergyPanelRenderPatches.PatchEnergyPanelField.isInDark.get(AbstractDungeon.overlayMenu.energyPanel) && !reset) {
            return;
        }

        if (switchToDark) {
            boolean canSwitch = false;
            if (EnergyPanel.totalCount > 0 || changeForFree) {
                canSwitch = true;
            }

            if (canSwitch) {
                EnergyPanelRenderPatches.PatchEnergyPanelField.isInDark.set(AbstractDungeon.overlayMenu.energyPanel, true);
                if (!changeForFree)
                    AbstractDungeon.player.loseEnergy(1);

                if (AbstractDungeon.player instanceof Inherit) {
                    ((Inherit) AbstractDungeon.player).switchFromAnimation(true);
                }


            } else {
                AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F,
                        CardCrawlGame.languagePack.getUIString("SingleCardViewPopup").TEXT[11], true));
            }

        } else {
            EnergyPanelRenderPatches.PatchEnergyPanelField.isInDark.set(AbstractDungeon.overlayMenu.energyPanel, false);
            if (AbstractDungeon.player instanceof Inherit) {
                ((Inherit) AbstractDungeon.player).switchFromAnimation(false);
            }
        }

        switchCard(switchToDark);

        if(!AbstractDungeon.player.hasRelic(HolyStarSeal.ID) && switchToDark)
        AbstractDungeon.actionManager.addToTop(new StrengthToMetallicizeAction());
    }

    public void switchCard(boolean switchToDark) {
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card instanceof AbstractInheritCard) {
                ((AbstractInheritCard) card).initializeForm(switchToDark);
            }
        }

        for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
            if (card instanceof AbstractInheritCard) {
                ((AbstractInheritCard) card).initializeForm(switchToDark);
            }
        }

        for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
            if (card instanceof AbstractInheritCard) {
                ((AbstractInheritCard) card).initializeForm(switchToDark);
            }
        }

        for (AbstractCard card : AbstractDungeon.player.exhaustPile.group) {
            if (card instanceof AbstractInheritCard) {
                ((AbstractInheritCard) card).initializeForm(switchToDark);
            }
        }
    }

    public boolean getCurrentForm() {
        return EnergyPanelRenderPatches.PatchEnergyPanelField.isInDark.get(AbstractDungeon.overlayMenu.energyPanel);
    }
}


