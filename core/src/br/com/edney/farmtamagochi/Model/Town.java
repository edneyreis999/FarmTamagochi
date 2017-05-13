package br.com.edney.farmtamagochi.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.com.edney.farmtamagochi.TamagochiFarm;

import static br.com.edney.farmtamagochi.Util.Constantes.*;

/**
 * Created by Desktop on 02/05/2017.
 */

public class Town {

    private Sprite mapSprite;

    public Town(){
        mapSprite = new Sprite(new Texture(Gdx.files.internal("cidade2.png")));
        mapSprite.setPosition(0, 0);
        mapSprite.setSize(V_WIDTH, V_HEIGHT);
    }

    public void draw(SpriteBatch batch){
        mapSprite.draw(batch);
    }

    public void dispose() {
        mapSprite.getTexture().dispose();
    }
}
