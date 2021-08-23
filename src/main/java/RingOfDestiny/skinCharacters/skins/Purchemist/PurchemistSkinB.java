package RingOfDestiny.skinCharacters.skins.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.skinCharacters.skins.AbstractSkin;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class PurchemistSkinB extends AbstractSkin {
    public PurchemistSkinB() {
        this.NAME = CardCrawlGame.languagePack.getCharacterString(RingOfDestiny.makeID("Purchemist")).OPTIONS[2];
        this.portraitStatic_IMG = ImageMaster.loadImage(RingOfDestiny.assetPath("characters/Purchemist/portrait_B.png"));

        this.SHOULDER1 = RingOfDestiny.assetPath("characters/Purchemist/shoulder.png");
        this.SHOULDER2 =  RingOfDestiny.assetPath("characters/Purchemist/shoulder.png");
        this.CORPSE =  "";
        this.atlasURL =  RingOfDestiny.assetPath("characters/Purchemist/animation/Purchemist_2");
        this.jsonURL = RingOfDestiny.assetPath("characters/Purchemist/animation/Purchemist_2");;
        this.renderscale = 1.6f;
    }
}
