package RingOfDestiny.actions.unique;

import RingOfDestiny.vfx.BackgroundBoardRender;
import RingOfDestiny.vfx.BackgroundRender;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ChangeStageAction extends AbstractGameAction {
    public ChangeStageAction(String id) {

        AbstractDungeon.effectList.clear();

        switch (id) {
            case "Black1":
                addToBot(new VFXAction(new BackgroundRender("Black1", 10, 0.0F, 0.55F, 768, 259, 2.5f, 8.0f), 0.0F));
                break;
            case "Black2":
                addToBot(new VFXAction(new BackgroundRender("Black2", 14, 0.0F, 0.55F, 768, 254, 2.5f, 8.0f), 0.0F));
                break;
            case "ZERO":
                addToBot(new VFXAction(new BackgroundRender("ZERO", 8, 0.0F, 0.55F, 752, 224, 3.0f, 8.0f), 0.0F));
                break;
            case "Rocket":
                addToBot(new VFXAction(new BackgroundRender("Rocket", 16, 0.0F, 0.55F, 740, 224, 3.0f, 10.0f), 0.0F));
                break;
        }

        addToBot(new VFXAction(new BackgroundBoardRender(0.0f, 1.04f, 0.0f, 0.15f, 1.5f)));

    }

    public void update() {
        this.tickDuration();
    }
}