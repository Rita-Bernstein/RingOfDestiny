package RingOfDestiny.skinCharacters.skins.MagicBullet;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.skinCharacters.skins.AbstractSkin;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class MagicBulletSkinE extends AbstractSkin {
    public MagicBulletSkinE() {
        this.NAME = CardCrawlGame.languagePack.getCharacterString(RingOfDestiny.makeID("MagicBullet")).OPTIONS[5];
        this.portraitStatic_IMG = ImageMaster.loadImage(RingOfDestiny.assetPath("characters/MagicBullet/portrait_E.png"));

        this.SHOULDER1 = RingOfDestiny.assetPath("characters/MagicBullet/shoulder.png");
        this.SHOULDER2 =  RingOfDestiny.assetPath("characters/MagicBullet/shoulder.png");
        this.CORPSE =  "";
        this.atlasURL =  RingOfDestiny.assetPath("characters/MagicBullet/animation/MagicBullet_5.atlas");
        this.jsonURL = RingOfDestiny.assetPath("characters/MagicBullet/animation/MagicBullet_5.json");;
        this.renderscale = 1.6f;
    }
}
