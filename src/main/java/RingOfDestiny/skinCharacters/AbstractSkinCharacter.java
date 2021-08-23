package RingOfDestiny.skinCharacters;


import RingOfDestiny.skinCharacters.skins.AbstractSkin;

public abstract class AbstractSkinCharacter {
    public String id;
    public String name;

    public boolean reskinUnlock = false;
    public int reskinCount = 0;

    public AbstractSkin[] skins;

    public AbstractSkinCharacter(String id, String name, AbstractSkin[] skins) {
        this.id = id;
        this.name = name;
        this.skins = skins;
    }

    public void InitializeReskinCount() {
        if (this.reskinCount < 0)
            this.reskinCount = 0;
    }

    public void checkUnlock() {

    }
}


