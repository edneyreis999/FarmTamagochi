package br.com.edney.farmtamagochi.Util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import br.com.edney.farmtamagochi.Screen.TownScreen;

import static br.com.edney.farmtamagochi.Util.Validate.zoomMax;
import static br.com.edney.farmtamagochi.Util.Validate.zoomMin;

/**
 * Created by Desktop on 07/05/2017.
 */

public class MeuGestureListener implements GestureDetector.GestureListener {

    private TownScreen screen;
    private float currentZoom;

    public MeuGestureListener(TownScreen screen){
        this.screen = screen;
        currentZoom = screen.getCameraTown().zoom;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        screen.getCameraTown().translate(-deltaX * currentZoom,deltaY * currentZoom);
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        currentZoom = screen.getCameraTown().zoom;
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        float currentZoom =  screen.getCameraTown().zoom;
        float zoomFinal = (initialDistance / distance) * currentZoom;

        if(zoomFinal > zoomMin && zoomFinal < zoomMax){
            screen.getCameraTown().zoom = zoomFinal;
        }
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {
        System.out.println("pinch");
    }
}
