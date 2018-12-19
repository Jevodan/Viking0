package ru.jevo.animation.pools.weapons;

import ru.jevo.animation.basic.Pool;
import ru.jevo.animation.sprites.weapon.Bullet;
import ru.jevo.animation.sprites.weapon.Mega;

/**
 * Created by Alexander on 08.12.2018.
 */
public class MegaPool extends Pool<Mega> {

    private static MegaPool instance;

    public static synchronized MegaPool getInstance() {
        if (instance == null) {
            instance = new MegaPool();
        }
        return instance;
    }

    @Override
    protected Mega newObject() {
        return new Mega();
    }

}
