package RingOfDestiny.skinCharacters;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.patches.AbstractPlayerEnum;
import RingOfDestiny.skinCharacters.skins.AbstractSkin;
import RingOfDestiny.skinCharacters.skins.Inherit.InheritSkinA;
import RingOfDestiny.skinCharacters.skins.Inherit.InheritSkinB;
import RingOfDestiny.skinCharacters.skins.Summoner.SummonerSkinA;
import RingOfDestiny.skinCharacters.skins.Summoner.SummonerSkinB;
import RingOfDestiny.vfx.ReskinUnlockedTextEffect;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class InheritSkin extends AbstractSkinCharacter {
    public static final String ID = RingOfDestiny.makeID("InheritSkin");
    public static final String NAME = CardCrawlGame.languagePack.getCharacterString(RingOfDestiny.makeID("Inherit")).NAMES[0];
    public static final AbstractSkin[] SKINS = new AbstractSkin[]{
            new InheritSkinA(),
            new InheritSkinB()
    };

    public InheritSkin() {
        super(ID, NAME, SKINS);
    }

    @Override
    public void checkUnlock() {
        if (AbstractDungeon.player.chosenClass == AbstractPlayerEnum.ShadowFlower && !this.reskinUnlock) {
            AbstractDungeon.topLevelEffects.add(new ReskinUnlockedTextEffect(CardCrawlGame.languagePack.getCharacterString(RingOfDestiny.makeID("Inherit")).OPTIONS[0]));
            this.reskinUnlock = true;
        }
    }
}
