package RingOfDestiny.skinCharacters.skins.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.skinCharacters.skins.AbstractSkin;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class SummonerSkinA extends AbstractSkin {
    public SummonerSkinA() {
        this.NAME = CardCrawlGame.languagePack.getCharacterString(RingOfDestiny.makeID("Summoner")).OPTIONS[1];
        this.portraitStatic_IMG = ImageMaster.loadImage(RingOfDestiny.assetPath("characters/Summoner/portrait.png"));

        this.SHOULDER1 = RingOfDestiny.assetPath("characters/Summoner/shoulder.png");
        this.SHOULDER2 =  RingOfDestiny.assetPath("characters/Summoner/shoulder.png");
        this.CORPSE =  "";
        this.atlasURL =  RingOfDestiny.assetPath("characters/Summoner/animation/Summoner_1.atlas");
        this.jsonURL = RingOfDestiny.assetPath("characters/Summoner/animation/Summoner_1.json");;
        this.renderscale = 1.6f;
    }
}
