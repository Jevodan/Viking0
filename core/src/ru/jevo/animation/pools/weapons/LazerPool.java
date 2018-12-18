package ru.jevo.animation.pools.weapons;

import ru.jevo.animation.basic.Pool;
import ru.jevo.animation.sprites.weapon.Laser;

/**
 * Created by Alexander on 17.12.2018.
 */
public class LazerPool extends Pool<Laser> {

    @Override
    protected Laser newObject() {
        return new Laser();
    }
}
