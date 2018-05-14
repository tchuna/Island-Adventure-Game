package com.islandboys.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.islandboys.game.MGame;



public class ScoreScreen implements  Screen {

    private Texture scoreBackground;
    private Viewport  gamePort;
    private Stage scoreStage;
    private MGame game;

    public ScoreScreen(MGame game){
        this.game=game;

        this.scoreBackground=new Texture("test2.png");
        gamePort=new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        gamePort.apply();
        scoreStage=new Stage(gamePort,game.batch);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(scoreStage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(scoreBackground,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
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
