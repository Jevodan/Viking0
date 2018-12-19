package ru.jevo.animation.sprites.ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.jevo.animation.basic.Pool;
import ru.jevo.animation.basic.Ship;
import ru.jevo.animation.basic.Weapon;

/**
 * Created by Alexander on 12.12.2018.
 */
public class MilitaryMedium extends Ship {

    public MilitaryMedium(Pool weaponPool, String weapon) {
        this.weaponEnum = weapon;
        regions = new TextureRegion[1];
        regions[0] = this.getRegion();
        setAngle(180);
        this.speed.set(0, -0.7f);
        setHeightProportion(1.4f);
        this.weaponPool = weaponPool;
        this.hP = 25;
        createWeapon(weapon);
    }

    public TextureRegion getRegion() {
        return this.enemyTextureAtlas.findRegion("family5/military_medium");
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(speed, delta); //скорость привязана к частоте кадров
        speedFire += delta;
        if (isOutside(mServiceRect))
            setDestroyed(true);

        if (speedFire > 1f) {
            speedFire = 0;
            shoot();
        }
    }

    private void shoot() {
        weapon = (Weapon) weaponPool.obtain();
        weapon.setSpeedBul(weapon.getSpeedBul().scl(8f).rotate(180));
        weapon.set(this, mServiceRect, true);

    }

}
