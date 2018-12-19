package ru.jevo.animation.pools.weapons;

import ru.jevo.animation.basic.Pool;
import ru.jevo.animation.sprites.weapon.Bullet;
import ru.jevo.animation.sprites.weapon.SimpleBlaster;

/**
 * Created by Alexander on 09.12.2018.
 */
public class SimpleBlasterPool extends Pool<SimpleBlaster> {

    private static SimpleBlasterPool instance;

    public static synchronized SimpleBlasterPool getInstance() {
        if (instance == null) {
            instance = new SimpleBlasterPool();
        }
        return instance;
    }    
    
    @Override
    protected SimpleBlaster newObject() {
        return new SimpleBlaster() ;
    }
}
