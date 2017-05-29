package br.com.edney.farmtamagochi.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.com.edney.farmtamagochi.Itens.Comida;
import br.com.edney.farmtamagochi.Model.Pet;
import br.com.edney.farmtamagochi.Screen.TownScreen;

import static br.com.edney.farmtamagochi.Util.Constantes.V_HEIGHT;
import static br.com.edney.farmtamagochi.Util.Constantes.V_WIDTH;

/**
 * Created by Notebook on 05/05/2017.
 */

public class Hud implements Disposable {
    //Scene2D.ui Stage and its own Viewport for HUD
    private Stage stage;
    private Viewport viewport;
    private Skin skin = new Skin(Gdx.files.internal("skin/sgx-ui.json"));
    private TownScreen town;


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

        //define a table used to organize our hud's labels
        Table table = new Table();
        //Bottom-Align table
        table.top();
        //make the table fill the entire stage
        table.setFillParent(true);

        pet1 = town.getPets().get(0);
        pet2 = town.getPets().get(1);

        fomePet1Label = new Label(String.valueOf(0), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        fomePet2Label = new Label(String.valueOf(0), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(fomePet1Label).expandX();
        table.add(fomePet2Label).expandX();

        table.row();

        evolvePet1Label = new Label(String.valueOf(0), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        evolvePet2Label = new Label(String.valueOf(0), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(evolvePet1Label).expandX();
        table.add(evolvePet2Label).expandX();


        criarBotaoAlimentacao();

        table.setDebug(true);
        stage.addActor(table);
    }

    public void update(float dt){
        fomePet1Label.setText(String.valueOf(pet1.getStatus().getFomeAtual()));
        fomePet2Label.setText(String.valueOf(pet2.getStatus().getFomeAtual()));

        evolvePet1Label.setText(String.valueOf(pet1.getStatus().getTimeToEvolve()));
        evolvePet2Label.setText(String.valueOf(pet2.getStatus().getTimeToEvolve()));
    }

    private void criarBotaoAlimentacao() {

        final Comida comida = new Comida();
        final BtnComida btnComida = new BtnComida();
        btnComida.setX(viewport.getWorldWidth() / 2);
        btnComida.setY(viewport.getWorldWidth() / 24);

        stage.addActor(btnComida);

        final DragAndDrop dragAndDrop = new DragAndDrop();
        dragAndDrop.addSource(new DragAndDrop.Source(btnComida) {
            public DragAndDrop.Payload dragStart (InputEvent event, float x, float y, int pointer) {
                DragAndDrop.Payload payload = new DragAndDrop.Payload();

                // cria a img do payload
                payload.setDragActor(new BtnComida());

                return payload;
            }

            public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
                super.dragStop(event, x, y, pointer, payload, target);
                // Transforma as coordenadas de toque na tela em toque no world

                // Pega a coordenada exata do x e y que foi solto o rolê
                x = Gdx.input.getX();
                y = Gdx.input.getY();

                // converte para World coordenate
                Vector3 vec=new Vector3(x, y, 0);
                town.stage.getCamera().unproject(vec);

                Gdx.app.log("Drag", "World coord x: "+ vec.x);
                Gdx.app.log("Drag", "World coord y: "+ vec.y);
                Gdx.app.log("Drag", "Screen coord x: "+ x);
                Gdx.app.log("Drag", "Screen coord y: "+ y);

                // if sprite + 10 of px marge is touched
                for (int i = 0; i < town.getPets().size(); i++) {
                    if(town.getPets().get(i).isTouched(0, vec.x, vec.y, x, y)) {
                        Pet petDragado = town.getPets().get(i);
                        Gdx.app.log("Drag", petDragado.getSaveId() + "foi dragado");
                        comida.alimentar(petDragado);
                    }
                }
            }
        });
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public Stage getStage() {
        return stage;
    }
}
