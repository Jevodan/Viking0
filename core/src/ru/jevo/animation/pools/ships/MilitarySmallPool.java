package ru.jevo.animation.pools.ships;

import ru.jevo.animation.basic.Pool;
import ru.jevo.animation.basic.test;
import ru.jevo.animation.pools.weapons.BulletPool;
import ru.jevo.animation.sprites.ships.MilitarySmall;


/**
 * Created by Alexander on 08.12.2018.
 */
public class MilitarySmallPool extends Pool<MilitarySmall> {

    private static String weapon;
    private Pool weaponPool;

    private static MilitarySmallPool instance;

    public static synchronized MilitarySmallPool getInstance(String weapon) {
        if (instance == null) {
            instance = new MilitarySmallPool(weapon);
        }
        return instance;
    }

    private MilitarySmallPool(String weapon) {
        this.weapon = weapon;
        this.weaponPool = this.createPool(weapon);
    }

    @Override
    protected MilitarySmall newObject() {
        return new MilitarySmall(weaponPool, weapon);
    }
}
