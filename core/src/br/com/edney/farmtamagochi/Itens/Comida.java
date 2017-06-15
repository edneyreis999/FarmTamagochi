package br.com.edney.farmtamagochi.Itens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import br.com.edney.farmtamagochi.Enum.TipoComida;
import br.com.edney.farmtamagochi.Model.GameManager;
import br.com.edney.farmtamagochi.Model.Pet;

/**
 * Created by Desktop on 27/05/2017.
 */

public class Comida extends Widget{
    private Texture imgPayload;
    private int qtdAlimento = 100;
    private int preco = 100;
    private TipoComida tipoComida;
    private GameManager gameManager = GameManager.getInstance();

    public Comida(){
        imgPayload = new Texture("ovos/ovo_azulclaro_1.png");
    }

    public void alimentar(Pet pet){
        TipoComida comidaPet = pet.getStatus().getTipoComida();
        Gdx.app.log("Comida", "Comida do pet "+comidaPet);
        Gdx.app.log("Comida", "Tipo dessa comida "+tipoComida);
        if(comidaPet.equals(tipoComida)){
            if(hasComida()){
                int fomeAtualPet = pet.getStatus().getFomeAtual();

                int fomeMaxPet = pet.getStatus().getFomeMax();
                int fomeMinPet = pet.getStatus().getFomeMin();
                pet.getStatus().setFomeAtual(MathUtils.clamp(fomeAtualPet + qtdAlimento, fomeMinPet, fomeMaxPet));
            }

        }else{
            Gdx.app.log("Comida", "Pet "+pet.getSaveId()+" não gosta de "+tipoComida.toString());
        }
    }

    private boolean hasComida() {
        boolean hasComida = false;
        // Se tiver comida, já diminui a quantidade
        switch (tipoComida){
            case CARNE:
                int qtdCarne = gameManager.getQtdCarne();
                if(qtdCarne > 0){
                    hasComida = true;
                    gameManager.setQtdCarne(qtdCarne - 1);
                }
                break;
            case VEGETAL:
                int qtdVegetal = gameManager.getQtdVegetal();
                if(qtdVegetal > 0){
                    hasComida = true;
                    gameManager.setQtdVegetal(qtdVegetal - 1);
                }
                break;
        }

        return hasComida;
    }


    public void comprar() {
        int gold = gameManager.getGold();
        if(gold >= preco){
            switch (tipoComida){
                case CARNE:
                    int qtdCarne = gameManager.getQtdCarne();
                    gameManager.setQtdCarne(qtdCarne + 1);
                    break;
                case VEGETAL:
                    int qtdVegetal = gameManager.getQtdVegetal();
                    gameManager.setQtdVegetal(qtdVegetal + 1);
                    break;
            }
            gameManager.setGold(gold - preco);
            Gdx.app.log("Shop", "Comprou comida e gastou: "+preco);
        }else {
            Gdx.app.log("Shop", "you have no gold! preco da carne: "+preco);
        }
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

    public TipoComida getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(TipoComida tipoComida) {
        this.tipoComida = tipoComida;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }


}
