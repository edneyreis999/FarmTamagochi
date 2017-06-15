package br.com.edney.farmtamagochi.Hud;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

/**
 * Created by Desktop on 15/06/2017.
 */

public class SlotBar {
    private Widget botao;
    private Label labelQtd;

    public SlotBar(Widget botao){
        this.botao = botao;
    }

    public Widget getBotao() {
        return botao;
    }

    public void setBotao(Widget botao) {
        this.botao = botao;
    }

    public Label getLabelQtd() {
        return labelQtd;
    }

    public void setLabelQtd(Label labelQtd) {
        this.labelQtd = labelQtd;
    }
}
