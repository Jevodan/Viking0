package ru.jevo.animation.sprites.ships;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.jevo.animation.Weapons;
import ru.jevo.animation.basic.Pool;
import ru.jevo.animation.basic.Ship;
import ru.jevo.animation.basic.Weapon;
import ru.jevo.animation.pools.weapons.BulletPool;
import ru.jevo.animation.pools.weapons.LazerPool;
import ru.jevo.animation.pools.weapons.SimpleBlasterPool;
import ru.jevo.animation.service.Rect;
import ru.jevo.animation.sprites.weapon.Bullet;
import ru.jevo.animation.sprites.weapon.Laser;
import ru.jevo.animation.sprites.weapon.Mega;

import static ru.jevo.animation.basic.Const.BULLET;
import static ru.jevo.animation.basic.Const.LAZER;
import static ru.jevo.animation.basic.Const.SIMPLE_BLASTER;

/**
 * Created by Alexander on 03.12.2018.
 */
public class Viking extends Ship {

    public static final int DAMAGE_TARAN = 100;

    private boolean pressedLeft;
    private boolean pressedRight;

    private Vector2 ukaz = new Vector2();

    private int hP = 100;

    public Viking(TextureAtlas atlas, String weapon) {
        super(atlas.findRegion("viking"), 1, 2, 2);
        this.weaponEnum = weapon;
        setHeightProportion(1.2f);
        setAngle(0);
        speedVector.set(1.5f, 0);
        createWeapon(weapon);
    }


    @Override
    public void resize(Rect serviceRect) {
        super.resize(serviceRect);
        System.out.println(mServiceRect.getHeight() + "????");
        pos.set(0, -mServiceRect.getHalfHeight() + this.getHalfHeight());
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        check();
        speedFire += delta;
        timeDamaged += delta;
        pos.mulAdd(speed, delta); //скорость привязана к частоте кадров
        if (this.getCurrentFrame() == 1 && timeDamaged > 0.5f) {
            timeDamaged = 0;
            this.setCurrentFrame(0);
        }
    }

    private void check() {
        if (pos.x + this.getHalfWidth() > mServiceRect.getHalfWidth()) {
            stop();
            pos.x -= 0.01f;
        }
        if (pos.x - this.getHalfWidth() < -mServiceRect.getHalfWidth()) {
            stop();
            pos.x += 0.01f;
        }

    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
/*
        position.set(sprite.getX(), sprite.getY());
        dir.set(touch).sub(position).nor();
        velocity.set(dir).scl(speed);
        movement.set(velocity).scl(deltaTime);
        if (position.dst2(touch) > movement.len2()) {
            position.add(movement);
        } else {
            position.set(touch);
        }
        sprite.setX(position.x);
        sprite.setY(position.y);
*/

        ukaz.set(touch).sub(pos).nor();
        System.out.println("Направление " + ukaz);
        //  speedVector.scl(ukaz);
        speed.set(ukaz).scl(speedVector);
        pos.add(speed);
        System.out.println("Скорость" + speed);


        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        return super.touchUp(touch, pointer);

    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                if (pressedLeft)
                    goLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                if (pressedRight)
                    goRight();
                break;
        }

        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight)
                    goRight();
                else
                    stop();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft)
                    goLeft();
                else
                    stop();
                break;
            case Input.Keys.UP:
                speedShoot();
                break;
        }
        return false;
    }

    private void speedShoot() {
        if (speedFire > 0.5f) {
            shoot();
            speedFire = 0;
        }
    }

    private void stop() {
        speed.setZero();
    }

    private void goRight() {
        speed.set(speedVector);
        speedShoot();
    }

    private void goLeft() {
        speed.set(speedVector).rotate(180);
        speedShoot();

    }

    private void goUp() {
        speed.set(speedVector).rotate(90);
        speedShoot();
    }

    private void goBottom() {
        speed.set(speedVector).rotate(270);
        speedShoot();
    }

    private void shoot() {
        float angleRot = -90f;
        for (int i = 0; i < 3; i++) {
            weapon = (Weapon) weaponPool.obtain();
            angleRot += 45f;
            weapon.setSpeedBul(weapon.getSpeedBul().cpy().rotate(angleRot));
            weapon.set(this, mServiceRect, false);
        }
    }

    public int gethP() {
        return hP;
    }

    public void sethP(int hP) {
        this.hP = hP;
    }

    public boolean isBulletCollision(Rect bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > pos.y
                || bullet.getTop() < getBottom());
    }


    @Override
    protected TextureRegion getRegion() {
        return this.enemyTextureAtlas.findRegion("viking");
    }


}
