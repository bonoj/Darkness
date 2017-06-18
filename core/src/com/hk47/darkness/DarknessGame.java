package com.hk47.darkness;

import com.badlogic.gdx.Game;

public class DarknessGame extends Game {

	@Override
	public void create() {
		// Uncomment showDifficultyScreen() and comment out showDarknessScreen()
		// to reactivate Difficulty Settings on launch

		//showDifficultyScreen();
		showDarknessScreen(Constants.Difficulty.EASY);
	}

	public void showDifficultyScreen() {

		setScreen(new DifficultyScreen(this));
	}



	public void showDarknessScreen(Constants.Difficulty difficulty) {

		setScreen(new DarknessScreen(this, difficulty));
	}
}
