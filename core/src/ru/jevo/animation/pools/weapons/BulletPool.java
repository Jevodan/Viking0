package ru.jevo.animation.pools.weapons;

import ru.jevo.animation.basic.Pool;
import ru.jevo.animation.pools.ships.MilitarySmallPool;
import ru.jevo.animation.sprites.weapon.Bullet;

/**
 * Created by Alexander on 08.12.2018.
 */
public class BulletPool extends Pool<Bullet> {

    private static BulletPool instance;

    public static synchronized BulletPool getInstance() {
        if (instance == null) {
            instance = new BulletPool();
        }
        return instance;
    }

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }

}
