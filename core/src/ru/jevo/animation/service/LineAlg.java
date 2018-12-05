package ru.jevo.animation.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Alexander on 28.11.2018.
 */

public class LineAlg {

    final private static float SCALAR_SPEED = 0.02f;

    final public static Vector2 position = new Vector2(0,0);
    final private static Vector2 speed = new Vector2(-0.01f, 0.0f);
    final private static Vector2 side = new Vector2(1, 0);
    final private static Vector2 clickVector = new Vector2();

    public static float getAngle(Matrix3 screenToService) {
        clickVector.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()).mul(screenToService);
        side.set(clickVector.sub(position)).nor();
        speed.nor();
        System.out.println(side.x + "  " + side.y + " " + side.angle());
     //   System.out.println(clickVector.x + "  " + clickVector.y);
    //    System.out.println(position.x + "  " + position.y);
        speed.set(side.scl(SCALAR_SPEED));
        return side.angle();
    }

    public static void setNewPosition() {
        position.add(speed);
    }
}
