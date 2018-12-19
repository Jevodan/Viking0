package ru.jevo.animation.pools.weapons;

import ru.jevo.animation.basic.Pool;
import ru.jevo.animation.sprites.weapon.Blaster;
import ru.jevo.animation.sprites.weapon.Bullet;

/**
 * Created by Alexander on 08.12.2018.
 */
public class BlasterPool extends Pool<Blaster> {

    private static BlasterPool instance;

    public static synchronized BlasterPool getInstance() {
        if (instance == null) {
            instance = new BlasterPool();
        }
        return instance;
    }

    @Override
    protected Blaster newObject() {
        return new Blaster();
    }

}
