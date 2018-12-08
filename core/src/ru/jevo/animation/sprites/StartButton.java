package ru.jevo.animation.sprites;

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
        setHeightProportion(4f);
        pos.set(0, 1f);
    }

    @Override
    public void resize(Rect serviceRect) {
        super.resize(serviceRect);
    }

    @Override
    protected void actionPerformed() {
        mGame.setScreen(new GameScreen(mGame));
    }
}
