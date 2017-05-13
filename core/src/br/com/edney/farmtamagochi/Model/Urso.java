package br.com.edney.farmtamagochi.Model;


import static br.com.edney.farmtamagochi.Util.Constantes.*;

public class Urso extends Pet {
    private Tamanho tamanho;
    private String pathSprite;
    private int qtdSprites;

    public Urso(float posX, float posY, Tamanho tamanho) {
        super(posX, posY);
        this.tamanho = tamanho;

        switch (tamanho){
            case OVO:
                qtdSprites = QTD_SPRITES_OVO;
                pathSprite = "ovos/digieggs_";
                break;
            case PEQUENO:
                qtdSprites = QTD_SPRITES_URSO_PEQUENO;
                pathSprite = "urso/urso_pequeno/urso";
                break;
            case MEDIO:
                qtdSprites = QTD_SPRITES_URSO_MEDIO;
                pathSprite = "urso/urso_medio/urso";
                break;
            case GRANDE:
                qtdSprites = QTD_SPRITES_URSO_GRANDE;
                pathSprite = "urso/urso_grande/urso";
                break;
        }

        init();
    }


    @Override
    protected String setPathSprites() {

        return pathSprite;
    }

    @Override
    protected int setQtdSprites() {

        return qtdSprites;
    }

    public enum Tamanho{
        OVO,
        PEQUENO,
        MEDIO,
        GRANDE;
    }
}
