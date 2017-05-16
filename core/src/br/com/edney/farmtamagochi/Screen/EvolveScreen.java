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
    private Pet pet;
    private Viewport viewport;
    private OrthographicCamera cameraEvolve;

    private float zoomSpreed;
    private float deltaTimeZoom;

    private boolean evoluiu =false;


    public EvolveScreen (TamagochiFarm game, Pet pet, TownScreen town){
        this.game = game;
        this.pet = pet;
        this.town = town;
    }
    @Override
    public void show() {
        // Constructs a new OrthographicCamera, using the given viewport width and height
        // Height is multiplied by aspect ratio.
        viewport = new FitViewport(V_WIDTH, V_HEIGHT);
        cameraEvolve = (OrthographicCamera) viewport.getCamera();

        //initially set our gamcam to be centered correctly at the start of of map
        cameraEvolve.position.set(pet.getPosX(), pet.getPosY(), 0);
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

        pet.update(deltaTime);


        // sai da animação
        if(cameraEvolve.zoom <= 0.20f){
            game.setScreen(town);
        }
    }

    private void draw() {
        if(!evoluiu){
            if(cameraEvolve.zoom <= 0.25f){
                pet.evoluir();
                evoluiu = true;
            }
        }

        pet.draw(game.batch);
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
