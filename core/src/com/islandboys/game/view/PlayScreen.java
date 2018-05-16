package com.islandboys.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.islandboys.game.MGame;
import com.islandboys.game.model.Hud;

public class PlayScreen implements Screen {



    private Texture settingsBackground;
    private Viewport gamePort;
    private Stage playStage;
    private OrthographicCamera gameCamera;
    private MGame game;
    private Hud hud;

    public PlayScreen(MGame game){
        this.game=game;

        this.settingsBackground=new Texture("test1.png");
        gameCamera=new OrthographicCamera();
        gamePort=new FitViewport(MGame.V_WIDTH,MGame. V_HEIGTH,gameCamera);
        gamePort.apply();
        playStage=new Stage(gamePort,game.batch);
        hud=new Hud(game.batch,MGame.ISLANDER_3);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(playStage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.end();

        hud.draw();
        //game.batch.setProjectionMatrix(hud.hudStage.getCamera().combined);
        hud.hudStage.draw();



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
