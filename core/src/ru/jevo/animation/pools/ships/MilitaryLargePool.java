package ru.jevo.animation.pools.ships;

import ru.jevo.animation.basic.Pool;
import ru.jevo.animation.pools.weapons.BulletPool;
import ru.jevo.animation.sprites.ships.MilitaryLarge;
import ru.jevo.animation.sprites.ships.MilitarySmall;

/**
 * Created by Alexander on 08.12.2018.
 */
public class MilitaryLargePool extends Pool<MilitaryLarge> {

    private static String weapon;
    private Pool weaponPool;

    private static MilitaryLargePool instance;

    public static synchronized MilitaryLargePool getInstance(String weapon) {
        if (instance == null) {
            instance = new MilitaryLargePool(weapon);
        }
        return instance;
    }

    private MilitaryLargePool(String weapon) {
        this.weapon = weapon;
        this.weaponPool = this.createPool(weapon);
    }

    @Override
    protected MilitaryLarge newObject() {
        return new MilitaryLarge(weaponPool, weapon);
    }
}
