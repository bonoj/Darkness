package com.hk47.darkness;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Star {

    public static final String TAG = Star.class.getName();

    Vector2 mPosition;
    Vector2 mVelocity;

    public Star(Vector2 position) {
        mPosition = position;
        mVelocity = new Vector2(0,-3);
    }

    public void update(float delta) {
        // Update velocity using icicle acceleration constant
        //mVelocity.mulAdd(Constants.TRIKE_ACCELERATION, delta);
        // Update position using velocity
        mPosition.mulAdd(mVelocity, delta);
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.WHITE);
        //renderer.circle(mPosition.x, mPosition.y, .3f);
        renderer.rect(mPosition.x, mPosition.y, .05f, .05f);
    }
}
