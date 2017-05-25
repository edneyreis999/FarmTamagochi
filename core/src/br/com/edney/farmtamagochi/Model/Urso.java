package br.com.edney.farmtamagochi.Model;


import static br.com.edney.farmtamagochi.Util.Constantes.QTD_SPRITES_OVO;
import static br.com.edney.farmtamagochi.Util.Constantes.QTD_SPRITES_URSO_GRANDE;
import static br.com.edney.farmtamagochi.Util.Constantes.QTD_SPRITES_URSO_MEDIO;
import static br.com.edney.farmtamagochi.Util.Constantes.QTD_SPRITES_URSO_PEQUENO;

public class Urso extends Pet {

    public Urso(float posX, float posY, int saveId, Especie especie, Tamanho tamanho) {
        super(posX, posY, saveId, especie);
        this.tamanho = tamanho;
        init();
    }

    @Override
    protected String getPathSprites() {
        String pathSprite = "";
        switch (getTamanho()) {
            case OVO:
                pathSprite = "ovos/digieggs_";
                break;
            case PEQUENO:
                pathSprite = "urso/urso_pequeno/urso_";
                break;
            case MEDIO:
                pathSprite = "urso/urso_medio/urso_medio_";
                break;
            case GRANDE:
                pathSprite = "urso/urso_grande/urso_grande_";
                break;
        }
        return pathSprite;
    }

    @Override
    protected int getQtdSprites() {
        int qtdSprites = 0;
        switch (getTamanho()) {
            case OVO:
                qtdSprites = QTD_SPRITES_OVO;
                break;
            case PEQUENO:
                qtdSprites = QTD_SPRITES_URSO_PEQUENO;
                break;
            case MEDIO:
                qtdSprites = QTD_SPRITES_URSO_MEDIO;
                break;
            case GRANDE:
                qtdSprites = QTD_SPRITES_URSO_GRANDE;
                break;
        }
        return qtdSprites;
    }

    @Override
    protected void getCuidadePet() {
        // por hora vamos deixar tudo padrão
        switch (getTamanho()) {
            case OVO:

                break;
            case PEQUENO:

                break;
            case MEDIO:

                break;
            case GRANDE:

                break;
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
