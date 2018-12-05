package ru.jevo.animation.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.jevo.animation.basic.Sprite;


/**
 * Created by Alexander on 02.12.2018.
 */
public class StartButton extends Sprite {

    public StartButton(TextureAtlas region) {
        super(region.findRegion("btPlay"));
        setHeightProportion(4f);
        pos.set(0, 1f);
    }

}
