package br.com.edney.farmtamagochi.Town;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Desktop on 02/05/2017.
 */

public class Town {
    static final int WORLD_WIDTH = 100;
    static final int WORLD_HEIGHT = 100;

    private SpriteBatch batch;
    private Sprite mapSprite;

    public Town(SpriteBatch batch){
        this.batch = batch;

        mapSprite = new Sprite(new Texture(Gdx.files.internal("cidade2.png")));
        mapSprite.setPosition(0, 0);
        mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);
    }

    public void draw(){
        mapSprite.draw(batch);
    }

    public void dispose() {
        mapSprite.getTexture().dispose();
    }
}
