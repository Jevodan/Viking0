package ru.jevo.animation.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

import ru.jevo.animation.basic.BasicScreen;


public class GameScreen extends BasicScreen {

    private static final int BUCKET_SIZE = 64;

    final private Texture dropImage = new Texture("images/droplet.png");
    final private Texture bucketImage = new Texture("images/bucket.png");
    final private Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("sounds/kup.wav"));
    final private Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/rain.mp3"));
    final private Rectangle bucket = new Rectangle();
    final private Vector3 touchPos = new Vector3();
    final private Array<Rectangle> rainDrops = new Array<Rectangle>();
    private long lastDropTime;
    private int dropsGathered;
    final private BitmapFont font = new BitmapFont();


    @Override
    public void show() {
        super.show();
        mCamera.setToOrtho(false, 800, 480);
        rainMusic.setLooping(true); // повторение музыки
        rainMusic.play();
        bucket.x = 800 / 2 - BUCKET_SIZE / 2; // по центру,учитывая размер ведра
        bucket.y = 20;
        bucket.width = BUCKET_SIZE;
        bucket.height = BUCKET_SIZE;
        spawnRainDrop();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 0.2f, 0.9f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mCamera.update();
        batch.setProjectionMatrix(mCamera.combined); //спрайтбатч нарисует все,что будет находиться в заданных координатах.
        batch.begin();
        font.draw(batch, "Drops Collected: " + dropsGathered, 0, 480);
        batch.draw(bucketImage, bucket.x, bucket.y);
        for (Rectangle raindrop : rainDrops) {
            batch.draw(dropImage, raindrop.x, raindrop.y);
        }
        if (dropsGathered > 5){
            font.draw(batch, "YOUUUUUU WIIINNN!!!!: " + dropsGathered, 350, 240);
            // STOPSHIP: 25.11.2018
            pause();
            Gdx.app.exit();
        }
        batch.end();

        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            mCamera.unproject(touchPos); //пробразование координат в систему координат камеры
            bucket.x = (int) (touchPos.x - 64 / 2);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();

        if (bucket.x < 0) bucket.x = 0;
        if (bucket.x > 800 - BUCKET_SIZE) bucket.x = 800 - BUCKET_SIZE;

        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRainDrop();

        Iterator<Rectangle> iter = rainDrops.iterator();
        while (iter.hasNext()) {
            Rectangle rainDrop = iter.next();
            rainDrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (rainDrop.y + 64 < 0) iter.remove();
            if (rainDrop.overlaps(bucket)) {
                dropsGathered++;
                dropSound.play();
                iter.remove();
            }
        }
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
        dropImage.dispose();
        dropSound.dispose();
        bucketImage.dispose();
        rainMusic.dispose();
    }

    private void spawnRainDrop() {
        Rectangle rainDrop = new Rectangle();
        rainDrop.x = MathUtils.random(0, 800 - BUCKET_SIZE);
        rainDrop.y = 480;
        rainDrop.width = BUCKET_SIZE;
        rainDrop.height = BUCKET_SIZE;
        rainDrops.add(rainDrop);
        lastDropTime = TimeUtils.nanoTime();
    }
}
