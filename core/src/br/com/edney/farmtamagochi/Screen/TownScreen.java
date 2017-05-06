package br.com.edney.farmtamagochi.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.com.edney.farmtamagochi.Bicho.Pet;
import br.com.edney.farmtamagochi.Scenes.Hud;
import br.com.edney.farmtamagochi.TamagochiFarm;
import br.com.edney.farmtamagochi.Town.Town;

/**
 * Created by Desktop on 02/05/2017.
 */

public class TownScreen implements Screen{
    private TamagochiFarm game;
    private OrthographicCamera cameraTown;
    private Viewport viewport;
    private Town town;
    private Pet pet;
    private Hud hud;

    public TownScreen(TamagochiFarm game){
        this.game = game;
        // Constructs a new OrthographicCamera, using the given viewport width and height
        // Height is multiplied by aspect ratio.
        cameraTown = new OrthographicCamera();
        viewport = new FitViewport(TamagochiFarm.V_WIDTH, TamagochiFarm.V_HEIGHT, cameraTown);
        hud = new Hud(game.batch);

        town = new Town();
        pet = new Pet(viewport.getWorldWidth() /2, viewport.getWorldHeight() /2);

        //initially set our gamcam to be centered correctly at the start of of map
        cameraTown.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        cameraTown.zoom += 1.0f;
    }

    @Override
    public void show() {
        /*
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();



        cameraTown.position.set(cameraTown.viewportWidth / 2f, cameraTown.viewportHeight / 0.9f, 0);
        cameraTown.zoom += 1.0f;
        cameraTown.update();

        batch = new SpriteBatch();

        town = new Town(batch);
        pet = new Pet(batch, cameraTown.viewportWidth / 2.6f, cameraTown.viewportHeight / 1.2f);
        */

    }

    @Override
    public void render(float delta) {
        handleInput();
        cameraTown.update();
        //Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(cameraTown.combined);

        game.batch.begin();

        town.draw(game.batch);
        pet.draw(game.batch);
        game.batch.end();

        //Set our batch to now draw what the Hud camera sees.
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        /*
        handleInput();
        cameraTown.update();

        batch.begin();
        town.draw();
        pet.draw();
        batch.end();
        */
    }

    @Override
    public void resize(int width, int height) {
        /*
        cameraTown.viewportWidth = 30f;
        cameraTown.viewportHeight = 30f * height/width;
        cameraTown.update();
        */
        viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        town.dispose();
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            cameraTown.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            cameraTown.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cameraTown.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cameraTown.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cameraTown.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cameraTown.translate(0, 3, 0);
        }

        cameraTown.zoom = MathUtils.clamp(cameraTown.zoom, 0.1f, 100/ cameraTown.viewportWidth);

        float effectiveViewportWidth = cameraTown.viewportWidth * cameraTown.zoom;
        float effectiveViewportHeight = cameraTown.viewportHeight * cameraTown.zoom;

        cameraTown.position.x = MathUtils.clamp(cameraTown.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        cameraTown.position.y = MathUtils.clamp(cameraTown.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
    }
}
