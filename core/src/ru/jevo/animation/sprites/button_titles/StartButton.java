package ru.jevo.animation.sprites.button_titles;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.jevo.animation.basic.Button;
import ru.jevo.animation.screens.GameScreen;
import ru.jevo.animation.service.Rect;


/**
 * Created by Alexander on 02.12.2018.
 */
public class StartButton extends Button {

    private Game mGame;

    public StartButton(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("btPlay"));
        this.mGame = game;
        setHeightProportion(2f);
        pos.set(-3.2f + getHalfWidth(), -4.6f + getHalfHeight());
    }

    /*
    @Override
    public void resize(Rect serviceRect) {
        super.resize(serviceRect);
    }
    */

    @Override
    protected void actionPerformed() {
        mGame.setScreen(new GameScreen(mGame));
    }
}
