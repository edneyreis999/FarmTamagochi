package br.com.edney.farmtamagochi.Util;

/**
 * Created by Desktop on 13/05/2017.
 */

public interface Clickable {

    public boolean isTouched = false;

    public boolean isTouched(int margem,float xWorldCoord, float yWorldCoord, float xScreenPosition, float yScreenPosition);
}
