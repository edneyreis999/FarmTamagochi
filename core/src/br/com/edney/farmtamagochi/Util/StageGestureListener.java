package br.com.edney.farmtamagochi.Util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

import br.com.edney.farmtamagochi.Screen.TownScreen;

import static br.com.edney.farmtamagochi.Util.Validate.zoomMax;
import static br.com.edney.farmtamagochi.Util.Validate.zoomMin;

/**
 * Created by Desktop on 07/05/2017.
 */

public class StageGestureListener implements GestureDetector.GestureListener {

    private Stage screen;

    public StageGestureListener(Stage stage){
        this.screen = stage;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        Vector3 vec=new Vector3(x, y, 0);
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
        Gdx.app.log("Camera", "Panzei: ");
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {

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
