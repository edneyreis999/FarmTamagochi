package br.com.edney.farmtamagochi.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import static br.com.edney.farmtamagochi.Util.Constantes.*;

/**
 * Created by Desktop on 02/05/2017.
 */

public abstract class Pet {

    private String pathSprite;

    protected Texture[] sprites;
    private int qtdSpritesIdle;
    protected Animation<Texture> animation;
    protected float posX, posY;

    // A variable for tracking elapsed time for the animation
    protected float stateTime;

    public Pet(float posX, float posY){
        this.posX = posX;
        this.posY = posY;

        configuraSprites();
        initAnim();
    }

    private void configuraSprites(){
        this.pathSprite = setPathSprites();
        this.qtdSpritesIdle = setQtdSprites();
    }
    protected abstract String setPathSprites();
    protected abstract int setQtdSprites();

    private void initAnim() {
        sprites = new Texture[qtdSpritesIdle];
        for (int i = 1; i <= qtdSpritesIdle; i++) {
            Texture t = new Texture(pathSprite + i +".png");
            sprites[i - 1] = t;
        }

        animation = new Animation<Texture>(1f, sprites);
        stateTime = 0;
    }

    public void draw(SpriteBatch batch){
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

        // Get current frame of animation for the current stateTime
        //batch.draw(animation.getKeyFrame(stateTime, true), posX, posY, 10, 10); // Draw current frame at (50, 50)
    }

}
