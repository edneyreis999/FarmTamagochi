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

public class MainClass extends Game {

	@Override
	public void create() {
		setScreen(new TownScreen(this));
	}
}
