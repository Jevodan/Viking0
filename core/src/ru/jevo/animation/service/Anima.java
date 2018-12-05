package ru.jevo.animation.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Alexander on 28.11.2018.
 */
public class Anima {

    final private static int FRAME_COLS = 31; //определяют константы, представляющие, сколько спрайтов будет расположено по вертикали и горизонтали в спрайт-листе.
    final private static int FRAME_ROWS = 1;
    final private static String ATLAS = "atlas/viking.png";
    final private static Texture walkSheet = new Texture(Gdx.files.internal(ATLAS));  //Текстура, которая будет содержать весь лист в виде одного изображения (текстуры).

    private static TextureRegion[] walkFrames; //Объявление walkFrames как массива объектов TextureRegion. Массив будет содержать каждый кадр (спрайт) анимации. Первый элемент содержит верхний левый кадр, второй элемент содержит следующий справа кард и так далее. При достижении последнего элемента ряда, следующий ряд начинается с самого левого элемента.
    public static TextureRegion currentFrame;

    public static void setTexture() {
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = FRAME_COLS - 1; j >= 0; j--) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        currentFrame = walkFrames[0];
    }

    public static TextureRegion setCurrentFrame(float angle) {
        float minAngle = 78.36f;
        float[] grades = {
                90, 101.612f, 113.224f, 124.836f, 136.448f, 148.06f, 159.672f, 171.284f,
                182.896f, 194.508f, 206.12f, 217.732f, 229.344f, 240.956f, 252.568f, 264.18f,
                275.792f, 287.404f, 299.016f, 310.628f, 322.24f, 333.852f, 345.464f, 357.076f,
                8.688f, 20.3f, 31.912f, 43.524f, 55.136f, 66.748f, 78.36f
        };
        for (int i = 0; i < grades.length; i++) {
            if (angle <= grades[i] && angle >= minAngle)
                currentFrame = walkFrames[i];
            minAngle = grades[i];
        }
        if (angle > (357.076f - 360f) && angle < 8.688f)
        currentFrame = walkFrames[23];
        return currentFrame;
    }

}
