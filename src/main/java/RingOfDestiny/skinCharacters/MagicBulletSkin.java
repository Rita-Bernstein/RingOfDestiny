package RingOfDestiny.skinCharacters;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.patches.AbstractPlayerEnum;
import RingOfDestiny.skinCharacters.skins.AbstractSkin;
import RingOfDestiny.skinCharacters.skins.MagicBullet.*;
import RingOfDestiny.vfx.ReskinUnlockedTextEffect;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MagicBulletSkin extends AbstractSkinCharacter {
    public static final String ID = RingOfDestiny.makeID("MagicBulletSkin");
    public static final String NAME = CardCrawlGame.languagePack.getCharacterString(RingOfDestiny.makeID("MagicBullet")).NAMES[0];
    public static final AbstractSkin[] SKINS = new AbstractSkin[]{
            new MagicBulletSkinD(),
            new MagicBulletSkinB(),
            new MagicBulletSkinC(),
            new MagicBulletSkinE(),
            new MagicBulletSkinA()
    };

    public MagicBulletSkin() {
        super(ID, NAME, SKINS);
    }

    @Override
    public void checkUnlock() {
        if (AbstractDungeon.player.chosenClass == AbstractPlayerEnum.ShadowFlower && !this.reskinUnlock) {
            AbstractDungeon.topLevelEffects.add(new ReskinUnlockedTextEffect(CardCrawlGame.languagePack.getCharacterString(RingOfDestiny.makeID("MagicBullet")).OPTIONS[0]));
            this.reskinUnlock = true;
        }
    }
}
