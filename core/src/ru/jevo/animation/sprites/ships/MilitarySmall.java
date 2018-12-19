package ru.jevo.animation.sprites.ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.jevo.animation.basic.Pool;
import ru.jevo.animation.basic.Ship;
import ru.jevo.animation.basic.Sprite;
import ru.jevo.animation.basic.Weapon;
import ru.jevo.animation.pools.weapons.BulletPool;
import ru.jevo.animation.pools.weapons.LazerPool;
import ru.jevo.animation.sprites.weapon.Bullet;
import ru.jevo.animation.sprites.weapon.Laser;
import ru.jevo.animation.sprites.weapon.Mega;
import ru.jevo.animation.Weapons;

/**
 * Created by Alexander on 03.12.2018.
 */
public class MilitarySmall extends Ship {

    public MilitarySmall(Pool weaponPool, String weapon) {
        this.weaponEnum = weapon;
        regions = new TextureRegion[1];
        regions[0] = this.getRegion();
        setAngle(180);
        this.speed.set(0, -1.2f);
        setHeightProportion(1.1f);
        this.weaponPool = weaponPool;
        this.hP = 5;
    }

    public TextureRegion getRegion() {
        return this.enemyTextureAtlas.findRegion("family5/military_small");
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
        if (speedFire > 2.1f) {
            speedFire = 0;
            shoot();
        }
    }

    private void shoot() {
        weapon = (Weapon) weaponPool.obtain();
        weapon.setSpeedBul(weapon.getSpeedBul().rotate(180));
        weapon.set(this, mServiceRect, true);
    }

}
