package com.islandboys.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.islandboys.game.MGame;

import java.awt.Image;

public class MenuScreen implements Screen {

    private MGame game;
    private Viewport gamePort;
    private Texture menuBackground;

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

        this.menuBackground=new Texture("menu.png");


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
