package ru.jevo.animation.ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import ru.jevo.animation.basic.Ship;
import ru.jevo.animation.service.Rect;
import ru.jevo.animation.weapon.SimpleBlaster;

/**
 * Created by Alexander on 03.12.2018.
 */
public class MilitarySmall extends Ship {

    public MilitarySmall(TextureAtlas atlas) {
        super(atlas.findRegion("family5/military_small"));
        setHeightProportion(0.7f);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public void resize(Rect serviceRect) {
        super.resize(serviceRect);
        speed.set(0, -0.8f);
        System.out.println(mServiceRect.getLeft());
        pos.set(MathUtils.random(mServiceRect.getLeft(), mServiceRect.getRight()),
                MathUtils.random(mServiceRect.getHeight(), mServiceRect.getHeight() * 2));
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(speed, delta); //скорость привязана к частоте кадров
        speedFire += delta;

            if (speedFire > 5) {
                System.out.println("АПДЕКТ КОРАБЛЯ");
                fireBehavior.update(delta);
                speedFire = 0;
            }

    }
}
