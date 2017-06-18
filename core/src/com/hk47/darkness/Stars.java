package com.hk47.darkness;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Stars {

    public static final String TAG = Stars.class.getName();

    DelayedRemovalArray<Star> mStarArray;
    Viewport mViewport;

    public Stars(Viewport viewport) {
        mViewport = viewport;
        init();
    }

    public void init() {
        mStarArray = new DelayedRemovalArray<Star>(false, 100);
    }

    public void update(float delta) {
        if (MathUtils.random() < delta * 100) {
            Vector2 newStarPosition = new Vector2(
                    MathUtils.random() * mViewport.getWorldWidth(),
                    mViewport.getWorldHeight()
            );
            Star newStar = new Star(newStarPosition);
            mStarArray.add(newStar);
        }

        for (Star star : mStarArray) {
            star.update(delta);
        }

        // Remove stars that have left the screen
        mStarArray.begin();
        for (int i = 0; i < mStarArray.size; i++) {
            if (mStarArray.get(i).mPosition.y < -Constants.TRIKE_HEIGHT) {
                mStarArray.removeIndex(i);
            }
        }
        mStarArray.end();


    }

    public void render(ShapeRenderer renderer) {
        for (Star star : mStarArray) {
            star.render(renderer);
        }
    }
}
