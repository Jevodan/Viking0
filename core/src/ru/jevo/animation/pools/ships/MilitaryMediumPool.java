package ru.jevo.animation.pools.ships;

import ru.jevo.animation.basic.Pool;
import ru.jevo.animation.pools.weapons.BulletPool;
import ru.jevo.animation.sprites.ships.MilitaryMedium;
import ru.jevo.animation.sprites.ships.MilitarySmall;

/**
 * Created by Alexander on 08.12.2018.
 */
public class MilitaryMediumPool extends Pool<MilitaryMedium> {

    private static String weapon;
    private Pool weaponPool;

    private static MilitaryMediumPool instance;

    public static synchronized MilitaryMediumPool getInstance(String weapon) {
        if (instance == null) {
            instance = new MilitaryMediumPool(weapon);
        }
        return instance;
    }

    private MilitaryMediumPool(String weapon) {
        this.weapon = weapon;
        this.weaponPool = this.createPool(weapon);
    }

    @Override
    protected MilitaryMedium newObject() {
        return new MilitaryMedium(weaponPool, weapon);
    }
    
}
