package br.com.edney.farmtamagochi;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.com.edney.farmtamagochi.Screen.TownScreen;

public class TamagochiFarm extends Game {
	public static final int V_WIDTH = 800;
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
