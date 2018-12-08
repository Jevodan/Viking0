package ru.jevo.animation.service;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Alexander on 08.12.2018.
 */
public class Regions {

    /**
     * Возвращает нарезанный на спрайты входящий спрайт в виде массива
     *
     * @param region
     * @param rows
     * @param cols
     * @param frames
     * @return
     */
    public static TextureRegion[] split(TextureRegion region, int rows, int cols, int frames) {
        if (region == null) throw new RuntimeException("split null region");
        final TextureRegion[] regions = new TextureRegion[frames];
        final int spriteWidth = region.getRegionWidth() / cols;
        final int spriteHeight = region.getRegionHeight() / rows;
        int frame = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                regions[frame] = new TextureRegion(region, spriteWidth * j, spriteHeight * i, spriteWidth, spriteHeight);
                if (frame == frames - 1) return regions;
                frame++;
            }
        }
        return regions;
    }

}
