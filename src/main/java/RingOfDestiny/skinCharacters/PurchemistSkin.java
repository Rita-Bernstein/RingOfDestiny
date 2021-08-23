package RingOfDestiny.skinCharacters;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.patches.AbstractPlayerEnum;
import RingOfDestiny.skinCharacters.skins.AbstractSkin;
import RingOfDestiny.skinCharacters.skins.Purchemist.PurchemistSkinA;
import RingOfDestiny.skinCharacters.skins.Purchemist.PurchemistSkinB;
import RingOfDestiny.skinCharacters.skins.ShadowFlower.ShadowFlowerSkinA;
import RingOfDestiny.skinCharacters.skins.ShadowFlower.ShadowFlowerSkinB;
import RingOfDestiny.vfx.ReskinUnlockedTextEffect;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PurchemistSkin extends AbstractSkinCharacter {
    public static final String ID = RingOfDestiny.makeID("ShadowFlowerSkin");
    public static final String NAME = CardCrawlGame.languagePack.getCharacterString(RingOfDestiny.makeID("Purchemist")).NAMES[0];
    public static final AbstractSkin[] SKINS = new AbstractSkin[]{
            new PurchemistSkinA(),
            new PurchemistSkinB()
    };

    public PurchemistSkin() {
        super(ID, NAME, SKINS);
    }

    @Override
    public void checkUnlock() {
        if (AbstractDungeon.player.chosenClass == AbstractPlayerEnum.ShadowFlower && !this.reskinUnlock) {
            AbstractDungeon.topLevelEffects.add(new ReskinUnlockedTextEffect(CardCrawlGame.languagePack.getCharacterString(RingOfDestiny.makeID("Purchemist")).OPTIONS[0]));
            this.reskinUnlock = true;
        }
    }
}
