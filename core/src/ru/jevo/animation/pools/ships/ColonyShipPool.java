package ru.jevo.animation.pools.ships;

import ru.jevo.animation.basic.Pool;
import ru.jevo.animation.pools.weapons.SimpleBlasterPool;
import ru.jevo.animation.sprites.ships.ColonyShip;
import ru.jevo.animation.sprites.ships.MilitarySmall;

/**
 * Created by Alexander on 08.12.2018.
 */
public class ColonyShipPool extends Pool<ColonyShip> {

    public ColonyShipPool(SimpleBlasterPool blasterPool) {
        this.mBlasterPool = blasterPool;
    }

    @Override
    protected ColonyShip newObject() {
        return new ColonyShip(mBlasterPool);
    }
}
