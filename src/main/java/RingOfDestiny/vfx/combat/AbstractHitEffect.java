package RingOfDestiny.vfx.combat;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.DamageImpactCurvyEffect;


public class AbstractHitEffect extends AbstractGameEffect {
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


    public AbstractHitEffect(String EffectType) {



    }

    public void update() {
        super.update();

        AbstractDungeon.effectsQueue.add(new DamageImpactCurvyEffect(this.x + img.packedWidth / 2.0F, this.y + img.packedWidth / 2.0F));
              }

    public void render(SpriteBatch sb) {
           }
       public void dispose() {}
    }

