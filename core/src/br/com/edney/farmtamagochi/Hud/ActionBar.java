package br.com.edney.farmtamagochi.Hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.edney.farmtamagochi.Util.Constantes;

/**
 * Created by Desktop on 28/05/2017.
 */

public class ActionBar extends WidgetGroup {
    private Texture texture;
    private int slots = 9;
    private Widget[] slotItens = new Widget[9];
    private Label[] slotLabels = new Label[9];

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

    public Widget setActiorToBar(Widget widget, int slot){
        int btnWidth = (int) (this.getWidth() / this.getSlots());
        int btnHeight = (int) this.getHeight();
        int posX = slot * btnWidth + 10;
        if(widget != null){
            widget.setBounds(posX, 0, btnWidth, btnHeight);
        }
        if(widget.getClass().isAssignableFrom(BtnComida.class)){
            BtnComida btnComida = (BtnComida) widget;
            int qtdAlimento = btnComida.getComida().getQtdAlimento();
            slotLabels[slot] = new Label(String.valueOf(qtdAlimento), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
            setLabelToBar(slotLabels[slot], slot);
        }
        slotItens[slot] = widget;
        return widget;
    }

    public Widget setLabelToBar(Label qtdComidaLabel, int slot){
        int btnWidth = (int) (this.getWidth() / this.getSlots());
        int btnHeight = (int) this.getHeight();
        int posX = slot * btnWidth + 1;
        if(qtdComidaLabel != null){
            qtdComidaLabel.setBounds(posX, btnHeight + 5, btnWidth, btnHeight);
        }
        this.addActor(qtdComidaLabel);
        return qtdComidaLabel;
    }

    public void update(){
        for (int i = 0; i < slotLabels.length; i++) {
            
        }
    }

    public int getSlots() {
        return slots;
    }
}
