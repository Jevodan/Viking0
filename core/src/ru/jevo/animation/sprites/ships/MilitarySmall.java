package ru.jevo.animation.sprites.ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.jevo.animation.basic.Ship;
import ru.jevo.animation.pools.weapons.BulletPool;
import ru.jevo.animation.sprites.weapon.Bullet;

/**
 * Created by Alexander on 03.12.2018.
 */
public class MilitarySmall extends Ship {

    public MilitarySmall(BulletPool bulletPool) {
        regions = new TextureRegion[1];
        regions[0] = this.getRegion();
        setAngle(180);
        this.speed.set(0, -1.2f);
        setHeightProportion(1f);
        this.bulletPool = bulletPool;
        this.speedFire = 1f;
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
        if (isOutside(mServiceRect))
            setDestroyed(true);
        if (speedFire > 2.1f) {
            speedFire = 0;
            shoot();
        }
    }

    private void shoot() {
        System.out.println("огонь врага");
        Bullet bullet = bulletPool.obtain();
        bullet.setSpeedBul(bullet.getSpeedBul().rotate(180));
        bullet.set(this, getMainTextureAtlas().findRegion("bulletEnemy"), 0.1f, mServiceRect);

    }

}
