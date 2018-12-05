package ru.jevo.animation.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.jevo.animation.basic.Sprite;

/**
 * Created by Alexander on 02.12.2018.
 */
public class ExitButton extends Sprite {

    public ExitButton(TextureAtlas region) {
        super(region.findRegion("btExit"));
        setHeightProportion(1.5f);
        pos.set(0, - 5 * halfHeight);
    }
}
