package ru.jevo.animation.sprites.ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.jevo.animation.basic.Ship;
import ru.jevo.animation.pools.weapons.BulletPool;
import ru.jevo.animation.sprites.weapon.Bullet;

/**
 * Created by Alexander on 12.12.2018.
 */
public class MilitaryLarge extends Ship {

    public MilitaryLarge(BulletPool bulletPool) {
        regions = new TextureRegion[1];
        regions[0] = this.getRegion();
        setAngle(180);
        this.speed.set(0, -0.5f);
        setHeightProportion(1f);
        this.bulletPool = bulletPool;

    }

    public TextureRegion getRegion(){
        return this.enemyTextureAtlas.findRegion("family5/military_large");
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

        if (speedFire > 0.5f) {
            speedFire = 0;
            shoot();
        }

    }

    private void shoot() {
        System.out.println("огонь врага");
        Bullet bullet = bulletPool.obtain();
        System.out.println(getMainTextureAtlas().findRegion("bulletEnemy"));
        bullet.setSpeedBul(bullet.getSpeedBul().rotate(180));
        bullet.set(this, getMainTextureAtlas().findRegion("bulletEnemy"),  0.1f, mServiceRect, 1);

    }

}
