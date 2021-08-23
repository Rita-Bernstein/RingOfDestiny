package RingOfDestiny.skinCharacters.skins.MagicBullet;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.skinCharacters.skins.AbstractSkin;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class MagicBulletSkinB extends AbstractSkin {
    public MagicBulletSkinB() {
        this.NAME = CardCrawlGame.languagePack.getCharacterString(RingOfDestiny.makeID("MagicBullet")).OPTIONS[2];
        this.portraitStatic_IMG = ImageMaster.loadImage(RingOfDestiny.assetPath("characters/MagicBullet/portrait_B.png"));

        this.SHOULDER1 = RingOfDestiny.assetPath("characters/MagicBullet/shoulder.png");
        this.SHOULDER2 =  RingOfDestiny.assetPath("characters/MagicBullet/shoulder.png");
        this.CORPSE =  "";
        this.atlasURL =  RingOfDestiny.assetPath("characters/MagicBullet/animation/MagicBullet_2.atlas");
        this.jsonURL = RingOfDestiny.assetPath("characters/MagicBullet/animation/MagicBullet_2.json");;
        this.renderscale = 1.6f;
    }
}
