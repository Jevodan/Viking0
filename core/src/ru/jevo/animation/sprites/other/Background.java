package ru.jevo.animation.sprites.other;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.jevo.animation.basic.Sprite;
import ru.jevo.animation.service.Rect;

/**
 * Created by Alexander on 02.12.2018.
 */
public class Background extends Sprite {

    private Rect serviceRect;
    private Vector2 speed = new Vector2(0,-4.5f); // скорость звезды
    private TextureRegion region;

    public Background(TextureRegion region) {
        super(region);
        this.region = region;

    }

    @Override
    public void resize(Rect serviceRect) {
        super.resize(serviceRect);
        this.serviceRect = serviceRect;
        setHeightProportion(serviceRect.getHeight());
        pos.set(serviceRect.pos);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(speed, delta); //скорость привязана к частоте кадров
    }
}
