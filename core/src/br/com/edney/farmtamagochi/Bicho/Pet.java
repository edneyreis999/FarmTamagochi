package br.com.edney.farmtamagochi.Bicho;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Desktop on 02/05/2017.
 */

public class Pet {

    private Texture[] sprites;
    private Animation<Texture> animation;
    private float posX, posY;

    // A variable for tracking elapsed time for the animation
    float stateTime;

    public Pet(float posX, float posY){
        this.posX = posX;
        this.posY = posY;

        sprites = new Texture[3];
        for (int i = 1; i <= 3; i++) {
            Texture t = new Texture("ovos/digieggs_0"+ i +".png");
            sprites[i - 1] = t;
        }

        animation = new Animation<Texture>(1f, sprites);
        stateTime = 0;
    }

    public void draw(SpriteBatch batch){
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

        // Get current frame of animation for the current stateTime
        batch.draw(animation.getKeyFrame(stateTime, true), posX, posY, 10, 10); // Draw current frame at (50, 50)
    }

}
