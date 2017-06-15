package br.com.edney.farmtamagochi.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.com.edney.farmtamagochi.Enum.TipoComida;
import br.com.edney.farmtamagochi.Model.Pet;
import br.com.edney.farmtamagochi.Screen.TownScreen;

import static br.com.edney.farmtamagochi.Util.Constantes.V_HEIGHT;
import static br.com.edney.farmtamagochi.Util.Constantes.V_WIDTH;

/**
 * Created by Notebook on 05/05/2017.
 */

public class Hud implements Disposable {
    private final TownScreen town;
    //Scene2D.ui Stage and its own Viewport for HUD
    private Stage stage;
    private Viewport viewport;
    private Skin skin = new Skin(Gdx.files.internal("skin/sgx-ui.json"));


    private Label fomePet1Label;
    private Label fomePet2Label;

    private Label evolvePet1Label;
    private Label evolvePet2Label;

    private Pet pet1;
    private Pet pet2;

    public Hud(SpriteBatch batch, TownScreen town){
        //setup the HUD viewport using a new camera seperate from our gamecam
        //define our stage using that viewport and our games spritebatch
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, batch);
        this.town = town;

        pet1 = town.getPets().get(0);
        pet2 = town.getPets().get(1);

        addLabelsToHud();
        addActionBar();
    }

    private void addLabelsToHud() {
        //define a table used to organize our hud's labels
        Table table = new Table();
        //Bottom-Align table
        table.top();
        //make the table fill the entire stage
        table.setFillParent(true);
        fomePet1Label = new Label(String.valueOf(0), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        fomePet2Label = new Label(String.valueOf(0), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(fomePet1Label).expandX();
        table.add(fomePet2Label).expandX();

        table.row();

        evolvePet1Label = new Label(String.valueOf(0), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        evolvePet2Label = new Label(String.valueOf(0), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(evolvePet1Label).expandX();
        table.add(evolvePet2Label).expandX();

        table.setDebug(true);
        stage.addActor(table);
    }

    public void update(float dt){
        fomePet1Label.setText(String.valueOf(pet1.getStatus().getFomeAtual()));
        fomePet2Label.setText(String.valueOf(pet2.getStatus().getFomeAtual()));

        evolvePet1Label.setText(String.valueOf(pet1.getStatus().getTimeToEvolve()));
        evolvePet2Label.setText(String.valueOf(pet2.getStatus().getTimeToEvolve()));
    }

    private void addActionBar() {
        ActionBar actionBar = new ActionBar();
        BtnComida btnComidaCarne = (BtnComida) actionBar.setActiorToBar(new BtnComida(TipoComida.CARNE), 0);
        btnComidaCarne.addDragAndDrop(town);

        BtnComida btnComidaVegetal = (BtnComida) actionBar.setActiorToBar(new BtnComida(TipoComida.VEGETAL), 1);
        btnComidaVegetal.addDragAndDrop(town);

        BtnShop btnShop = (BtnShop) actionBar.setActiorToBar(new BtnShop(town.getGame()), 8);

        stage.addActor(actionBar);
        stage.addActor(btnComidaCarne);
        stage.addActor(btnComidaVegetal);
        stage.addActor(btnShop);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public Stage getStage() {
        return stage;
    }
}
