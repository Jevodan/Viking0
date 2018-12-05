package ru.jevo.animation.ships;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.jevo.animation.basic.Ship;
import ru.jevo.animation.basic.Sprite;
import ru.jevo.animation.service.Rect;

/**
 * Created by Alexander on 03.12.2018.
 */
public class Viking extends Ship {

    public Viking(TextureAtlas atlas) {
        super(atlas.findRegion("viking"));
        setHeightProportion(1f);
        setAngle(0);
    }


    @Override
    public void resize(Rect serviceRect) {
        super.resize(serviceRect);
        speed.set(0.1f,0.1f);
        System.out.println(mServiceRect.getHeight() + "????");
        pos.set(0, -mServiceRect.getHalfHeight() + this.getHalfHeight());
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(speed, delta); //скорость привязана к частоте кадров
    }
}
