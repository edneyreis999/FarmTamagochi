package br.com.edney.farmtamagochi.Itens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import br.com.edney.farmtamagochi.Model.Pet;

/**
 * Created by Desktop on 27/05/2017.
 */

public class Comida extends Widget{
    private Texture imgPayload;
    int qtdAlimento;

    public Comida(){
        imgPayload = new Texture("ovos/digieggs_11.png");
        qtdAlimento = 100;
    }

    public void alimentar(Pet pet){
        int fomeAtualPet = pet.getCuidadoPet().getFomeAtual();

        int fomeMaxPet = pet.getCuidadoPet().getFomeMax();
        int fomeMinPet = pet.getCuidadoPet().getFomeMin();

        pet.getCuidadoPet().setFomeAtual(MathUtils.clamp(fomeAtualPet + qtdAlimento, fomeMinPet, fomeMaxPet));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public Texture getImgPayload() {
        return imgPayload;
    }

    public void setImgPayload(Texture imgPayload) {
        this.imgPayload = imgPayload;
    }

    public int getQtdAlimento() {
        return qtdAlimento;
    }

    public void setQtdAlimento(int qtdAlimento) {
        this.qtdAlimento = qtdAlimento;
    }

}
