package br.com.edney.farmtamagochi.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.com.edney.farmtamagochi.Model.Pet;
import br.com.edney.farmtamagochi.Model.Urso;
import br.com.edney.farmtamagochi.Scenes.Hud;
import br.com.edney.farmtamagochi.TamagochiFarm;
import br.com.edney.farmtamagochi.Model.Town;
import br.com.edney.farmtamagochi.Util.MeuGestureListener;

import static br.com.edney.farmtamagochi.Util.Constantes.*;

/**
 * Created by Desktop on 02/05/2017.
 */

public class TownScreen implements Screen{
    private TamagochiFarm game;

    private OrthographicCamera cameraTown;
    public Viewport viewport;
    private Town town;
    public Pet pet;
    private Hud hud;

    //Tiled map variables
    private TmxMapLoader maploader;
    public TiledMap map;
    private OrthogonalTiledMapRenderer renderer;


    private Vector2 toque;

    public TownScreen(TamagochiFarm game){
        this.game = game;
        // Constructs a new OrthographicCamera, using the given viewport width and height
        // Height is multiplied by aspect ratio.
        viewport = new FitViewport(V_WIDTH, V_HEIGHT);
        hud = new Hud(game.batch);
        cameraTown = (OrthographicCamera) viewport.getCamera();


        //Load our map and setup our map rendere
        try{
            maploader = new TmxMapLoader();
            map = maploader.load("teste.tmx");
            renderer = new OrthogonalTiledMapRenderer(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        //initially set our gamcam to be centered correctly at the start of of map
        cameraTown.position.set(viewport.getWorldWidth() /4, viewport.getWorldHeight()/ 2, 0);
        cameraTown.zoom -= 0.5f;

        //town = new Town();
        pet = new Urso(viewport.getWorldWidth() /4, viewport.getWorldHeight() /2, Urso.Tamanho.OVO);

        toque = new Vector2();
        Gdx.input.setInputProcessor(new GestureDetector(new MeuGestureListener(this)));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        //Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render our game map
        renderer.render();

        game.batch.setProjectionMatrix(cameraTown.combined);

        game.batch.begin();
        draw();
        game.batch.end();

        //Set our batch to now draw what the Hud camera sees.
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    private void draw() {
        //town.draw(game.batch);
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

    public void update(float deltaTime){
        //handleInput(deltaTime);
        cameraTown.update();
        renderer.setView(cameraTown);
        pet.update(deltaTime);
    }

    public OrthographicCamera getCameraTown() {
        return cameraTown;
    }

    public void evoluirPet(Pet pet){
        Gdx.app.log("Pet", "Fazendo animação de evoluir pet");
        //TODO mudar o usuario para uma nova tela, onde a camera vai aproximando de vagar e depois ele evolui.
        // quando terminar a animação ele chama esse método
        pet.evoluir();
    }


    @Override
    public void dispose() {
        //town.dispose();
        map.dispose();
        //dispose of all our opened resources
        map.dispose();
        renderer.dispose();
        hud.dispose();
    }
}
