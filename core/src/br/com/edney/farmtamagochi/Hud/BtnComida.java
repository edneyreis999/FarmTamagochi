package br.com.edney.farmtamagochi.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import br.com.edney.farmtamagochi.Enum.TipoComida;
import br.com.edney.farmtamagochi.Itens.Comida;
import br.com.edney.farmtamagochi.Model.Pet;
import br.com.edney.farmtamagochi.Screen.TownScreen;

import static br.com.edney.farmtamagochi.Util.Constantes.petsCorpoRaio;

/**
 * Created by Desktop on 27/05/2017.
 */

public class BtnComida extends Widget {
    private Texture texture;
    private Comida comida;

    final private DragAndDrop dragAndDrop = new DragAndDrop();

    BtnComida(TipoComida tipoComida){
        this.setWidth(petsCorpoRaio * 1.3f);
        this.setHeight(petsCorpoRaio * 1.3f);

        comida = new Comida();
        comida.setTipoComida(tipoComida);

        String texturePath = getTexturePath(tipoComida);
        texture = new Texture(texturePath);
    }

    private String getTexturePath(TipoComida tipoComida) {
        String pathImgLabel = "";
        switch (tipoComida){
            case CARNE:
                pathImgLabel = "ovos/ovo_azulclaro_1.png";
                break;
            case VEGETAL:
                pathImgLabel = "ovos/ovo_laranja_1.png";
                break;
        }

        return pathImgLabel;
    }

    public void addDragAndDrop(final TownScreen town) {
        dragAndDrop.addSource(new DragAndDrop.Source(this) {
            public DragAndDrop.Payload dragStart (InputEvent event, float x, float y, int pointer) {
                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                // cria a img do payload
                Image btnLabel = new Image(texture);
                payload.setDragActor(btnLabel);

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
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public Comida getComida() {
        return comida;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
    }
}
