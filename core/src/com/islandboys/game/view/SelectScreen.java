package com.islandboys.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.islandboys.game.MGame;

public class SelectScreen implements Screen{

    private Texture selectBackground;
    private Viewport gamePort;
    private Stage selectStage;
    private MGame game;

    private Image playB;
    private Image exitB;
    private Image scoreB;
    private Image settingsB;

    private float xPositionB;
    private float yPositionB;
    private float hPositionB;
    private float wPositionB;

    public SelectScreen(MGame game){
        this.game=game;
        this.selectBackground=new Texture("mira.png");
        gamePort=new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        gamePort.apply();
        selectStage=new Stage(gamePort,game.batch);

        setupButtons();
        inputListener();


    }



    public void setupButtons(){

        hPositionB=Gdx.graphics.getHeight()*32/100;
        wPositionB=Gdx.graphics.getWidth()*19/100;
        xPositionB=Gdx.graphics.getWidth()*20/100;
        yPositionB=Gdx.graphics.getHeight()*4/100;

        playB=new Image(selectBackground);
        playB.setHeight(hPositionB);
        playB.setWidth(wPositionB);
        playB.setPosition(xPositionB,yPositionB);

        xPositionB=Gdx.graphics.getWidth()*43/100;


        scoreB=new Image(selectBackground);
        scoreB.setHeight(hPositionB);
        scoreB.setWidth(wPositionB);
        scoreB.setPosition(xPositionB,yPositionB);

        xPositionB=Gdx.graphics.getWidth()*66/100;

        settingsB=new Image(selectBackground);
        settingsB.setHeight(hPositionB);
        settingsB.setWidth(wPositionB);
        settingsB.setPosition(xPositionB,yPositionB);


        hPositionB=Gdx.graphics.getHeight()*11/100;
        wPositionB=Gdx.graphics.getWidth()*7/100;
        xPositionB=Gdx.graphics.getWidth()*93/100;
        yPositionB=Gdx.graphics.getHeight()*89/100;

        exitB=new Image(selectBackground);
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
        game.changeScreen(5);

    }

    public void setMainScreen(){
        game.changeScreen(4);
    }


    public void inputListener(){

        Gdx.input.setInputProcessor(selectStage);
        selectStage.addActor(playB);
        selectStage.addActor(scoreB);
        selectStage.addActor(settingsB);
        selectStage.addActor(exitB);


        playB.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                setPlayScreen();

            }
        });

        scoreB.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                setMainScreen();

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
        Gdx.input.setInputProcessor(selectStage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(selectBackground,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
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
