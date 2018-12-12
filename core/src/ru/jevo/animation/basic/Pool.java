package ru.jevo.animation.basic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

import ru.jevo.animation.pools.weapons.BulletPool;
import ru.jevo.animation.pools.weapons.SimpleBlasterPool;

/**
 * Created by Alexander on 08.12.2018.
 */
public abstract class Pool<T extends Sprite> {

    protected BulletPool mBulletPool;
    protected SimpleBlasterPool mBlasterPool;

    // активный пул спрайтов T
    protected List<T> activePool = new ArrayList<T>();
    // пассивный пул спрайтов T
    protected List<T> passivePool = new ArrayList<T>();

    protected abstract T newObject();

    public T obtain() {
        T poolObj;
        if (passivePool.isEmpty())
            poolObj = newObject();
        else
            poolObj = passivePool.remove(passivePool.size() - 1);
        activePool.add(poolObj);
        System.out.println("active/free:"+activePool.size() + "/" + passivePool.size());
        return poolObj;
    }

    public void updateActiveSprites(float delta) {
        for(int i = 0; i < activePool.size(); i++) {
            Sprite sprite = activePool.get(i);
            if (!sprite.isDestroyed()) {
                sprite.update(delta);
            }
        }
    }

    public void drawActiveSprites(SpriteBatch batch) {
        for(int i = 0; i < activePool.size(); i++) {
            Sprite sprite = activePool.get(i);
            if (!sprite.isDestroyed()) {
                sprite.draw(batch);
            }
        }
    }

    public void freeAllDestroyedActiveSprites() {
        for(int i = 0; i < activePool.size(); i++) {
            T sprite = activePool.get(i);
            if (sprite.isDestroyed()) {
                free(sprite);
                i--;
                sprite.setDestroyed(false);
            }
        }
    }

    private void free(T object) {
        if (activePool.remove(object)) {
            passivePool.add(object);
            System.out.println("active/free:"+activePool.size() + "/" + passivePool.size());
        }
    }

    public List<T> getactivePool() {
        return activePool;
    }

    public void dispose() {
        activePool.clear();
        passivePool.clear();
    }


}
