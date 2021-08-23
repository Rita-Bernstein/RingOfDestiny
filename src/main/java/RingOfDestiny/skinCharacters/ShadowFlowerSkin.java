package RingOfDestiny.skinCharacters;

import RingOfDestiny.skinCharacters.skins.AbstractSkin;
import RingOfDestiny.vfx.ReskinUnlockedTextEffect;
import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.patches.AbstractPlayerEnum;
import RingOfDestiny.skinCharacters.skins.ShadowFlower.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ShadowFlowerSkin extends AbstractSkinCharacter {
    public static final String ID = RingOfDestiny.makeID("ShadowFlowerSkin");
    public static final String NAME = CardCrawlGame.languagePack.getCharacterString(RingOfDestiny.makeID("ShadowFlower")).NAMES[0];
    public static final AbstractSkin[] SKINS = new AbstractSkin[]{
            new ShadowFlowerSkinA(),
            new ShadowFlowerSkinB()
    };

    public ShadowFlowerSkin() {
        super(ID, NAME, SKINS);
    }

    @Override
    public void checkUnlock() {
        if (AbstractDungeon.player.chosenClass == AbstractPlayerEnum.ShadowFlower && !this.reskinUnlock) {
            AbstractDungeon.topLevelEffects.add(new ReskinUnlockedTextEffect(CardCrawlGame.languagePack.getCharacterString(RingOfDestiny.makeID("ShadowFlower")).OPTIONS[0]));
            this.reskinUnlock = true;
        }
    }
}
