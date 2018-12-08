package ru.jevo.animation.sprites.ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import ru.jevo.animation.basic.Ship;
import ru.jevo.animation.service.Rect;

/**
 * Created by Alexander on 03.12.2018.
 */
public class MilitarySmall extends Ship {

    public MilitarySmall() {
        regions = new TextureRegion[1];
        regions[0] = enemyTextureAtlas.findRegion("family5/military_small");
        setAngle(180);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(speed, delta); //скорость привязана к частоте кадров
       // speedFire += delta;
        if (isOutside(mServiceRect))
            setDestroyed(true);

    }
}
