package RingOfDestiny.character;

import basemod.abstracts.CustomPlayer;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;

public abstract class AbstractRingCharacter extends CustomPlayer {

    public AbstractRingCharacter(String name, PlayerClass playerClass, EnergyOrbInterface energyOrbInterface, String model, String animation){
        super(name, playerClass, energyOrbInterface, model, animation);
    }
}
