package ru.jevo.animation.sprites.button_titles;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.jevo.animation.basic.Sprite;
import ru.jevo.animation.basic.Titles;

/**
 * Created by Alexander on 14.12.2018.
 */
public class GameOverTitle extends Titles {

    public float timeDispose = 0f;

    public GameOverTitle(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
        this.timerAnimation = 0f;
    }

    @Override
    public void update(float delta) {
        timerAnimation += delta;
        timeDispose += delta;
        if (timerAnimation > 0.03f && timeDispose < 2.1f) {
            timerAnimation = 0;
            this.scale += 0.1f;
        }
    }

}
