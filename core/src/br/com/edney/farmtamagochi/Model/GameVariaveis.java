package br.com.edney.farmtamagochi.Model;

/**
 * Created by Desktop on 16/05/2017.
 */

public class GameVariaveis {
    private static GameVariaveis gameVariaveis;
    private int qtdPets;

    public static GameVariaveis getInstance(){
        if(gameVariaveis == null){
            return gameVariaveis = new GameVariaveis();
        }else{
            return gameVariaveis;
        }
    }

    private GameVariaveis(){

    }

    public int getQtdPets() {
        return qtdPets;
    }

    public void setQtdPets(int qtdPets) {
        this.qtdPets = qtdPets;
    }
}
