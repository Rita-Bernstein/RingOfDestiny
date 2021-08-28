package RingOfDestiny.ui;

import RingOfDestiny.vfx.AbstractAtlasGameEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.scenes.TitleBackground;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.core.AbstractCreature.sr;

public class RingLoginBackground extends TitleBackground implements Disposable {
    private ArrayList<TextureAtlas> loginAtlas = new ArrayList<>();
    private ArrayList<Skeleton> loginSkeletons = new ArrayList<>();
    private ArrayList<AnimationStateData> loginStateDatas = new ArrayList<>();
    private ArrayList<AnimationState> loginStates = new ArrayList<>();
    private ArrayList<Float> cx = new ArrayList<>();
    private ArrayList<Float> cy = new ArrayList<>();

    private Texture bg_account = ImageMaster.loadImage("RingOfDestiny/title/bg_account.jpg");

    private AbstractAtlasGameEffect ui_login_bubble = new AbstractAtlasGameEffect("ui_login_bubble", Settings.WIDTH / 2.0f, 0.0f,
            214.0f, 65.5f, 3.805464647233427f * Settings.scale, true);

    private float display_Cx = Settings.WIDTH / 2.0f;
    private float display_Cy = Settings.HEIGHT / 2.0F;

    private static Texture titleLogoImg = null;
    private static int W = 0;
    private static int H = 0;

    private float loginScale = 1.1f;

    public RingLoginBackground() {
        addAnimation(
                "RingOfDestiny/title/ui_login_water.atlas",
                "RingOfDestiny/title/ui_login_water.json",
                0.0f,
                -10.0f * Settings.scale,
                1.5221858588933708f
        );

        addAnimation(
                "RingOfDestiny/title/ui_login_water_3.atlas",
                "RingOfDestiny/title/ui_login_water_3.json",
                0.0f,
                -10.0f * Settings.scale,
                1.5221858588933708f
        );

        addAnimation(
                "RingOfDestiny/title/ui_login_role_2.atlas",
                "RingOfDestiny/title/ui_login_role_2.json",
                0.0f,
                10.0f * Settings.scale,
                0.837202222391354f
        );

        addAnimation(
                "RingOfDestiny/title/ui_login_role_3.atlas",
                "RingOfDestiny/title/ui_login_role_3.json",
                -120.0f * Settings.scale,
                30.0f * Settings.scale,
                0.7610929294466854f
        );

        addAnimation(
                "RingOfDestiny/title/ui_login_role.atlas",
                "RingOfDestiny/title/ui_login_role.json",
                10.0f * Settings.scale,
                0.0f,
                0.837202222391354f
        );

        addAnimation(
                "RingOfDestiny/title/ui_login_water_2.atlas",
                "RingOfDestiny/title/ui_login_water_2.json",
                0.0f,
                -10.0f * Settings.scale,
                1.5221858588933708f
        );


        if (titleLogoImg == null) {
            switch (Settings.language) {
                default:
                    titleLogoImg = ImageMaster.loadImage("RingOfDestiny/title/logo.png");
                    W = titleLogoImg.getWidth();
                    H = titleLogoImg.getHeight();
            }
        }

    }

    @Override
    public void update() {
        super.update();
        ui_login_bubble.xPosition = display_Cx;
        ui_login_bubble.yPosition = - 120.0f * Settings.renderScale;

        ui_login_bubble.update();
    }

    @Override
    public void render(SpriteBatch sb) {
//        display_Cy = Settings.HEIGHT / 2.0F -70.0F * Settings.scale * this.slider;

        sb.setColor(Color.WHITE);
        sb.draw(bg_account, display_Cx - 1024.0F,
                display_Cy - 473.0F - 0.0f * Settings.scale,
                1024.0F, 473.0F,
                2048.0f, 946.0f,
                Settings.renderScale * 1.14f, Settings.renderScale * 1.14f,
                0, 0, 0, 2048, 946, false, false);


        if (loginAtlas.size() > 0) {
            for (int i = 0; i < loginAtlas.size(); i++) {
                loginStates.get(i).update(Gdx.graphics.getDeltaTime());
                loginStates.get(i).apply(loginSkeletons.get(i));
                loginSkeletons.get(i).updateWorldTransform();
                loginSkeletons.get(i).setPosition(display_Cx + cx.get(i), display_Cy + cy.get(i));
                loginSkeletons.get(i).setColor(Color.WHITE);
            }

            sb.end();
            CardCrawlGame.psb.begin();
            for (Skeleton sk : loginSkeletons)
                sr.draw(CardCrawlGame.psb, sk);
            CardCrawlGame.psb.end();
            sb.begin();

        }

        ui_login_bubble.render(sb);

        sb.draw(titleLogoImg, 980.0F * Settings.xScale - W / 2.0F,
                Settings.HEIGHT / 2.0F - H / 2.0F - 70.0F * Settings.scale * this.slider + 280.0F * Settings.scale,
                (float) W / 2.0F, (float) H / 2.0F, (float) W, (float) H,
                Settings.scale, Settings.scale,
                0.0F, 0, 0, W, H, false, false);
    }

    protected void addAnimation(String atlasUrl, String skeletonUrl, float x, float y, float scale) {
        loginAtlas.add(new TextureAtlas(Gdx.files.internal(atlasUrl)));
        SkeletonJson json = new SkeletonJson(loginAtlas.get(loginAtlas.size() - 1));

        json.setScale(Settings.renderScale * scale);
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(skeletonUrl));
        loginSkeletons.add(new Skeleton(skeletonData));
        loginSkeletons.get(loginSkeletons.size() - 1).setColor(Color.WHITE);
        loginStateDatas.add(new AnimationStateData(skeletonData));
        loginStates.add(new AnimationState(loginStateDatas.get(loginStateDatas.size() - 1)));

        loginSkeletons.get(loginSkeletons.size() - 1).setPosition(x, y);
        cx.add(x);
        cy.add(y);
        loginStates.get(loginStates.size() - 1).setAnimation(0, "huxi", true);
//        loginStates.get(loginStates.size() - 1).setTimeScale();
    }

    @Override
    public void dispose() {
        if (loginAtlas.size() > 0)
            for (TextureAtlas t : loginAtlas)
                t.dispose();
    }
}
