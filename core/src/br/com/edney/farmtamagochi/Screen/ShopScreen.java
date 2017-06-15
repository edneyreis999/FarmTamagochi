package br.com.edney.farmtamagochi.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.com.edney.farmtamagochi.Enum.TipoComida;
import br.com.edney.farmtamagochi.Hud.BtnComida;
import br.com.edney.farmtamagochi.Itens.Comida;
import br.com.edney.farmtamagochi.Model.GameManager;
import br.com.edney.farmtamagochi.TamagochiFarm;

import static br.com.edney.farmtamagochi.Util.Constantes.V_HEIGHT;
import static br.com.edney.farmtamagochi.Util.Constantes.V_WIDTH;

/**
 * Created by Desktop on 14/06/2017.
 */

public class ShopScreen implements Screen {
    private final TamagochiFarm game;
    private Viewport viewport;
    private Stage stage;

    private Label goldLabel;
    private Skin skin = new Skin(Gdx.files.internal("skin/sgx-ui.json"));
    private GameManager gameManager = GameManager.getInstance();


    public ShopScreen(TamagochiFarm game){
        this.game = game;
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        viewport = new FitViewport(V_WIDTH, V_HEIGHT);
        stage = new Stage(viewport, game.batch);

        goldLabel = new Label("10000", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //define a table used to organize our hud's labels
        Table table = new Table();
        //Top-Align table
        table.top();
        //make the table fill the entire stage
        table.setFillParent(true);
        table.setDebug(true);
        table.add(goldLabel).expandX();

        addBackButton(table);
        table.row();
        addFoodButtons(table);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    private void addFoodButtons(Table table) {
        table.add(criarButtonComida(TipoComida.CARNE)).expand().fill();
        table.add(criarButtonComida(TipoComida.VEGETAL)).expand().fill();
        table.row();
        table.add(criarButtonComida(TipoComida.CARNE)).expand().fill();
        table.add(criarButtonComida(TipoComida.VEGETAL)).expand().fill();


        stage.addActor(table);
    }

    private Actor criarButtonComida(TipoComida tipoComida) {
        final BtnComida btnComida = new BtnComida(tipoComida);
        btnComida.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Comida comida = btnComida.getComida();
                comida.comprar();
            }
        });

        return btnComida;
    }

    private void addBackButton(Table table) {
        // register the button "start game"
        TextButton startGameButton = new TextButton( "Voltar", skin );
        startGameButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen( new TownScreen(game) );
            }
        });

        table.add(startGameButton).right();
    }

    @Override
    public void render(float delta) {
        update(delta);
        //Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(viewport.getCamera().combined);

        stage.draw();
    }

    private void update(float delta) {
        int gold = gameManager.getGold();
        goldLabel.setText(String.valueOf(gold));
    }

    @Override
    public void resize(int width, int height) {

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
