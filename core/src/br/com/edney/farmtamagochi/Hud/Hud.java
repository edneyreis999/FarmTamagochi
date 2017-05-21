package br.com.edney.farmtamagochi.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.com.edney.farmtamagochi.Model.Pet;
import br.com.edney.farmtamagochi.Screen.TownScreen;
import br.com.edney.farmtamagochi.Util.MeuGestureListener;

import static br.com.edney.farmtamagochi.Util.Constantes.V_HEIGHT;
import static br.com.edney.farmtamagochi.Util.Constantes.V_WIDTH;

/**
 * Created by Notebook on 05/05/2017.
 */

public class Hud implements Disposable {
    //Scene2D.ui Stage and its own Viewport for HUD
    public Stage stage;
    private Viewport viewport;
    private Skin skin = new Skin(Gdx.files.internal("skin/sgx-ui.json"));
    private TownScreen town;

    //Scene2D widgets
    private TextButton btnActionChooser;
    private TextButton btnShop;
    private TextButton btnAction1;
    private TextButton btnAction2;
    private TextButton btnAction3;

    public Hud(SpriteBatch sb, TownScreen town){
        //setup the HUD viewport using a new camera seperate from our gamecam
        //define our stage using that viewport and our games spritebatch
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        this.town = town;

        //define a table used to organize our hud's labels
        Table table = new Table();
        //Bottom-Align table
        table.bottom();
        //make the table fill the entire stage
        table.setFillParent(true);

        criarBotaoAlimentacao();

        table.setDebug(true); // turn on all debug lines (table, cell, and widget)
        //add our table to the stage
        //stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

    }

    private void criarBotaoAlimentacao() {
        //final Skin skin = new Skin();
        skin.add("default", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        skin.add("badlogic", new Texture("ovos/digieggs_1.png"));

        Image sourceImage = new Image(skin, "badlogic");
        sourceImage.setBounds(50, 125, 100, 100);
        stage.addActor(sourceImage);

        Image validTargetImage = new Image(skin, "badlogic");
        validTargetImage.setBounds(200, 50, 100, 100);
        stage.addActor(validTargetImage);
/*
        Image invalidTargetImage = new Image(skin, "badlogic");
        invalidTargetImage.setBounds(200, 200, 100, 100);
        stage.addActor(invalidTargetImage);
        */

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
        });
//        dragAndDrop.addTarget(new DragAndDrop.Target(validTargetImage) {
//            public boolean drag (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
//                getActor().setColor(Color.BLUE);
//                return true;
//            }
//
//            public void reset (DragAndDrop.Source source, DragAndDrop.Payload payload) {
//                getActor().setColor(Color.WHITE);
//            }
//
//            public void drop (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
//                System.out.println("Accepted: " + payload.getObject() + " " + x + ", " + y);
//            }
//        });
//        dragAndDrop.addTarget(new DragAndDrop.Target(invalidTargetImage) {
//            public boolean drag (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
//                getActor().setColor(Color.BLACK);
//                return false;
//            }
//
//            public void reset (DragAndDrop.Source source, DragAndDrop.Payload payload) {
//                getActor().setColor(Color.WHITE);
//            }
//
//            public void drop (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
//                System.out.println("NOT Accepted: " + payload.getObject() + " " + x + ", " + y);
//            }
//        });

        for (Pet pet: town.getPets()) {
            //pet.setBounds(pet.getPosX(), pet.getPosY(), pet.corpo.radius * 2, pet.corpo.radius * 2);
            validTargetImage.getX();
            Gdx.app.log("Drag", " pet.getHeight(): "+ pet.getX()+" pet.getWidth(): "+pet.getY());
            Gdx.app.log("Drag", " validTargetImage.getX(): "+ validTargetImage.getX()+" validTargetImage.getY(): "+validTargetImage.getY());
            stage.addActor(pet);
            dragAndDrop.addTarget(new DragAndDrop.Target(pet) {
                public boolean drag (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    getActor().setColor(Color.YELLOW);
                    return true;
                }

                public void reset (DragAndDrop.Source source, DragAndDrop.Payload payload) {
                    getActor().setColor(Color.WHITE);
                }

                public void drop (DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    Gdx.app.log("Drag", " Pet aceitou ser draggado");
                }
            });
        }
    }

    private void montaActionBar(final Table table) {
        final HorizontalGroup hg = new HorizontalGroup();

        final VerticalGroup vg = new VerticalGroup();

        Button btnv1 = new TextButton("1", skin);
        Button btnv2 = new TextButton("2", skin);
        Button btnv3 = new TextButton("3", skin);

        vg.addActor(btnv1);
        vg.addActor(btnv2);
        vg.addActor(btnv3);

        vg.setVisible(true);


        btnActionChooser = new TextButton("Chooser", skin);
        btnActionChooser.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.log("Hud", "Chooser foi clikado.");
                vg.setVisible(!vg.isVisible());
            }
        });




        btnv1 = new TextButton("1", skin);
        btnv2 = new TextButton("2", skin);
        btnv3 = new TextButton("3", skin);
        btnShop = new TextButton("Shop", skin);

        hg.addActor(btnActionChooser);
        hg.addActor(btnv1);
        hg.addActor(btnv2);
        hg.addActor(btnv3);
        hg.addActor(btnShop);

        vg.reverse();
        table.add(vg).left();
        table.row();
        table.add(hg).bottom();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
