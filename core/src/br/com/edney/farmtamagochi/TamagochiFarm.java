package br.com.edney.farmtamagochi;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import br.com.edney.farmtamagochi.Screen.TownScreen;
import br.com.edney.farmtamagochi.Town.Town;

public class TamagochiFarm extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 400;
	public SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new TownScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}
}
