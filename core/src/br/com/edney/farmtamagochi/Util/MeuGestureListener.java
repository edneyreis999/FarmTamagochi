package br.com.edney.farmtamagochi.Util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import br.com.edney.farmtamagochi.Screen.TownScreen;
import br.com.edney.farmtamagochi.TamagochiFarm;

import static br.com.edney.farmtamagochi.Util.Validate.zoomMax;
import static br.com.edney.farmtamagochi.Util.Validate.zoomMin;

/**
 * Created by Desktop on 07/05/2017.
 */

public class MeuGestureListener implements GestureDetector.GestureListener {

    private TownScreen screen;
    private float currentZoom;
    private OrthographicCamera cam;

    public MeuGestureListener(TownScreen screen){
        this.screen = screen;
        currentZoom = screen.getCameraTown().zoom;
        cam = screen.getCameraTown();
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        Vector3 vec=new Vector3(x, y, 0);
        cam.unproject(vec);

        for (int i = 0; i < screen.getPets().size(); i++) {
            if(screen.getPets().get(i).isTouched(0, vec.x, vec.y, x, y)) {

            }
        }

        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        // Transforma as coordenadas de toque na tela em toque no world
        Vector3 vec=new Vector3(x, y, 0);
        cam.unproject(vec);

        Gdx.app.log("MeuGestureListener", "World coord x: "+ vec.x);
        Gdx.app.log("MeuGestureListener", "World coord y: "+ vec.y);
        Gdx.app.log("MeuGestureListener", "Screen coord x: "+ x);
        Gdx.app.log("MeuGestureListener", "Screen coord y: "+ y);

        // if sprite + 10 of px marge is touched
        for (int i = 0; i < screen.getPets().size(); i++) {
            if(screen.getPets().get(i).isTouched(0, vec.x, vec.y, x, y)) {
                screen.evoluirPet(screen.getPets().get(i));
            }
        }
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        screen.getCameraTown().translate(-deltaX * currentZoom,deltaY * currentZoom);
        // TODO ajustar calculo com zoom.

        // These values likely need to be scaled according to your world coordinates.
        // The left boundary of the map (x)
        int mapLeft = 0;
        // The right boundary of the map (x + width)
        //int mapRight = 0 + (int) screen.viewport.getWorldWidth();
        // TODO Arrumar isso aqui!! tem que ser dinamico
        int mapRight = 0 + 480;
        // The bottom boundary of the map (y)
        int mapBottom = 0;
        // The top boundary of the map (y + height)
        int mapTop = 0 + (int) screen.viewport.getWorldHeight();
        // The camera dimensions, halved
        float cameraHalfWidth = (cam.viewportWidth * cam.zoom) / 2 ;
        float cameraHalfHeight = (cam.viewportHeight * cam.zoom) / 2;

        // Move camera after player as normal

        float cameraLeft = cam.position.x - cameraHalfWidth;
        float cameraRight = cam.position.x + cameraHalfWidth;
        float cameraBottom = cam.position.y - cameraHalfHeight;
        float cameraTop = cam.position.y + cameraHalfHeight;

        Gdx.app.log("Camera", "cameraRight: "+cameraRight);
        Gdx.app.log("Camera", "mapRight: "+mapRight);
        Gdx.app.log("Camera", "zoom: "+cam.zoom);

        // Horizontal axis
        if(screen.viewport.getWorldWidth() < cam.viewportWidth)
        {
            cam.position.x = mapRight / 2;
        }
        else if(cameraLeft <= mapLeft)
        {
            cam.position.x = mapLeft + cameraHalfWidth;
        }
        else if(cameraRight >= mapRight)
        {
            cam.position.x = mapRight - cameraHalfWidth;
        }

        // Vertical axis
        if(screen.viewport.getWorldHeight() < cam.viewportHeight)
        {
            cam.position.y = mapTop / 2;
        }
        else if(cameraBottom <= mapBottom)
        {
            cam.position.y = mapBottom + cameraHalfHeight;
        }
        else if(cameraTop >= mapTop)
        {
            cam.position.y = mapTop - cameraHalfHeight;
        }
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
