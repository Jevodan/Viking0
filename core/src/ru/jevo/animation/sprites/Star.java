package ru.jevo.animation.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.jevo.animation.basic.Sprite;
import ru.jevo.animation.service.Rect;

/**
 * Created by Alexander on 02.12.2018.
 */
public class Star extends Sprite {

    private Rect mServiceRect;
    private Vector2 speed = new Vector2(); // скорость звезды

    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        setHeightProportion(MathUtils.random(0.05f,0.1f));
        speed.set(MathUtils.random(-0.05f, 0.05f), MathUtils.random(-0.8f, -0.1f));
    }

    @Override
    public void resize(Rect serviceRect) {
        super.resize(serviceRect);
        this.mServiceRect = serviceRect;
        final float posX = MathUtils.random(serviceRect.getLeft(), serviceRect.getRight());
        final float posY = MathUtils.random(serviceRect.getBottom(), serviceRect.getTop());
        pos.set(posX, posY);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(speed, delta); //скорость привязана к частоте кадров
        checkBounds();
    }

    private void checkBounds() {
        if (getRight() < mServiceRect.getLeft()) setLeft(mServiceRect.getRight());
        if (getLeft() > mServiceRect.getRight()) setRight(mServiceRect.getLeft());
        if (getTop() < mServiceRect.getBottom()) setBottom(mServiceRect.getTop());
    }
}
