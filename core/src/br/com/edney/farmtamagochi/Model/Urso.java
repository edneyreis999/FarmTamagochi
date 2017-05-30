package br.com.edney.farmtamagochi.Model;


import br.com.edney.farmtamagochi.Enum.Tamanho;
import br.com.edney.farmtamagochi.Enum.TipoComida;

import static br.com.edney.farmtamagochi.Enum.Especie.URSO;
import static br.com.edney.farmtamagochi.Util.Constantes.QTD_SPRITES_OVO;
import static br.com.edney.farmtamagochi.Util.Constantes.QTD_SPRITES_URSO_GRANDE;
import static br.com.edney.farmtamagochi.Util.Constantes.QTD_SPRITES_URSO_MEDIO;
import static br.com.edney.farmtamagochi.Util.Constantes.QTD_SPRITES_URSO_PEQUENO;

public class Urso extends Pet {

    public Urso(float posX, float posY, int saveId, Tamanho tamanho) {
        super(posX, posY, saveId, URSO);
        this.tamanho = tamanho;
        init();
    }

    @Override
    protected String getPathSprites() {
        String pathSprite = "";
        switch (getTamanho()) {
            case OVO:
                pathSprite = "ovos/ovo_vermelho_";
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
    protected void getStatusPet() {
        // por hora vamos deixar tudo padrão
        Status status = new Status();
        status.setTipoComida(TipoComida.CARNE);
        switch (getTamanho()) {
            case OVO:
                status.setFrequenciaFome(10);
                status.setTimeToEvolve(60);
                this.setStatus(status);
                break;
            case PEQUENO:
                status.setFrequenciaFome(8);
                status.setTimeToEvolve(120);
                this.setStatus(status);
                break;
            case MEDIO:
                status.setFrequenciaFome(5);
                status.setTimeToEvolve(240);
                this.setStatus(status);
                break;
            case GRANDE:
                status.setFrequenciaFome(2);
                status.setTimeToEvolve(480);
                this.setStatus(status);
                break;
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
