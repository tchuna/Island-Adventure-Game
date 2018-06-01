package com.islandboys.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.islandboys.game.view.MenuScreen;
import com.islandboys.game.view.PlayScreen;
import com.islandboys.game.view.ScoreScreen;
import com.islandboys.game.view.SelectScreen;
import com.islandboys.game.view.SettingsScreen;
import com.uwsoft.editor.renderer.SceneLoader;

import java.awt.Menu;

public class MGame extends Game {

	public static SpriteBatch batch;
	private MenuScreen menu ;

	private PlayScreen playScreen;
	private ScoreScreen scoreScreen;
	private SettingsScreen settingsScreen;
	private SelectScreen selectScreen;


    private int currentLevel=2;

	public static AssetManager assetManager;
	private boolean sontOn;


	@Override
	public void create () {
		batch = new SpriteBatch();

		//this.menu=new MenuScreen(this);
		//this.scoreScreen=new ScoreScreen(this);
		//this.settingsScreen=new SettingsScreen(this);
		//this.selectScreen=new SelectScreen(this);

		assetManager=new AssetManager();
		assetManager.load("song.wav", Music.class);
		assetManager.load("coin.wav", Sound.class);
		assetManager.load("hurt.wav",Sound.class);
		assetManager.load("attack.wav",Sound.class);
        assetManager.load("attack_O.ogg",Sound.class);
		assetManager.load("attack_D.mp3",Sound.class);
		assetManager.load("orc_.mp3",Sound.class);

  		assetManager.finishLoading();
		this.sontOn=true;

		this.playScreen=new PlayScreen(this,currentLevel);


		setScreen(playScreen);

	}




    public void setToPlayScreen(){
        playScreen = new PlayScreen(this, currentLevel);
        setScreen(playScreen);
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }



	public void changeScreen(int option){

		switch (option){
			case 1 : setScreen(selectScreen);break;
			case 2 : setScreen(scoreScreen);break;
			case 3 : setScreen(settingsScreen);break;
			case 4 : setScreen(menu);break;
			case 5 : setScreen(playScreen);break;
			case 6 :System.out.println("thunaaa");
		}


	}

	@Override
	public void render () {
		super.render();

	}

	@Override
	public void resize(int width,int height){

	}

	@Override
	public void dispose(){

		super.dispose();
		batch.dispose();
		assetManager.dispose();

	}

}
