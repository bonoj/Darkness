package com.hk47.darkness;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class DarknessScreen extends InputAdapter implements Screen {

    public static final String TAG = DarknessScreen.class.getName();

    DarknessGame mGame;
    Constants.Difficulty mDifficulty;
    ExtendViewport mDarknessViewport;
    ShapeRenderer mRenderer;
    ScreenViewport mHudViewport;
    SpriteBatch mBatch;
    BitmapFont mFont;
    Player mPlayer;
    Trikes mTrikes;
    int mTopScore;

    Stars mStars;

    public DarknessScreen(DarknessGame game, Constants.Difficulty difficulty) {
        mGame = game;
        mDifficulty = difficulty;
    }

    @Override
    public void show() {

        mDarknessViewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);

        mRenderer = new ShapeRenderer();
        mRenderer.setAutoShapeType(true);

        mHudViewport = new ScreenViewport();
        mBatch = new SpriteBatch();
        mFont = new BitmapFont();
        mFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        mPlayer = new Player(mDarknessViewport);
        mTrikes = new Trikes(mDarknessViewport, mDifficulty);

        mStars = new Stars(mDarknessViewport);

        Gdx.input.setInputProcessor(this);

        mTopScore = 0;
    }

    @Override
    public void resize(int width, int height) {
        mDarknessViewport.update(width, height, true);

        mHudViewport.update(width, height, true);
        mFont.getData().setScale(Math.min(width, height) / Constants.HUD_FONT_REFERENCE_SCREEN_SIZE);

        mPlayer.init();
        mTrikes.init();
        mStars.init();
    }

    @Override
    public void dispose() {
        mRenderer.dispose();
        mBatch.dispose();
        mFont.dispose();
    }


    @Override
    public void render(float delta) {

        mStars.update(delta);

        mTrikes.update(delta);
        mPlayer.update(delta);

        if (mPlayer.collideWithTrike(mTrikes)) {
            mTrikes.init();
        }

        mDarknessViewport.apply(true);
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mRenderer.setProjectionMatrix(mDarknessViewport.getCamera().combined);
        mRenderer.begin(ShapeRenderer.ShapeType.Filled);

        mStars.render(mRenderer);
        mPlayer.render(mRenderer);
        mTrikes.render(mRenderer);
        mRenderer.end();

        mTopScore = Math.max(mTopScore, mTrikes.mTrikesDodged);
        mHudViewport.apply();

        mBatch.setProjectionMatrix(mHudViewport.getCamera().combined);
        mBatch.begin();
        mFont.draw(mBatch, "Deaths: " + mPlayer.mDeaths,
                Constants.HUD_MARGIN, mHudViewport.getWorldHeight() - Constants.HUD_MARGIN);
        mFont.draw(mBatch, "Score: " + mTrikes.mTrikesDodged + "\nTop Score: " + mTopScore,
                mHudViewport.getWorldWidth() - Constants.HUD_MARGIN, mHudViewport.getWorldHeight() - Constants.HUD_MARGIN,
                0, Align.right, false);
        mBatch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        mRenderer.dispose();
    }


    @Override

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        mGame.showDifficultyScreen();
        return true;

    }
}