package ru.jevo.animation.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.jevo.animation.basic.Button;
import ru.jevo.animation.basic.Sprite;

/**
 * Created by Alexander on 02.12.2018.
 */
public class ExitButton extends Button {

    public ExitButton(TextureAtlas region) {
        super(region.findRegion("btExit"));
        setHeightProportion(1.5f);
        pos.set(0, - 5 * halfHeight);
    }

    @Override
    protected void actionPerformed() {
        Gdx.app.exit();
    }
}
