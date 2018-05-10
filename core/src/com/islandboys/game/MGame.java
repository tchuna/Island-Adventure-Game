package com.islandboys.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.islandboys.game.view.MenuScreen;
import com.islandboys.game.view.PlayScreen;
import com.islandboys.game.view.ScoreScreen;
import com.islandboys.game.view.SettingsScreen;
import com.uwsoft.editor.renderer.SceneLoader;

import java.awt.Menu;

public class MGame extends Game {

	public SpriteBatch batch;
	private MenuScreen menu ;

	private PlayScreen playScreen;
	private ScoreScreen scoreScreen;
	private SettingsScreen settingsScreen;


	@Override
	public void create () {

		this.menu=new MenuScreen(this);
		this.scoreScreen=new ScoreScreen(this);

		batch = new SpriteBatch();
		setScreen(scoreScreen);

	}

	@Override
	public void render () {
		super.render();

	}

	@Override
	public void resize(int width,int height){

	}

}
