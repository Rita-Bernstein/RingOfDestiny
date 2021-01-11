package RingOfDestiny.vfx.combat;

import RingOfDestiny.RingOfDestiny;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import jdk.nashorn.internal.runtime.arrays.ArrayIndex;


public class Kaiser1Effect extends AbstractGameEffect {
    private float x;
    private static TextureAtlas.AtlasRegion img = null;
    private float y;
    private static Texture[] ZERO = new Texture[100];
    private int time = 0;
    private float timer = 0;
    private float playSpeed = 0;
    private int gifLength = 0;
    private int gifWidth = 0;
    private int gifHeight = 0;
    private int imageWidth = 47;
    private int imageHeight = 48;
    private float gifScale = 3.5f;
    private int gifFrame = 5;
    private float Frameduration = 32.0f;
    private String  id = "Kaiser1";
    private boolean  soundPlayed  = false;
    private float  tanX = 0.0f;
    private float  tanY = 0.0f;


    public Kaiser1Effect(float fixX,float fixY,float thisX,float thisY) {

        for (int i = 0; i < gifFrame; i++) {
            ZERO[i] = new Texture(String.format(RingOfDestiny.assetPath("img/vfx/")+ id +"/frame_" + "%01d.png", new Object[]{Integer.valueOf(i)}));
        }

        this.renderBehind = false;
        img = new TextureAtlas.AtlasRegion(ZERO[0],0,0,imageWidth,imageHeight);
        this.startingDuration = 2.0F;
        this.duration = this.startingDuration;
        this.scale = Settings.scale * gifScale;

        this.x = thisX - img.packedWidth / 2.0F;//.WIDTH * 0.5F - img.packedWidth / 2.0F +Settings.WIDTH * fixX;
        this.y = thisY - img.packedHeight / 2.0F;//-img.packedHeight / 2.0F +Settings.HEIGHT * fixY;

        this.color = Color.WHITE.cpy();
        this.playSpeed = Frameduration;
        this.gifLength = gifFrame;
        this.gifWidth = imageWidth;
        this.gifHeight = imageHeight;

        this.tanX =  (fixX-thisX);
        this.tanY =  (fixY-thisY);

    }

    public void update() {
        super.update();
        if (time == gifFrame -1){
            //this.isDone = true  ;
            //this.duration = 0;
        }else {
            this.isDone = false;
        }



        timer += (Gdx.graphics.getDeltaTime()* this.playSpeed) ;


        if(this.gifLength-1 <= 0){
            time = 0;
        }else{
            time =  (int)timer % (this.gifLength);
        }

                this.x += (Gdx.graphics.getDeltaTime()* this.playSpeed)* 0.20F * tanX;
                this.y += (Gdx.graphics.getDeltaTime()* this.playSpeed) *0.20F * tanY;

        img = new TextureAtlas.AtlasRegion(ZERO[time],0,0,this.gifWidth,this.gifHeight);


        if(!soundPlayed){
            CardCrawlGame.sound.playA(RingOfDestiny.makeID("Kaiser"), MathUtils.random(0.0F, -0.15F));
            soundPlayed = true;
        }
              }

    public void render(SpriteBatch sb) {
         sb.setColor( Color.WHITE.cpy());
             sb.draw(img, this.x, this.y, img.packedWidth / 2.0f, img.packedHeight / 2.0f, img.packedWidth, img.packedHeight, this.scale, this.scale, 0.0f);


           }
       public void dispose() {}
    }

