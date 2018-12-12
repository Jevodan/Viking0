package ru.jevo.animation.pools.ships;

import ru.jevo.animation.basic.Pool;
import ru.jevo.animation.pools.weapons.BulletPool;
import ru.jevo.animation.sprites.ships.MilitaryMedium;
import ru.jevo.animation.sprites.ships.MilitarySmall;

/**
 * Created by Alexander on 08.12.2018.
 */
public class MilitaryMediumPool extends Pool<MilitaryMedium> {

    public MilitaryMediumPool(BulletPool bulletPool) {
        this.mBulletPool = bulletPool;
    }

    @Override
    protected MilitaryMedium newObject() {
        return new MilitaryMedium(mBulletPool);
    }
}
