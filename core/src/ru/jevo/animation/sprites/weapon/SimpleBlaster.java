package ru.jevo.animation.sprites.weapon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.jevo.animation.basic.Fire;
import ru.jevo.animation.basic.Fireable;
import ru.jevo.animation.service.Rect;

/**
 * Created by Alexander on 03.12.2018.
 */
public class SimpleBlaster extends Fire implements Fireable {

    public SimpleBlaster() {
        super(mainTextureAtlas.findRegion("bulletEnemy"));
        this.shipRect = shipRect;
        setHeightProportion(0.2f);

        speed.set(0, -1.3f);
    }

    @Override
    public void fire(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public void resize(Rect shipRect) {
        super.resize(shipRect);
        System.out.println(shipRect.getLeft() + "----- " + shipRect.pos.y);
        pos.set(shipRect.pos);
    }

    @Override
    public void update(float delta) {
          super.update(delta);
        pos.mulAdd(speed, delta); //скорость привязана к частоте кадров
      //  System.out.println(speed);
    }
}
