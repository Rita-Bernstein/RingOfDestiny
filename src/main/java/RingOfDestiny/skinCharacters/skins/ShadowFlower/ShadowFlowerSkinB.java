package RingOfDestiny.skinCharacters.skins.ShadowFlower;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.skinCharacters.skins.AbstractSkin;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class ShadowFlowerSkinB extends AbstractSkin {
    public ShadowFlowerSkinB() {
        this.NAME = CardCrawlGame.languagePack.getCharacterString(RingOfDestiny.makeID("ShadowFlower")).OPTIONS[2];
        this.portraitStatic_IMG = ImageMaster.loadImage(RingOfDestiny.assetPath("characters/ShadowFlower/portrait_B.png"));

        this.SHOULDER1 = RingOfDestiny.assetPath("characters/ShadowFlower/shoulder.png");
        this.SHOULDER2 =  RingOfDestiny.assetPath("characters/ShadowFlower/shoulder.png");
        this.CORPSE =  "";
        this.atlasURL =  RingOfDestiny.assetPath("characters/ShadowFlower/animation/ShadowFlower_2.atlas");
        this.jsonURL = RingOfDestiny.assetPath("characters/ShadowFlower/animation/ShadowFlower_2.json");;
        this.renderscale = 1.6f;
    }
}
