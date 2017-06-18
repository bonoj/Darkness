package com.hk47.darkness;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Constants {

    public static final float WORLD_SIZE = 10.0f;
    public static final Color BACKGROUND_COLOR = Color.BLACK;

    public static final float ACCELEROMETER_SENSITIVITY = 0.5f;
    public static final float GRAVITATIONAL_ACCELERATION = 9.8f;
    public static final float VERTICAL_ACCELEROMETER_ADJUSTMENT = 1.2f;

    public static final float PLAYER_SHIP_RADIUS = 0.3f;
    public static final float PLAYER_SHIP_HEIGHT = 2.0f * PLAYER_SHIP_RADIUS;
    public static final int PLAYER_SHIP_SEGMENTS = 20;
    public static final float PLAYER_MOVEMENT_SPEED = 10.0f;
    // For movement with acceleration and deceleration
    public static final float PLAYER_ACCELERATION = 10.0f;
    public static final float PLAYER_MAX_SPEED = 10.0f;
    public static final float DRAG = 1.0f;

    public static final float TRIKE_HEIGHT = 1.0f;
    public static final float TRIKE_WIDTH = 0.5f;
    public static final Vector2 TRIKE_ACCELERATION = new Vector2(0, -5.0f);

    public static final float HUD_FONT_REFERENCE_SCREEN_SIZE = 480.0f;
    public static final float HUD_MARGIN = 20.0f;

    public static final String EASY_LABEL = "Dark";
    public static final String MEDIUM_LABEL = "Darker";
    public static final String HARD_LABEL = "Darkest";

    public static final float EASY_SPAWNS_PER_SECOND = 5;
    public static final float MEDIUM_SPAWNS_PER_SECOND = 15;
    public static final float HARD_SPAWNS_PER_SECOND = 25;

    public static final Color EASY_COLOR = new Color(0.2f, 0.2f, 1, 1);
    public static final Color MEDIUM_COLOR = new Color(0.5f, 0.5f, 1, 1);
    public static final Color HARD_COLOR = new Color(0.7f, 0.7f, 1, 1);

    public static final float DIFFICULTY_SCREEN_WORLD_SIZE = 480.0f;
    public static final float DIFFICULTY_BUBBLE_RADIUS = DIFFICULTY_SCREEN_WORLD_SIZE / 9;
    public static final float DIFFICULTY_LABEL_SCALE = 1.5f;
    public static final int DIFFICULT_LABEL_CIRCLE_SEGMENTS = 50;

    public static final Vector2 EASY_CENTER = new Vector2(DIFFICULTY_SCREEN_WORLD_SIZE / 4, DIFFICULTY_SCREEN_WORLD_SIZE / 2);
    public static final Vector2 MEDIUM_CENTER = new Vector2(DIFFICULTY_SCREEN_WORLD_SIZE / 2, DIFFICULTY_SCREEN_WORLD_SIZE / 2);
    public static final Vector2 HARD_CENTER = new Vector2(DIFFICULTY_SCREEN_WORLD_SIZE * 3 / 4, DIFFICULTY_SCREEN_WORLD_SIZE / 2);

    public enum Difficulty {

        EASY(EASY_SPAWNS_PER_SECOND, EASY_LABEL),
        MEDIUM(MEDIUM_SPAWNS_PER_SECOND, MEDIUM_LABEL),
        HARD(HARD_SPAWNS_PER_SECOND, HARD_LABEL);

        float mSpawnRate;
        String mLabel;

        Difficulty(float spawnRate, String label) {

            mSpawnRate = spawnRate;
            mLabel = label;
        }

    }
}
