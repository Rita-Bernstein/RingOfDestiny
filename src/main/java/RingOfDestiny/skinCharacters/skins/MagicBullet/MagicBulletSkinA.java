package RingOfDestiny.skinCharacters.skins.MagicBullet;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.skinCharacters.skins.AbstractSkin;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class MagicBulletSkinA extends AbstractSkin {
    public MagicBulletSkinA() {
        this.NAME = CardCrawlGame.languagePack.getCharacterString(RingOfDestiny.makeID("MagicBullet")).OPTIONS[1];
        this.portraitStatic_IMG = ImageMaster.loadImage(RingOfDestiny.assetPath("characters/MagicBullet/portrait_D.png"));

        this.SHOULDER1 = RingOfDestiny.assetPath("characters/MagicBullet/shoulder.png");
        this.SHOULDER2 =  RingOfDestiny.assetPath("characters/MagicBullet/shoulder.png");
        this.CORPSE =  "";
        this.atlasURL =  RingOfDestiny.assetPath("characters/MagicBullet/animation/MagicBullet_1.atlas");
        this.jsonURL = RingOfDestiny.assetPath("characters/MagicBullet/animation/MagicBullet_1.json");;
        this.renderscale = 1.6f;
    }
}
