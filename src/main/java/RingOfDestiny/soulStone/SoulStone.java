package RingOfDestiny.soulStone;

import RingOfDestiny.RingOfDestiny;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import static RingOfDestiny.RingOfDestiny.soulStoneCustomSavable;


public class SoulStone implements CustomSavable<Integer> {
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

        this.scale = 1.15f * Settings.scale;
        this.angle = 0.0f;

        this.soulStoneAmount = RingOfDestiny.soulStoneCustomSavable.soulStoneSaved;
    }


    public void addSoulStone(int amount) {
        if (amount <= 0) return;
        int num = amount;

        if (this.soulStoneAmount + num > 3)
            num = 3 - soulStoneAmount;

        this.soulStoneAmount += num;
    }

    public void evokeSoulStone(int amount) {
        if (amount <= 0) return;

        int num = amount;
        if (this.soulStoneAmount < num)
            num = soulStoneAmount;

        this.soulStoneAmount -= num;
    }

    public void update() {
        this.cX = AbstractDungeon.overlayMenu.energyPanel.current_x + 0.0f * Settings.scale;
        this.cY = AbstractDungeon.overlayMenu.energyPanel.current_y + 0.0f * Settings.scale;
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.c);

        for (int i = 0; i < this.soulStoneAmount; i++) {
            sb.draw(stoneImg[i],
                    this.cX + this.current_x - 80.0f,
                    this.cY + this.current_y - 80.0f,
                    80.0f, 80.0f, 160.0f, 160.0f, this.scale, this.scale, this.angle, 0, 0, 160, 160, false, false);

        }
    }


    @Override
    public Integer onSave() {
        System.out.println("存下来的值：" + this.soulStoneAmount );
        return null;
    }

    @Override
    public void onLoad(Integer integer) {
        System.out.println("输出的值：" + this.soulStoneAmount + "      另一个："+integer);
        soulStoneAmount = soulStoneCustomSavable.soulStoneSaved;
    }

}


