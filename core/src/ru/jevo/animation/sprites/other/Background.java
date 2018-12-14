package ru.jevo.animation.sprites.other;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.jevo.animation.basic.Sprite;
import ru.jevo.animation.service.Rect;

/**
 * Created by Alexander on 02.12.2018.
 */
public class Background extends Sprite {

    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect serviceRect) {
        setHeightProportion(serviceRect.getHeight());
        pos.set(serviceRect.pos);
    }
}
