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

import java.util.ArrayList;

import br.com.edney.farmtamagochi.Hud.Hud;
import br.com.edney.farmtamagochi.Model.Especie;
import br.com.edney.farmtamagochi.Model.GameVariaveis;
import br.com.edney.farmtamagochi.Model.Pet;
import br.com.edney.farmtamagochi.Model.Tamanho;
import br.com.edney.farmtamagochi.Model.Town;
import br.com.edney.farmtamagochi.Model.Urso;
import br.com.edney.farmtamagochi.TamagochiFarm;
import br.com.edney.farmtamagochi.Util.MeuGestureListener;
import br.com.edney.farmtamagochi.Util.Save;

import static br.com.edney.farmtamagochi.Util.Constantes.TIME_TO_AUTO_SAVE;
import static br.com.edney.farmtamagochi.Util.Constantes.V_HEIGHT;
import static br.com.edney.farmtamagochi.Util.Constantes.V_WIDTH;

/**
 * Created by Desktop on 02/05/2017.
 */

public class TownScreen implements Screen {
    private TamagochiFarm game;

    private OrthographicCamera cameraTown;
    public Viewport viewport;
    private Town town;
    private ArrayList<Pet> pets;
    private Hud hud;

    //Tiled map variables
    private TmxMapLoader maploader;
    public TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private Vector2 toque;

    //autosave
    float autoSaveCont = 0;

    public TownScreen(TamagochiFarm game) {
        this.game = game;
    }

    @Override
    public void show() {
        viewport = new FitViewport(V_WIDTH, V_HEIGHT);

        // Cria o pet caso não tenha ( pq pode voltar da screen de evolve )
        Save save = Save.getInstance();
        GameVariaveis gameVariaveis = save.loadGame();
        if (gameVariaveis.getQtdPets() <= 0){
            this.pets = new ArrayList<Pet>();
            int qtdPets = 0;
            this.pets.add(new Urso(viewport.getWorldWidth() / 4, viewport.getWorldHeight() / 2, ++qtdPets, Especie.URSO, Tamanho.OVO));
            this.pets.add(new Urso(viewport.getWorldWidth() / 4 + 50, viewport.getWorldHeight() / 2 + 50, ++qtdPets, Especie.URSO, Tamanho.OVO));
            gameVariaveis.setQtdPets(qtdPets);
            save.saveGame(this);
        }else{
            ArrayList<Pet> loadPets = save.loadPets();
            if (loadPets.size() <= 0){
                Gdx.app.error("Save", "Deu problema ao carregar pets");
            }else{
                this.pets = loadPets;
            }
        }

        hud = new Hud(game.batch, this);

        //Load our map and setup our map rendere
        try {
            maploader = new TmxMapLoader();
            map = maploader.load("teste.tmx");
            renderer = new OrthogonalTiledMapRenderer(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Constructs a new OrthographicCamera, using the given viewport width and height
        // Height is multiplied by aspect ratio.
        cameraTown = (OrthographicCamera) viewport.getCamera();

        //initially set our gamcam to be centered correctly at the start of of map
        cameraTown.position.set(viewport.getWorldWidth() / 4, viewport.getWorldHeight() / 2, 0);
        cameraTown.zoom -= 0.5f;

        toque = new Vector2();
        Gdx.input.setInputProcessor(new GestureDetector(new MeuGestureListener(this)));
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
        for (int i = 0; i < pets.size(); i++) {
            pets.get(i).draw(game.batch);
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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

    public void update(float deltaTime) {
        cameraTown.update();
        renderer.setView(cameraTown);
        for (int i = 0; i < pets.size(); i++) {
            pets.get(i).update(deltaTime);
        }

        // autoSave
        autoSaveCont += deltaTime;
        if(autoSaveCont >= TIME_TO_AUTO_SAVE){
            Save save = Save.getInstance();
            save.saveGame(this);
            GameVariaveis gameVariaveis = GameVariaveis.getInstance();
            Gdx.app.log("AutoSave", "Salvei o game e os "+gameVariaveis.getQtdPets()+" pets");
            autoSaveCont = 0;
        }
    }

    public OrthographicCamera getCameraTown() { return cameraTown; }

    public void evoluirPet(Pet pet) {
        game.setScreen(new EvolveScreen(game, pet, this));
    }


    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        hud.dispose();
        for (int i = 0; i < pets.size(); i++) {
            pets.get(i).dispose();
        }
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }
}
