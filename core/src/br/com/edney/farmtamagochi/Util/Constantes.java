package br.com.edney.farmtamagochi.Util;

import com.badlogic.gdx.Gdx;

/**
 * Created by Notebook on 10/05/2017.
 */

public class Constantes {
    //Constantes de game
    public static final int V_WIDTH = 800;
    public static final int V_HEIGHT = 400;
    public static final int TIME_TO_AUTO_SAVE = 10;

    public static float ZOOM_MIN = 0.02f;
    public static float ZOOM_MAX = 0.5f;
    public static float ZOOM_SPEED = 0.1f; // não funciona ainda// ;

    // Constantes de pet
    public static int petsCorpoRaio = (int) (0.03f * V_WIDTH);
    public static final int QTD_SPRITES_OVO = 3;
    //Urso
    public static final int QTD_SPRITES_URSO_PEQUENO = 22;
    public static final int QTD_SPRITES_URSO_MEDIO = 10;
    public static final int QTD_SPRITES_URSO_GRANDE = 8;
    //Cavalo
    public static final int QTD_SPRITES_CAVALO_PEQUENO = 9;
    public static final int QTD_SPRITES_CAVALO_MEDIO = 10;
    public static final int QTD_SPRITES_CAVALO_GRANDE = 9;
}
