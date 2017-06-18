package com.hk47.darkness;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Trike {

    public static final String TAG = Trike.class.getName();

    Vector2 mPosition;
    Vector2 mVelocity;

    public Trike(Vector2 position) {
        mPosition = position;
        mVelocity = new Vector2();
    }

    public void update(float delta) {
        // Update trike velocity using trike acceleration constant
        mVelocity.mulAdd(Constants.TRIKE_ACCELERATION, delta);
        // Update trike position using velocity
        mPosition.mulAdd(mVelocity, delta);
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.YELLOW);
        renderer.triangle(
                mPosition.x, mPosition.y,
                mPosition.x - Constants.TRIKE_WIDTH / 5, mPosition.y + Constants.TRIKE_HEIGHT + 10,
                mPosition.x + Constants.TRIKE_WIDTH / 5, mPosition.y + Constants.TRIKE_HEIGHT + 10
        );
//        renderer.setColor(Color.ORANGE);
//        renderer.triangle(
//                mPosition.x, mPosition.y,
//                mPosition.x - Constants.TRIKE_WIDTH / 2, mPosition.y + Constants.TRIKE_HEIGHT + 12,
//                mPosition.x + Constants.TRIKE_WIDTH / 2, mPosition.y + Constants.TRIKE_HEIGHT + 12
//        );
        renderer.setColor(Color.RED);
        renderer.triangle(
                mPosition.x, mPosition.y,
                mPosition.x - Constants.TRIKE_WIDTH / 4, mPosition.y + Constants.TRIKE_HEIGHT + 22,
                mPosition.x + Constants.TRIKE_WIDTH / 4, mPosition.y + Constants.TRIKE_HEIGHT + 22
        );
        renderer.setColor(Color.GRAY);
        renderer.triangle(mPosition.x, mPosition.y + Constants.TRIKE_HEIGHT + .2f,
                mPosition.x - Constants.TRIKE_WIDTH / 10, mPosition.y + Constants.TRIKE_HEIGHT,
                mPosition.x + Constants.TRIKE_WIDTH / 10, mPosition.y + Constants.TRIKE_HEIGHT
        );
        //renderer.rect(mPosition.x - Constants.TRIKE_WIDTH, mPosition.y + Constants.TRIKE_HEIGHT - .15f, 1f, .01f);
        renderer.triangle(
                mPosition.x, mPosition.y,
                mPosition.x - Constants.TRIKE_WIDTH / 2, mPosition.y + Constants.TRIKE_HEIGHT,
                mPosition.x + Constants.TRIKE_WIDTH / 2, mPosition.y + Constants.TRIKE_HEIGHT
        );
        renderer.setColor(Color.LIGHT_GRAY);
        renderer.triangle(
                mPosition.x, mPosition.y,
                mPosition.x - Constants.TRIKE_WIDTH / 3, mPosition.y + Constants.TRIKE_HEIGHT,
                mPosition.x + Constants.TRIKE_WIDTH / 3, mPosition.y + Constants.TRIKE_HEIGHT
        );
        renderer.setColor(Color.GRAY);
        renderer.triangle(
                mPosition.x, mPosition.y,
                mPosition.x - Constants.TRIKE_WIDTH / 5, mPosition.y + Constants.TRIKE_HEIGHT,
                mPosition.x + Constants.TRIKE_WIDTH / 5, mPosition.y + Constants.TRIKE_HEIGHT
        );
    }
}
