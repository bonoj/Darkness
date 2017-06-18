package com.hk47.darkness;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Trikes {

    public static final String TAG = Trikes.class.getName();

    Constants.Difficulty mDifficulty;

    DelayedRemovalArray<Trike> mTrikesArray;
    Viewport mViewport;
    int mTrikesDodged;

    public Trikes(Viewport viewport, Constants.Difficulty difficulty) {
        mDifficulty = difficulty;
        mViewport = viewport;
        init();
    }

    public void init() {
        mTrikesArray = new DelayedRemovalArray<Trike>(false, 100);

        mTrikesDodged = 0;
    }

    public void update(float delta) {
        if (MathUtils.random() < delta * mDifficulty.mSpawnRate) {
            Vector2 newTrikePosition = new Vector2(
                    MathUtils.random() * mViewport.getWorldWidth(),
                    mViewport.getWorldHeight()
            );
            Trike newTrike = new Trike(newTrikePosition);
            mTrikesArray.add(newTrike);
        }

        for (Trike trike : mTrikesArray) {
            trike.update(delta);
        }

        // Remove Trikes that have fallen off of the screen
        mTrikesArray.begin();
        for (int i = 0; i < mTrikesArray.size; i++) {
            if (mTrikesArray.get(i).mPosition.y < -Constants.TRIKE_HEIGHT) {
                mTrikesDodged += 1;
                mTrikesArray.removeIndex(i);
            }
        }
        mTrikesArray.end();


    }

    public void render(ShapeRenderer renderer) {
        for (Trike trike : mTrikesArray) {
            trike.render(renderer);
        }
    }
}
