package ru.jevo.animation.sprites.button_titles;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.jevo.animation.basic.Titles;

/**
 * Created by Alexander on 13.12.2018.
 */
public class NewGameTitle extends Titles {

    public float timeDispose = 0f;

    public NewGameTitle(TextureAtlas atlas) {
        super(atlas.findRegion("button_new_game"));
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
        if (timeDispose > 2.5f) {
            this.scale -= 0.1f;
        }
        if (this.timeDispose > 4f)
            this.scale = 0f;
    }
}