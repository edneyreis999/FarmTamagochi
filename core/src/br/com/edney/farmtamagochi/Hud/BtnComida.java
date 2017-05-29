package br.com.edney.farmtamagochi.Hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import br.com.edney.farmtamagochi.Itens.Comida;

import static br.com.edney.farmtamagochi.Util.Constantes.petsCorpoRaio;

/**
 * Created by Desktop on 27/05/2017.
 */

public class BtnComida extends Widget {
    private Texture texture;
    private Comida comida;

    BtnComida(){
        texture = new Texture("ovos/ovo_azulclaro_1.png");
        this.setWidth(petsCorpoRaio * 1.3f);
        this.setHeight(petsCorpoRaio * 1.3f);

        comida = new Comida();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public Comida getComida() {
        return comida;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
    }
}
