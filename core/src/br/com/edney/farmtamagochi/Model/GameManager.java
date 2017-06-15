package br.com.edney.farmtamagochi.Model;

/**
 * Created by Desktop on 16/05/2017.
 */

public class GameManager {
    private static GameManager gameManager;
    private int gold = 300;
    private int qtdPets;
    private int qtdCarne = 2;
    private int qtdVegetal = 2;

    public static GameManager getInstance(){
        if(gameManager == null){
            return gameManager = new GameManager();
        }else{
            return gameManager;
        }
    }

    private GameManager(){}

    public int getQtdPets() {
        return qtdPets;
    }

    public void setQtdPets(int qtdPets) {
        this.qtdPets = qtdPets;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getQtdCarne() {
        return qtdCarne;
    }

    public void setQtdCarne(int qtdCarne) {
        this.qtdCarne = qtdCarne;
    }

    public int getQtdVegetal() {
        return qtdVegetal;
    }

    public void setQtdVegetal(int qtdVegetal) {
        this.qtdVegetal = qtdVegetal;
    }
}
