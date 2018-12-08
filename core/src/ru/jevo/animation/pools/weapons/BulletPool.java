package ru.jevo.animation.pools.weapons;

import ru.jevo.animation.basic.Pool;
import ru.jevo.animation.sprites.weapon.Bullet;

/**
 * Created by Alexander on 08.12.2018.
 */
public class BulletPool extends Pool<Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }

}
