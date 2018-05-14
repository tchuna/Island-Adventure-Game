package com.islandboys.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.islandboys.game.MGame;

public class SettingsScreen implements Screen {


    private Texture settinBackground;
    private Viewport gamePort;
    private Stage settinStage;
    private MGame game;

    public SettingsScreen(MGame game){
        this.game=game;
        this.settinBackground=new Texture("test3.png");
        gamePort=new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        gamePort.apply();
        settinStage=new Stage(gamePort,game.batch);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(settinStage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(settinBackground,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
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
