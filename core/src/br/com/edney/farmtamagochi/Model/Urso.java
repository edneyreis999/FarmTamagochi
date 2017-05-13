package br.com.edney.farmtamagochi.Model;


import static br.com.edney.farmtamagochi.Util.Constantes.*;

public class Urso extends br.com.edney.farmtamagochi.Model.Pet {
    private Tamanho tamanho;
    private String pathSprite;
    private int qtdSpritesIdle;

    public Urso(float posX, float posY, Tamanho tamanho) {
        super(posX, posY);
        this.tamanho = tamanho;

        switch (tamanho){
            case OVO:
                qtdSpritesIdle = QTD_SPRITES_OVO;
                pathSprite = "ovos/digieggs";
                break;
            case PEQUENO:
                qtdSpritesIdle = QTD_SPRITES_URSO_PEQUENO;
                pathSprite = "urso/urso_pequeno/urso";
                break;
            case MEDIO:
                qtdSpritesIdle = QTD_SPRITES_URSO_MEDIO;
                pathSprite = "urso/urso_medio/urso";
                break;
            case GRANDE:
                qtdSpritesIdle = QTD_SPRITES_URSO_GRANDE;
                pathSprite = "urso/urso_grande/urso";
                break;
        }
    }


    @Override
    protected String setPathSprites() {

        return pathSprite;
    }

    @Override
    protected int setQtdSprites() {
        return qtdSpritesIdle;
    }

    public enum Tamanho{
        OVO,
        PEQUENO,
        MEDIO,
        GRANDE;
    }
}
