package com.hk47.darkness;

import com.badlogic.gdx.Game;

public class DarknessGame extends Game {

	@Override
	public void create() {
		// Uncomment showDifficultyScreen() and comment out showDarknessScreen() to reactive difficulty settings.
		//showDifficultyScreen();
		showDarknessScreen(Constants.Difficulty.EASY);
	}

	public void showDifficultyScreen() {

		// TODO: Show the difficulty screen

		setScreen(new DifficultyScreen(this));

	}



	public void showDarknessScreen(Constants.Difficulty difficulty) {

		// TODO: Show the Trikes screen with the appropriate difficulty

		setScreen(new DarknessScreen(this, difficulty));

	}
}
