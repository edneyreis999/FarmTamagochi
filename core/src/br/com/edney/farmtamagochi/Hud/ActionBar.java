package br.com.edney.farmtamagochi.Hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

import br.com.edney.farmtamagochi.Util.Constantes;

import static br.com.edney.farmtamagochi.Util.Constantes.petsCorpoRaio;

/**
 * Created by Desktop on 28/05/2017.
 */

public class ActionBar extends WidgetGroup {
    private Texture texture;
    private int slots = 9;

    public ActionBar(){
        texture = new Texture("hud/action_bar.png");
        this.setWidth(Constantes.V_WIDTH);
        //this.setHeight(Constantes.V_HEIGHT / 8);
        this.setHeight(70); //TODO arrumar isso aqui
        this.setX(0);
        this.setY(0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public BtnComida setActiorToBar(BtnComida btnComida){
        int btnWidth = (int) (this.getWidth() / this.getSlots());
        int btnHeight = (int) this.getHeight();
        if(btnComida != null){
            btnComida.setBounds(0, 0, btnWidth, btnHeight);
        }
        return btnComida;
    }

    public int getSlots() {
        return slots;
    }
}
