package com.islandboys.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.islandboys.game.MGame;



public class MenuScreen implements Screen {

    private MGame game;
    private Viewport gamePort;
    private Texture menuBackground;
    private Stage menuStage;

    private Image playB;
    private Image exitB;
    private Image scoreB;
    private Image settingsB;

    private float xPositionB;
    private float yPositionB;
    private float hPositionB;
    private float wPositionB;





    public MenuScreen(MGame game){
        this.game=game;

        gamePort=new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        gamePort.apply();

        menuStage=new Stage(gamePort,game.batch);
        menuBackground=new Texture("menu.png");


        setupButtons();
        inputListener();

    }


    public void setupButtons(){

        hPositionB=Gdx.graphics.getHeight()*32/100;
        wPositionB=Gdx.graphics.getWidth()*19/100;
        xPositionB=Gdx.graphics.getWidth()*20/100;
        yPositionB=Gdx.graphics.getHeight()*4/100;

        playB=new Image(menuBackground);
        playB.setHeight(hPositionB);
        playB.setWidth(wPositionB);
        playB.setPosition(xPositionB,yPositionB);

        xPositionB=Gdx.graphics.getWidth()*43/100;


        scoreB=new Image(menuBackground);
        scoreB.setHeight(hPositionB);
        scoreB.setWidth(wPositionB);
        scoreB.setPosition(xPositionB,yPositionB);

        xPositionB=Gdx.graphics.getWidth()*66/100;

        settingsB=new Image(menuBackground);
        settingsB.setHeight(hPositionB);
        settingsB.setWidth(wPositionB);
        settingsB.setPosition(xPositionB,yPositionB);


        hPositionB=Gdx.graphics.getHeight()*11/100;
        wPositionB=Gdx.graphics.getWidth()*7/100;
        xPositionB=Gdx.graphics.getWidth()*93/100;
        yPositionB=Gdx.graphics.getHeight()*89/100;

        exitB=new Image(menuBackground);
        exitB.setHeight(hPositionB);
        exitB.setWidth(wPositionB);
        exitB.setPosition(xPositionB,yPositionB);




    }


    public void setSettingsScreen(){
        game.changeScreen(3);

    }


    public void setScoreScreen(){
        game.changeScreen(2);

    }


    public void setPlayScreen(){
        game.changeScreen(1);

    }



    public void inputListener(){

        Gdx.input.setInputProcessor(menuStage);
        menuStage.addActor(playB);
        menuStage.addActor(scoreB);
        menuStage.addActor(settingsB);
        menuStage.addActor(exitB);


        playB.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                setPlayScreen();

            }
        });

        scoreB.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                setScoreScreen();

            }
        });


        settingsB.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                setSettingsScreen();

            }
        });

        exitB.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();

            }
        });


    }






    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(menuBackground,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch.end();


    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
