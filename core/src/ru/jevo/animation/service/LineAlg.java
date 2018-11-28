package ru.jevo.animation.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Alexander on 28.11.2018.
 */

public class LineAlg {

    final private static float SCALAR_SPEED = 2f;

    final public static Vector2 position = new Vector2(0, 0);
    final private static Vector2 speed = new Vector2(0.5f, 0.4f);
    final private static Vector2 side = new Vector2(1, 0);
    final private static Vector2 clickVector = new Vector2(0, 0);

    public static float getAngle() {
        clickVector.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        side.set(clickVector.sub(position)).nor();
        speed.nor();
        System.out.println(side.x + "  " + side.y + " " + side.angle());
        speed.set(side.scl(SCALAR_SPEED));
        return side.angle();
    }

    public static void setNewPosition() {
        position.add(speed);
    }
}
