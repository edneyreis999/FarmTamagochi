package br.com.edney.farmtamagochi;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.com.edney.farmtamagochi.Screen.TownScreen;

public class TamagochiFarm extends Game {
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
