package ru.jevo.animation.basic;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Alexander on 13.12.2018.
 */
public abstract class Titles extends Sprite {

    public Titles(TextureRegion region) {
        super(region);
        this.setHeightProportion(0.1f);
    }


}
