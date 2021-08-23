package RingOfDestiny.skinCharacters.skins.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.skinCharacters.skins.AbstractSkin;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class InheritSkinA extends AbstractSkin {
    public InheritSkinA() {
        this.NAME = CardCrawlGame.languagePack.getCharacterString(RingOfDestiny.makeID("Inherit")).OPTIONS[1];
        this.portraitStatic_IMG = ImageMaster.loadImage(RingOfDestiny.assetPath("characters/Inherit/portrait.png"));

        this.SHOULDER1 = RingOfDestiny.assetPath("characters/Inherit/shoulder.png");
        this.SHOULDER2 =  RingOfDestiny.assetPath("characters/Inherit/shoulder.png");
        this.CORPSE =  "";
        this.atlasURL =  RingOfDestiny.assetPath("characters/Inherit/animation/Inherit_1");
        this.jsonURL = RingOfDestiny.assetPath("characters/Inherit/animation/Inherit_1");;
        this.renderscale = 1.6f;
    }
}
