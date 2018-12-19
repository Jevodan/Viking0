package ru.jevo.animation.pools.weapons;

import ru.jevo.animation.basic.Pool;
import ru.jevo.animation.sprites.weapon.Bullet;
import ru.jevo.animation.sprites.weapon.Laser;

/**
 * Created by Alexander on 17.12.2018.
 */
public class LazerPool extends Pool<Laser> {

    private static LazerPool instance;

    public static synchronized LazerPool getInstance() {
        if (instance == null) {
            instance = new LazerPool();
        }
        return instance;
    }


    @Override
    protected Laser newObject() {
        return new Laser();
    }
}
