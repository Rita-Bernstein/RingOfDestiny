package RingOfDestiny.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class RainbowColor extends Color {

    public RainbowColor(float r, float g, float b) {
        this(r, g, b, 1F);
    }
    public RainbowColor(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    public RainbowColor(Color color) {
        this(color.r, color.g, color.b, color.a);
    }

    public void update() {
        float velocity = Gdx.graphics.getDeltaTime();

        if(this.g < 1 && this.r >= 1 && this.b <= 0) {
            this.g += velocity;
            if(this.g > 1.0F) {
                this.g = 1.0F;
            }
        } else if(this.g >= 1 && this.r > 0 && this.b <= 0) {
            this.r -= velocity;
            if(this.r < 0.0F) {
                this.r = 0.0F;
            }
        } else if(this.g >= 1 && this.r <= 0 && this.b < 1) {
            this.b += velocity;
            if(this.b > 1.0F) {
                this.b = 1.0F;
            }
        } else if(this.g > 0 && this.r <= 0 && this.b >= 1) {
            this.g -= velocity;
            if(this.g < 0.0F) {
                this.g = 0.0F;
            }
        } else if(this.g <= 0 && this.r < 1 && this.b >= 1) {
            this.r += velocity;
            if(this.r > 1.0F) {
                this.r = 1.0F;
            }
        } else/* if(this.g <= 0 && this.r >= 1 && this.b > 0) */{
            this.b -= velocity;
            if(this.b < 0.0F) {
                this.b = 0.0F;
            }
        }
    }
}
