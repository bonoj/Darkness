package com.hk47.darkness;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

public class Player {

    public static final String TAG = Player.class.getName();

    Viewport mViewport;
    Vector2 mPosition;
    private Vector2 mVelocity;
    int mDeaths;


    public Player(Viewport viewport) {
        mViewport = viewport;
        mDeaths = 0;
        init();
    }

    public void init() {
        mPosition = new Vector2(mViewport.getWorldWidth() / 2, Constants.PLAYER_SHIP_HEIGHT);
        mVelocity = new Vector2();
    }

    public void update(float delta) {

        // Movement with keyboard
        if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
            mPosition.x -= delta * Constants.PLAYER_MOVEMENT_SPEED;

        } else if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
            mPosition.x += delta * Constants.PLAYER_MOVEMENT_SPEED;
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)) {
            mPosition.y -= delta * 3;

        } else if (Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W)) {
            mPosition.y += delta * 3;
        }

        // Movement with accelerometer
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            float accelerometerInput = -Gdx.input.getAccelerometerY() / (Constants.ACCELEROMETER_SENSITIVITY * Constants.GRAVITATIONAL_ACCELERATION);
            mPosition.x += -delta * accelerometerInput * Constants.PLAYER_MOVEMENT_SPEED;

            float accelerometerInputVertical = -Gdx.input.getAccelerometerZ() / (Constants.ACCELEROMETER_SENSITIVITY * Constants.GRAVITATIONAL_ACCELERATION);
            Gdx.app.log("ACCELVERT", "" + accelerometerInputVertical);
            accelerometerInputVertical += Constants.VERTICAL_ACCELEROMETER_ADJUSTMENT;
            mPosition.y += -delta * accelerometerInputVertical * Constants.PLAYER_MOVEMENT_SPEED;
        }

//        // Movement with acceleration, deceleration, and drag
//        if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
//            mVelocity.x -= delta * Constants.PLAYER_ACCELERATION;
//        }
//        if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
//            mVelocity.x += delta * Constants.PLAYER_ACCELERATION;
//        }
//        // Flying!
//        if (Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W)) {
//            mVelocity.y += delta * 3;
//        }
//
//        if (Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)) {
//            mVelocity.y -= delta * 3;
//        }
//        mVelocity.clamp(0, Constants.PLAYER_MAX_SPEED);
//
//        mVelocity.x -= delta * Constants.DRAG * mVelocity.x;
//        mVelocity.y -= delta * Constants.DRAG * mVelocity.y;
//
//        mPosition.x += delta * mVelocity.x;
//        mPosition.y += delta * mVelocity.y;

        // Collision detection
        collideWithBounds(mViewport.getWorldWidth(), mViewport.getWorldHeight());
    }

    private void collideWithBounds(float viewportWidth, float viewportHeight) {
        if (mPosition.x - Constants.PLAYER_SHIP_RADIUS < 0) {
            mPosition.x = Constants.PLAYER_SHIP_RADIUS;
//            mVelocity.x = -mVelocity.y;
        }
        if (mPosition.x + Constants.PLAYER_SHIP_RADIUS > viewportWidth) {
            mPosition.x = viewportWidth - Constants.PLAYER_SHIP_RADIUS;
//            mVelocity.x = -mVelocity.y;
        }
        if (mPosition.y - Constants.PLAYER_SHIP_RADIUS < 0) {
            mPosition.y = Constants.PLAYER_SHIP_RADIUS;
//            mVelocity.x = -mVelocity.y;
        }
        if (mPosition.y + Constants.PLAYER_SHIP_RADIUS > viewportHeight) {
            mPosition.y = viewportHeight - Constants.PLAYER_SHIP_RADIUS;
//            mVelocity.x = -mVelocity.y;
        }
    }

    public boolean collideWithTrike(Trikes trikes) {
        boolean isHit = false;

        for (Trike trike : trikes.mTrikesArray) {
            if (trike.mPosition.dst2(mPosition) < Constants.PLAYER_SHIP_RADIUS) {
                isHit = true;
                mDeaths++;
            }
            // The dst2 method returns the squared distance between the two vectors
            // If this is ever less than the player's head radius, then the trike point is inside the player's head
            // The method below draws a square hit box around the circular head. Not ideal.
//            if (trike.mPosition.x >= mPosition.x - Constants.PLAYER_SHIP_RADIUS &&
//                    trike.mPosition.x <= mPosition.x + Constants.PLAYER_SHIP_RADIUS &&
//                    trike.mPosition.y >= mPosition.y - Constants.PLAYER_SHIP_RADIUS &&
//                    trike.mPosition.y <= mPosition.y + Constants.PLAYER_SHIP_RADIUS) {
//                isHit = true;
//            }
        }
        return isHit;
    }

    Random random = new Random();

    public void render(ShapeRenderer renderer) {

        renderer.set(ShapeRenderer.ShapeType.Filled);

        if(random.nextInt(10) < 1) {
            renderer.setColor(Color.PINK);
        } else {
            renderer.setColor(Color.BLACK);
        }

        //renderer.setColor(Color.GREEN);
        //renderer.ellipse(mPosition.x - .35f, mPosition.y - .5f, .7f, 1f, 40);

        renderer.circle(mPosition.x, mPosition.y, Constants.PLAYER_SHIP_RADIUS * 1.1f, Constants.PLAYER_SHIP_SEGMENTS);

        if(random.nextInt(5) < 1) {
            renderer.setColor(Color.PURPLE);
        } else {
            renderer.setColor(Color.BLACK);
        }
        renderer.circle(mPosition.x, mPosition.y, Constants.PLAYER_SHIP_RADIUS, Constants.PLAYER_SHIP_SEGMENTS);
        renderer.setColor(Color.GREEN);
        renderer.circle(mPosition.x, mPosition.y, Constants.PLAYER_SHIP_RADIUS / 1.5f, Constants.PLAYER_SHIP_SEGMENTS);
    }
}
