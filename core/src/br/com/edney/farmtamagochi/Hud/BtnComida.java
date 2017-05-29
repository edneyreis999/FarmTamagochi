package br.com.edney.farmtamagochi.Hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import static br.com.edney.farmtamagochi.Util.Constantes.petsCorpoRaio;

/**
 * Created by Desktop on 27/05/2017.
 */

public class BtnComida extends Widget {
    private Texture texture;

    BtnComida(){
        texture = new Texture("ovos/ovo_azulclaro_1.png");
        this.setWidth(petsCorpoRaio * 1.3f);
        this.setHeight(petsCorpoRaio * 1.3f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
