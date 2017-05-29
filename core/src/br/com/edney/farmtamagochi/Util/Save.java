package br.com.edney.farmtamagochi.Util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;

import br.com.edney.farmtamagochi.Model.Cavalo;
import br.com.edney.farmtamagochi.Model.Status;
import br.com.edney.farmtamagochi.Enum.Especie;
import br.com.edney.farmtamagochi.Model.GameVariaveis;
import br.com.edney.farmtamagochi.Model.Pet;
import br.com.edney.farmtamagochi.Enum.Tamanho;
import br.com.edney.farmtamagochi.Model.Urso;
import br.com.edney.farmtamagochi.Screen.TownScreen;

/**
 * Created by Desktop on 15/05/2017.
 */

public class Save {
    private static Save save;
    private static Preferences gamePreferences;

    public static Save getInstance(){
        if(save == null){
            save = new Save();
        }
        return save;
    }

    public void salvarPets(ArrayList<Pet> pets){
        for (int i = 0; i < pets.size(); i++) {
            salvarPet(pets.get(i));
        }
    }

    public void saveGame(TownScreen town){
        GameVariaveis gameVariaveis = GameVariaveis.getInstance();
        gamePreferences = Gdx.app.getPreferences("game");
        gamePreferences.putInteger(GameSave.QTD_PET.toString(), gameVariaveis.getQtdPets());
        gamePreferences.flush();
        salvarPets(town.getPets());
    }

    public GameVariaveis loadGame(){
        gamePreferences = Gdx.app.getPreferences("game");
        int qtdPets = gamePreferences.getInteger(GameSave.QTD_PET.toString());
        GameVariaveis gameVariaveis = GameVariaveis.getInstance();
        gameVariaveis.setQtdPets(qtdPets);
        return gameVariaveis;
    }

    public ArrayList<Pet> loadPets(){
        gamePreferences = Gdx.app.getPreferences("game");
        int qtdPets = gamePreferences.getInteger(GameSave.QTD_PET.toString());
        ArrayList<Pet> pets = new ArrayList<Pet>();
        for (int i = 1; i <= qtdPets; i++) {
            Pet pet = loadPet(i);
            pets.add(pet);
        }
        return pets;
    }

    public void salvarPet(Pet pet){
        Preferences preferences;
        preferences = Gdx.app.getPreferences(String.valueOf(pet.getSaveId()));
        // basicos
        preferences.putInteger(PetSave.PET_SAVE_IDD.toString(), pet.getSaveId());
        preferences.putInteger(PetSave.PET_TAMANHO.toString(), pet.getTamanho().ordinal());
        preferences.putInteger(PetSave.PET_ESPECIE.toString(), pet.getEspecie().ordinal());
        //posicao
        preferences.putFloat(PetSave.PET_POS_X.toString(),pet.getX());
        preferences.putFloat(PetSave.PET_POS_Y.toString(), pet.getY());
        //status
        preferences.putInteger(PetSave.PET_FOME_ATUALL.toString(), pet.getStatus().getFomeAtual());
        preferences.putFloat(PetSave.PET_TIME_TO_EVOLVE.toString(), pet.getStatus().getTimeToEvolve());
        preferences.flush();
    }

    private Pet loadPet(int index){
        Preferences preferences = Gdx.app.getPreferences(String.valueOf(index));
        int tamanho = preferences.getInteger(PetSave.PET_TAMANHO.toString());
        int especieId = preferences.getInteger(PetSave.PET_ESPECIE.toString());
        float posX = preferences.getFloat(PetSave.PET_POS_X.toString());
        float posY = preferences.getFloat(PetSave.PET_POS_Y.toString());
        int saveId = preferences.getInteger(PetSave.PET_SAVE_IDD.toString());
        int fomeAtual = preferences.getInteger(PetSave.PET_FOME_ATUALL.toString());
        float timeToEvolve = preferences.getFloat(PetSave.PET_TIME_TO_EVOLVE.toString());
        Gdx.app.log("Save", "Load pet ID: "+String.valueOf(saveId));

        Pet p = null;
        Especie especie = Especie.values()[especieId];

        Status status = new Status();
        status.setFomeAtual(fomeAtual);
        status.setTimeToEvolve(timeToEvolve);
        switch (especie){
            case URSO:
                Urso urso = new Urso(posX, posY, saveId, Tamanho.values()[tamanho]);
                p = urso;
                p.setStatus(status);
                break;
            case CAVALO:
                Cavalo cavalo = new Cavalo(posX, posY, saveId, Tamanho.values()[tamanho]);
                p = cavalo;
                p.setStatus(status);
                break;
        }

        return p;
    }

    private enum GameSave {
        QTD_PET;
    }

    private enum PetSave {
        PET_SAVE_IDD,
        PET_ESPECIE,
        PET_TAMANHO,
        PET_POS_X,
        PET_POS_Y,
        PET_FOME_ATUALL,
        PET_TIME_TO_EVOLVE;
    }
}

