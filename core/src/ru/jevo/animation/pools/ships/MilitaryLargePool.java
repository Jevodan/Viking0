package ru.jevo.animation.pools.ships;

import ru.jevo.animation.basic.Pool;
import ru.jevo.animation.pools.weapons.BulletPool;
import ru.jevo.animation.sprites.ships.MilitaryLarge;
import ru.jevo.animation.sprites.ships.MilitarySmall;

/**
 * Created by Alexander on 08.12.2018.
 */
public class MilitaryLargePool extends Pool<MilitaryLarge> {

    public MilitaryLargePool(BulletPool bulletPool) {
        this.mBulletPool = bulletPool;
    }

    @Override
    protected MilitaryLarge newObject() {
        return new MilitaryLarge(mBulletPool);
    }
}
