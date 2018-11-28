package ru.jevo.animation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationTest implements ApplicationListener {

    private static final int FRAME_COLS = 31; //определяют константы, представляющие, сколько спрайтов будет расположено по вертикали и горизонтали в спрайт-листе.
    private static final int FRAME_ROWS = 1;
    private static final String ATLAS = "atlas/viking.png";

    Animation walkAnimation; //объявление walkAnimation объекта, который является реализацией libGDX анимации.
    Texture walkSheet;  //Текстура, которая будет содержать весь лист в виде одного изображения (текстуры).
    TextureRegion[] walkFrames; //Объявление walkFrames как массива объектов TextureRegion. Массив будет содержать каждый кадр (спрайт) анимации. Первый элемент содержит верхний левый кадр, второй элемент содержит следующий справа кард и так далее. При достижении последнего элемента ряда, следующий ряд начинается с самого левого элемента.
    SpriteBatch mSpriteBatch;
    TextureRegion currentFrame;
    float stateTime;


    @Override
    public void create() {
        walkSheet = new Texture(Gdx.files.internal(ATLAS)); //Создает текстуру из animation_sheet.png файла, находящегося в assets директории проекта (смотрите настройку проекта).

        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = FRAME_COLS  - 1; j >= 0; j--) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(0.035f, walkFrames);
        mSpriteBatch = new SpriteBatch();
        stateTime = 0f;

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime, true);
        mSpriteBatch.begin();
        mSpriteBatch.draw(currentFrame, 100, 100);
        mSpriteBatch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        mSpriteBatch.dispose();
    }
}
