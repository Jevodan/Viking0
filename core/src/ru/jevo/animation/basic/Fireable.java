package ru.jevo.animation.basic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.jevo.animation.service.Rect;

/**
 * Created by Alexander on 03.12.2018.
 */
public interface Fireable {

    void fire(SpriteBatch batch);

    void update(float delta);

    void resize(Rect shipRect);

}
