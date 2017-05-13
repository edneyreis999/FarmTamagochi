package br.com.edney.farmtamagochi.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

import br.com.edney.farmtamagochi.Util.Clickable;

import static br.com.edney.farmtamagochi.Util.Constantes.*;

/**
 * Created by Desktop on 02/05/2017.
 */

public abstract class Pet implements Clickable{

    public Circle corpo;

    protected Texture[] sprites;
    private String pathSprite;
    private int qtdSprites;
    private float deltaTimeSprites;


    public float posX, posY;

    // A variable for tracking elapsed time for the animation
    protected float stateTime;

    public Pet(float posX, float posY){
        corpo = new Circle(posX, posY, petsCorpoRaio);
        this.posX = posX;
        this.posY = posY;

        Gdx.app.log("Pet", "Nasci na coordenada: "+posX+" / "+posY);
    }

    public void init(){
        configuraSprites();
        initAnim();
    }

    private void configuraSprites(){
        this.pathSprite = setPathSprites();
        this.qtdSprites = setQtdSprites();
    }
    protected abstract String setPathSprites();
    protected abstract int setQtdSprites();

    private void initAnim() {
        sprites = new Texture[qtdSprites];
        for (int i = 1; i <= qtdSprites; i++) {
            Texture t = new Texture(pathSprite + i +".png");
            sprites[i - 1] = t;
        }

        stateTime = 0;
    }

    public void draw(SpriteBatch batch){
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

        // Get current frame of animation for the current stateTime
        batch.draw(sprites[(int) deltaTimeSprites % qtdSprites], (corpo.x - corpo.radius), (corpo.y - corpo.radius), corpo.radius *2, corpo.radius *2);
    }

    public int update(float deltaTime){
        deltaTimeSprites += 6 * deltaTime;
        return 0;
    }

    private boolean isTouched;

    /**
     * Type: Input Listener function
     * listen if this sprite button was pressed (touched)
     * @param margem : the extra touchable space out of sprite
     * @param xWorldCoord     : World position x
     * @param yWorldCoord     : World position y
     *
     * return true  : Sprite touched
     * return false : Sprite not touched
     */
    public boolean isTouched(int margem,float xWorldCoord, float yWorldCoord, float xScreenPosition, float yScreenPosition) {
        int xInit = (int)(posX - corpo.radius) - margem;
        int xFim  = (int)(posX + corpo.radius) + margem;
        int yInit = (int)(posY - corpo.radius) - margem;
        int yFim  = (int)(posY + corpo.radius) + margem;

        isTouched = false;
        if(xWorldCoord >= xInit && xWorldCoord <= xFim){
            if(yWorldCoord >= yInit && yWorldCoord <= yFim){
                isTouched = true;
            }
        }

        return isTouched;
    }

    public void evoluir(){
        Gdx.app.log("Pet", "Estou evoluindooooooo!");
    }

}
