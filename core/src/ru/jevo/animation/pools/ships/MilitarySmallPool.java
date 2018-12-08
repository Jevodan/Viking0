package ru.jevo.animation.pools.ships;

import ru.jevo.animation.basic.Pool;
import ru.jevo.animation.sprites.ships.MilitarySmall;

/**
 * Created by Alexander on 08.12.2018.
 */
public class MilitarySmallPool extends Pool<MilitarySmall> {

    @Override
    protected MilitarySmall newObject() {
        return new MilitarySmall();
    }
}
