package ru.jevo.animation.sprites.other;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.jevo.animation.basic.Sprite;

/**
 * Created by Alexander on 13.12.2018.
 */
public class Explosion extends Sprite {

    private float animateInterval = 0.00001f;
    private float animateTimer;

    private Sound explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosive.wav"));

    public Explosion(TextureRegion region, int cols, int rows, int frames) {
        super(region, cols, rows, frames);

    }

    public void set(float height, Vector2 pos) {
        this.pos = pos;
        setHeightProportion(height);
        explosionSound.play();
    }

    @Override
    public void update(float delta) {
        animateTimer += delta;
        if (animateTimer >= animateInterval) {
            animateTimer = 0f;
            if (++currentFrame == regions.length) {
                setDestroyed(true);
                currentFrame = 0;
            }
        }
    }


}
