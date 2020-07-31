package RingOfDestiny.soulStone;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;


public class SoulStone {
    public float cX = 0.0F;
    public float cY = 0.0F;
    protected float current_x = 0.0f;
    protected float current_y = 0.0f;
    protected Color c;
    protected float angle;
    protected float scale;
    public int soulStoneAmount;

    private Texture[] stoneImg = new Texture[]{
            ImageMaster.loadImage("RingOfDestiny/img/ui/topPanel/Summoner/soulStone_1.png"),
            ImageMaster.loadImage("RingOfDestiny/img/ui/topPanel/Summoner/soulStone_2.png"),
            ImageMaster.loadImage("RingOfDestiny/img/ui/topPanel/Summoner/soulStone_3.png")
    };


    public SoulStone() {
        this.c = Settings.CREAM_COLOR.cpy();
        this.soulStoneAmount = 0;
        this.scale = 1.15f * Settings.scale;
        this.angle = 0.0f;
    }


    public void addSoulStone() {
        if (this.soulStoneAmount <= 3)
            this.soulStoneAmount++;
    }

    public void evokeSoulStone() {
        if (this.soulStoneAmount >= 1) ;
        this.soulStoneAmount--;
    }

    public void update() {
        this.cX = AbstractDungeon.overlayMenu.energyPanel.current_x + 0.0f * Settings.scale;
        this.cY = AbstractDungeon.overlayMenu.energyPanel.current_y + 0.0f * Settings.scale;
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.c);

        for (int i = 0; i < this.soulStoneAmount ; i++) {
            sb.draw(stoneImg[i],
                    this.cX + this.current_x - 80.0f,
                    this.cY + this.current_y - 80.0f,
                    80.0f, 80.0f, 160.0f, 160.0f, this.scale, this.scale, this.angle, 0, 0, 160, 160, false, false);

        }
    }
}


