package br.com.edney.farmtamagochi.Model;

import br.com.edney.farmtamagochi.Enum.Tamanho;
import br.com.edney.farmtamagochi.Enum.TipoComida;

import static br.com.edney.farmtamagochi.Util.Constantes.QTD_SPRITES_CAVALO_GRANDE;
import static br.com.edney.farmtamagochi.Util.Constantes.QTD_SPRITES_CAVALO_MEDIO;
import static br.com.edney.farmtamagochi.Util.Constantes.QTD_SPRITES_CAVALO_PEQUENO;
import static br.com.edney.farmtamagochi.Util.Constantes.QTD_SPRITES_OVO;

/**
 * Created by Desktop on 28/05/2017.
 */

public class Cavalo extends Pet {

    public Cavalo(float posX, float posY, int saveId, Tamanho tamanho) {
        super(posX, posY, saveId, br.com.edney.farmtamagochi.Enum.Especie.CAVALO);
        this.tamanho = tamanho;
        init();
    }

    @Override
    protected String getPathSprites() {
        String pathSprite = "";
        switch (getTamanho()) {
            case OVO:
                pathSprite = "ovos/ovo_azul_";
                break;
            case PEQUENO:
                pathSprite = "cavalo/cavalo_pequeno/cavalo_pequeno_";
                break;
            case MEDIO:
                pathSprite = "cavalo/cavalo_medio/cavalo_medio_";
                break;
            case GRANDE:
                pathSprite = "cavalo/cavalo_grande/cavalo_grande_";
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
                qtdSprites = QTD_SPRITES_CAVALO_PEQUENO;
                break;
            case MEDIO:
                qtdSprites = QTD_SPRITES_CAVALO_MEDIO;
                break;
            case GRANDE:
                qtdSprites = QTD_SPRITES_CAVALO_GRANDE;
                break;
        }
        return qtdSprites;
    }

    @Override
    protected void getStatusPet() {
        // por hora vamos deixar tudo padrão
        Status status = new Status();
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
                status.setFrequenciaFome(4);
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
