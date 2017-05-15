package br.com.edney.farmtamagochi.Model;


import static br.com.edney.farmtamagochi.Util.Constantes.QTD_SPRITES_OVO;
import static br.com.edney.farmtamagochi.Util.Constantes.QTD_SPRITES_URSO_GRANDE;
import static br.com.edney.farmtamagochi.Util.Constantes.QTD_SPRITES_URSO_MEDIO;
import static br.com.edney.farmtamagochi.Util.Constantes.QTD_SPRITES_URSO_PEQUENO;

public class Urso extends Pet {
    private String pathSprite;
    private int qtdSprites;

    public Urso(float posX, float posY, Tamanho tamanho) {
        super(posX, posY);
        this.tamanho = tamanho;

        switch (tamanho) {
            case OVO:
                qtdSprites = QTD_SPRITES_OVO;
                pathSprite = "ovos/digieggs_";
                break;
            case PEQUENO:
                qtdSprites = QTD_SPRITES_URSO_PEQUENO;
                pathSprite = "urso/urso_pequeno/urso_";
                break;
            case MEDIO:
                qtdSprites = QTD_SPRITES_URSO_MEDIO;
                pathSprite = "urso/urso_medio/urso_medio_";
                break;
            case GRANDE:
                qtdSprites = QTD_SPRITES_URSO_GRANDE;
                pathSprite = "urso/urso_grande/urso_grande_";
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

    public Tamanho getTamanho() {
        return tamanho;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
