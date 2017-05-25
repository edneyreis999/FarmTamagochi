package br.com.edney.farmtamagochi.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.com.edney.farmtamagochi.Model.Pet;
import br.com.edney.farmtamagochi.Screen.TownScreen;

import static br.com.edney.farmtamagochi.Util.Constantes.V_HEIGHT;
import static br.com.edney.farmtamagochi.Util.Constantes.V_WIDTH;
import static br.com.edney.farmtamagochi.Util.Constantes.petsCorpoRaio;

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

        fomePet1Label = new Label(String.valueOf(pet1.getCuidadoPet().getFomeAtual()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        fomePet2Label = new Label(String.valueOf(pet2.getCuidadoPet().getFomeAtual()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(fomePet1Label).expandX();
        table.add(fomePet2Label).expandX();

        criarBotaoAlimentacao();

        table.setDebug(true);
        stage.addActor(table);
    }

    public void update(float dt){
        fomePet1Label.setText(String.valueOf(pet1.getCuidadoPet().getFomeAtual()));
        fomePet2Label.setText(String.valueOf(pet2.getCuidadoPet().getFomeAtual()));
    }

    private void criarBotaoAlimentacao() {
        skin.add("default", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        skin.add("badlogic", new Texture("ovos/digieggs_1.png"));

        Image sourceImage = new Image(skin, "badlogic");
        sourceImage.setBounds(viewport.getWorldWidth() / 2, viewport.getWorldWidth() / 24, petsCorpoRaio * 1.3f, petsCorpoRaio * 1.3f);
        stage.addActor(sourceImage);

        DragAndDrop dragAndDrop = new DragAndDrop();
        dragAndDrop.addSource(new DragAndDrop.Source(sourceImage) {
            public DragAndDrop.Payload dragStart (InputEvent event, float x, float y, int pointer) {
                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                payload.setObject("Some payload!");

                payload.setDragActor(new Label("Some payload!", skin));

                Label validLabel = new Label("Some payload!", skin);
                validLabel.setColor(0, 1, 0, 1);
                payload.setValidDragActor(validLabel);

                Label invalidLabel = new Label("Some payload!", skin);
                invalidLabel.setColor(1, 0, 0, 1);
                payload.setInvalidDragActor(invalidLabel);

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
                        Gdx.app.log("Drag", town.getPets().get(i).getSaveId() + "foi dragado");
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
