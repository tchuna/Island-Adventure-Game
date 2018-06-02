package com.islandboys.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.islandboys.game.view.MenuScreen;
import com.islandboys.game.view.PlayScreen;
import com.islandboys.game.view.ScoreScreen;

public class MGame extends Game {

	public static SpriteBatch batch;
	public MenuScreen menu ;
	public PlayScreen playScreen;
	public ScoreScreen scoreScreen;



    private int currentLevel=1;

	public static AssetManager assetManager;
	private boolean sontOn;


	@Override
	public void create () {
		batch = new SpriteBatch();

		this.menu=new MenuScreen(this);

		//this.scoreScreen=new ScoreScreen(this);
		//this.settingsScreen=new SettingsScreen(this);

		assetManager=new AssetManager();
		assetManager.load("coin.wav", Sound.class);
		assetManager.load("hurt.wav",Sound.class);
		assetManager.load("attack.wav",Sound.class);
        assetManager.load("attack_O.ogg",Sound.class);
		assetManager.load("attack_D.mp3",Sound.class);
		assetManager.load("orc_.mp3",Sound.class);
		assetManager.load("lev1.mp3",Music.class);
		assetManager.load("lev2.mp3",Music.class);
		assetManager.load("lev3.mp3",Music.class);
		assetManager.load("sk.mp3",Sound.class);

  		assetManager.finishLoading();
		this.sontOn=true;

		setScreen(menu);


	}


    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
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

	public void changeScreen(Screen screen) {
		setScreen(screen);
	}

	public void setCurentLevel(int curentLevel) {
		this.currentLevel = curentLevel;
	}

	public void setScoreMenu(){
		setScreen(scoreScreen);
	}
	public void setPlayScreen(){
		this.playScreen=new PlayScreen(this,currentLevel);
		setScreen(playScreen);
	}
}
