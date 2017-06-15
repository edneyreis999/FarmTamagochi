package br.com.edney.farmtamagochi.Hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import br.com.edney.farmtamagochi.Screen.ShopScreen;
import br.com.edney.farmtamagochi.Screen.TownScreen;
import br.com.edney.farmtamagochi.TamagochiFarm;

/**
 * Created by Desktop on 15/06/2017.
 */

public class BtnShop extends Widget {
    private Texture texture;

    public BtnShop(final TamagochiFarm game){
        texture = new Texture("ovos/ovo_azulclaro_1.png");

        this.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ShopScreen(game));
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
