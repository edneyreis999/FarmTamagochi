package br.com.edney.farmtamagochi.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.com.edney.farmtamagochi.Model.Pet;
import br.com.edney.farmtamagochi.TamagochiFarm;

import static br.com.edney.farmtamagochi.Util.Constantes.V_HEIGHT;
import static br.com.edney.farmtamagochi.Util.Constantes.V_WIDTH;

/**
 * Created by Desktop on 14/05/2017.
 */

public class EvolveScreen implements Screen {
    private TamagochiFarm game;
    private TownScreen town;
    private Pet petAntigo;
    private Pet petNovo;
    private Viewport viewport;
    private OrthographicCamera cameraEvolve;

    private float zoomSpreed;
    private float deltaTimeZoom;


    public EvolveScreen (TamagochiFarm game, Pet petAntigo,Pet petNovo, TownScreen town){
        this.game = game;
        this.petAntigo = petAntigo;
        this.petNovo = petNovo;
        this.town = town;
    }
    @Override
    public void show() {
        // Constructs a new OrthographicCamera, using the given viewport width and height
        // Height is multiplied by aspect ratio.
        viewport = new FitViewport(V_WIDTH, V_HEIGHT);
        cameraEvolve = (OrthographicCamera) viewport.getCamera();

        //initially set our gamcam to be centered correctly at the start of of map
        cameraEvolve.position.set(viewport.getWorldWidth() /4, viewport.getWorldHeight()/ 2, 0);
        cameraEvolve.zoom -= 0.5f;

        zoomSpreed = 0.5f;
    }

    @Override
    public void render(float delta) {
        update(delta);
        //Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        game.batch.setProjectionMatrix(cameraEvolve.combined);

        game.batch.begin();
        draw();
        game.batch.end();
    }

    private void update(float deltaTime) {
        deltaTimeZoom += deltaTime;

        if(deltaTimeZoom >= zoomSpreed){
            cameraEvolve.zoom -= 0.01f;

            deltaTimeZoom = 0;
        }
        cameraEvolve.update();

        petNovo.update(deltaTime);
        petAntigo.update(deltaTime);


        // sai da animação
        if(cameraEvolve.zoom <= 0.20f){
            town.pet = petNovo;
            game.setScreen(town);
        }
    }

    private void draw() {
        if(cameraEvolve.zoom <= 0.25f){
            petNovo.draw(game.batch);
        }else{
            petAntigo.draw(game.batch);
        }
    }

    @Override
    public void resize(int width, int height) {
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

    }
}
