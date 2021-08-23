package RingOfDestiny.skinCharacters.skins.ShadowFlower;

import RingOfDestiny.skinCharacters.skins.AbstractSkin;
import RingOfDestiny.RingOfDestiny;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class ShadowFlowerSkinA extends AbstractSkin {
    public ShadowFlowerSkinA() {
        this.NAME = CardCrawlGame.languagePack.getCharacterString(RingOfDestiny.makeID("ShadowFlower")).OPTIONS[1];
        this.portraitStatic_IMG = ImageMaster.loadImage(RingOfDestiny.assetPath("characters/ShadowFlower/portrait.png"));

        this.SHOULDER1 = RingOfDestiny.assetPath("characters/ShadowFlower/shoulder.png");
        this.SHOULDER2 =  RingOfDestiny.assetPath("characters/ShadowFlower/shoulder.png");
        this.CORPSE =  "";
        this.atlasURL =  RingOfDestiny.assetPath("characters/ShadowFlower/animation/ShadowFlower_1.atlas");
        this.jsonURL = RingOfDestiny.assetPath("characters/ShadowFlower/animation/ShadowFlower_1.json");;
        this.renderscale = 1.6f;
    }
}
