package ru.jevo.animation.pools.other;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.jevo.animation.basic.Pool;
import ru.jevo.animation.basic.Sprite;
import ru.jevo.animation.sprites.other.Explosion;

/**
 * Created by Alexander on 13.12.2018.
 */
public class ExplosionPool extends Pool<Explosion> {

    private TextureRegion explosionRegion;
    private Sound expSound;

    public ExplosionPool(TextureAtlas atlas) {
        explosionRegion = atlas.findRegion("explosion");
    }

    @Override
    protected Explosion newObject() {
        return new Explosion(explosionRegion, 9,9, 74);
    }
}
