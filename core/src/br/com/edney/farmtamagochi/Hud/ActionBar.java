package br.com.edney.farmtamagochi.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Desktop on 19/05/2017.
 */

public class ActionBar extends HorizontalGroup {
    Viewport viewport;
    //private Image fundo;
    private TextButton btnActionChooser;
    private TextButton btnShop;
    private TextButton btnAction1;
    private TextButton btnAction2;
    private TextButton btnAction3;
    private Skin skin = new Skin(Gdx.files.internal("skin/sgx-ui.json"));

    public ActionBar(Viewport viewport){
        this.viewport = viewport;
        Texture bground = new Texture(Gdx.files.internal("raw/background.png"));
        //fundo = new Image(bground);
        btnActionChooser = new TextButton("Choose", skin);
        btnAction1 = new TextButton("1", skin);
        btnAction2 = new TextButton("2", skin);
        btnAction3 = new TextButton("3", skin);
        btnShop = new TextButton("Shop", skin);

        btnActionChooser.setPosition(0, 0);
        btnShop.setPosition(100, 100);

        this.addActor(btnActionChooser);
        this.addActor(btnAction1);
        this.addActor(btnAction2);
        this.addActor(btnAction3);
        this.addActor(btnShop);

        this.setFillParent(true);
        this.bottom();
    }

    private float calculaWidth() {
        return viewport.getScreenWidth() / 5;
    }


}
