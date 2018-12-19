package ru.jevo.animation.sprites.ships;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.jevo.animation.basic.Ship;
import ru.jevo.animation.basic.Weapon;
import ru.jevo.animation.pools.weapons.BlasterPool;
import ru.jevo.animation.pools.weapons.SimpleBlasterPool;
import ru.jevo.animation.sprites.weapon.Bullet;
import ru.jevo.animation.sprites.weapon.SimpleBlaster;

/**
 * Created by Alexander on 12.12.2018.
 */
public class ColonyShip extends Ship {

    public ColonyShip(SimpleBlasterPool bulletPool) {
        regions = new TextureRegion[1];
        regions[0] = this.getRegion();
        setAngle(180);
        this.speed.set(0, -0.3f);
        setHeightProportion(1f);
     //   this.BlasterPool = bulletPool;

    }

    public TextureRegion getRegion(){
        return this.enemyTextureAtlas.findRegion("family5/colonyship");
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

        if (speedFire > 4f) {
            speedFire = 0;
            shoot();
        }

    }

    private void shoot() {
        System.out.println("огонь");

        float angleRot = -90f;
        for (int i = 0; i < 3; i++) {
          //  SimpleBlaster bullet = BlasterPool.obtain();
            angleRot += 45f;
            System.out.println("Угол:" + angleRot);
        //    bullet.setSpeedBul(bullet.getSpeedBul().rotate(angleRot).rotate(180));
          //  bullet.set(this, 0.1f, mServiceRect, 1);
        }
    }

}
