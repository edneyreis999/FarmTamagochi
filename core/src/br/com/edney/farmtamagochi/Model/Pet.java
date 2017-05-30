package br.com.edney.farmtamagochi.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

import br.com.edney.farmtamagochi.Enum.Tamanho;
import br.com.edney.farmtamagochi.Util.Clickable;

import static br.com.edney.farmtamagochi.Util.Constantes.petsCorpoRaio;

/**
 * Created by Desktop on 02/05/2017.
 */

public abstract class Pet extends Actor implements Clickable, Disposable{
    private int saveId = 0;

    protected Tamanho tamanho;
    protected Status status;
    protected Texture[] sprites;

    private br.com.edney.farmtamagochi.Enum.Especie especie;
    private String pathSprite;
    private int qtdSprites;

    private float deltaTimeSprites;




    // A variable for tracking elapsed time for the animation
    protected float stateTime;

    public Pet(float posX, float posY, int saveId, br.com.edney.farmtamagochi.Enum.Especie especie){
        this.saveId = saveId;
        this.especie = especie;

        this.setX(posX);
        this.setY(posY);
        this.setWidth(petsCorpoRaio* 2);
        this.setHeight(petsCorpoRaio * 2);
        this.setDebug(true);

        status = new Status();
        Gdx.app.log("Drag", "Nasci na coordenada: "+posX+" / "+posY);
    }

    public void init(){
        configuraSprites();
        initAnim();
        getStatusPet();
    }

    private void configuraSprites(){
        this.pathSprite = getPathSprites();
        this.qtdSprites = getQtdSprites();
    }
    protected abstract String getPathSprites();
    protected abstract int getQtdSprites();
    protected abstract void getStatusPet();

    private void initAnim() {
        sprites = new Texture[qtdSprites];
        for (int i = 1; i <= qtdSprites; i++) {
            Texture t = new Texture(pathSprite + i +".png");
            sprites[i - 1] = t;
        }

        stateTime = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

        // Get current frame of animation for the current stateTime
        batch.draw(sprites[(int) deltaTimeSprites % qtdSprites], getX(), getY(), getWidth(), getHeight());

    }

    public void update(float deltaTime){
        deltaTimeSprites += 6 * deltaTime;

        // atualiza as variaveis de cuidado do pet
        status.update(deltaTime);
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
        int xInit = (int) getX() - margem;
        int xFim  = (int)(getX() + getWidth()) + margem;
        int yInit = (int) getY() - margem;
        int yFim  = (int)(getY() + getHeight()) + margem;



        isTouched = false;
        if(xWorldCoord >= xInit && xWorldCoord <= xFim){
            if(yWorldCoord >= yInit && yWorldCoord <= yFim){
                isTouched = true;
            }
        }

        Gdx.app.log("Pet", "Tamanho: "+ getTamanho());


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
        status.setReadyToEvolve(false);
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

    public br.com.edney.farmtamagochi.Enum.Especie getEspecie() {
        return especie;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getSaveId() {
        return saveId;
    }

}
