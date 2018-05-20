package com.islandboys.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.islandboys.game.MGame;
import com.islandboys.game.model.GameInfo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Adventures In Sal Island";
		config.width = GameInfo.WIDTH;
		config.height = GameInfo.HEIGHT;
		//config.resizable = false;


		new LwjglApplication(new MGame(), config);
	}
}
