package RingOfDestiny.skinCharacters.skins.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.skinCharacters.skins.AbstractSkin;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class SummonerSkinB extends AbstractSkin {
    public SummonerSkinB() {
        this.NAME = CardCrawlGame.languagePack.getCharacterString(RingOfDestiny.makeID("Summoner")).OPTIONS[2];
        this.portraitStatic_IMG = ImageMaster.loadImage(RingOfDestiny.assetPath("characters/Summoner/portrait_B.png"));

        this.SHOULDER1 = RingOfDestiny.assetPath("characters/Summoner/shoulder.png");
        this.SHOULDER2 =  RingOfDestiny.assetPath("characters/Summoner/shoulder.png");
        this.CORPSE =  "";
        this.atlasURL =  RingOfDestiny.assetPath("characters/Summoner/animation/Summoner_2.atlas");
        this.jsonURL = RingOfDestiny.assetPath("characters/Summoner/animation/Summoner_2.json");;
        this.renderscale = 1.6f;
    }
}
