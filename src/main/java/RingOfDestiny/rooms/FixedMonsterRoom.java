package RingOfDestiny.rooms;

import RingOfDestiny.helpers.AssetLoader;
import actlikeit.patches.AbstractRoomUpdateIncrementElitesPatch;
import basemod.BaseMod;
import com.megacrit.cardcrawl.rooms.MonsterRoom;


public class FixedMonsterRoom extends MonsterRoom {
    private String encounterID;

    public FixedMonsterRoom(String encounterID, String mapImg, String mapOutlineImg) {
        this.encounterID = encounterID;
        this.setMapImg(AssetLoader.loadImage(mapImg), AssetLoader.loadImage(mapOutlineImg));
    }

    @Override
    public void onPlayerEntry() {
        this.playBGM(null);
        this.monsters = BaseMod.getMonster(encounterID);
        this.monsters.init();
        waitTimer = MonsterRoom.COMBAT_WAIT_TIME;
    }

    @Override
    public void endBattle() {
        super.endBattle();
        AbstractRoomUpdateIncrementElitesPatch.Insert(null);
    }
}
