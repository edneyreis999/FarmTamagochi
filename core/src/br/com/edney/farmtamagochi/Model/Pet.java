package br.com.edney.farmtamagochi.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

import br.com.edney.farmtamagochi.Util.Clickable;

import static br.com.edney.farmtamagochi.Util.Constantes.*;

/**
 * Created by Desktop on 02/05/2017.
 */

public abstract class Pet extends Actor implements Clickable, Disposable{
    private int saveId = 0;

    protected Tamanho tamanho;
    private float posX;
    private float posY;
    private Especie especie;

    public Circle corpo;

    protected Texture[] sprites;
    private String pathSprite;
    private int qtdSprites;
    private float deltaTimeSprites;




    // A variable for tracking elapsed time for the animation
    protected float stateTime;

    public Pet(float posX, float posY, int saveId, Especie especie){
        corpo = new Circle(posX, posY, petsCorpoRaio);
        this.setPosX(posX);
        this.setPosY(posY);
        this.saveId = saveId;
        this.especie = especie;

        this.setX(posX);
        this.setY(posY);
        this.setWidth(corpo.radius * 2);
        this.setHeight(corpo.radius * 2);
        this.setDebug(true);
        Gdx.app.log("Pet", "Nasci na coordenada: "+posX+" / "+posY);
    }

    public void init(){
        configuraSprites();
        initAnim();
    }

    private void configuraSprites(){
        this.pathSprite = getPathSprites();
        this.qtdSprites = getQtdSprites();
    }
    protected abstract String getPathSprites();
    protected abstract int getQtdSprites();

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
        int xInit = (int)(getPosX() - corpo.radius) - margem;
        int xFim  = (int)(getPosX() + corpo.radius) + margem;
        int yInit = (int)(getPosY() - corpo.radius) - margem;
        int yFim  = (int)(getPosY() + corpo.radius) + margem;

        isTouched = false;
        if(xWorldCoord >= xInit && xWorldCoord <= xFim){
            if(yWorldCoord >= yInit && yWorldCoord <= yFim){
                isTouched = true;
            }
        }

        Gdx.app.log("Evolucao", "Tamanho: "+ getTamanho());

        return isTouched;
    }

    public void evoluir(){
        Gdx.app.log("Pet", "Estou evoluindooooooo!");
        switch (getTamanho()){
            case OVO:
                tamanho = (Tamanho.PEQUENO);
                break;
            case PEQUENO:
                tamanho = (Tamanho.MEDIO);
                break;
            case MEDIO:
                tamanho = (Tamanho.GRANDE);
                break;
            case GRANDE:
                tamanho = (Tamanho.OVO);
                break;
        }
        init();
    }

    @Override
    public void dispose() {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].dispose();
        }
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public Especie getEspecie() {
        return especie;
    }

    public int getSaveId() {
        return saveId;
    }

}
