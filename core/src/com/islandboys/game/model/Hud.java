package com.islandboys.game.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.islandboys.game.MGame;

public class Hud  {

    public Stage hudStage;
    private Viewport gamePort;
    private OrthographicCamera camera;
    private Table table;

    private Integer worldTimer;
    private float timeCount;
    private int score;



    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label islanderBoyLabel;
    Label worldLabel;

    public Hud(SpriteBatch batch){
        worldTimer=300;
        timeCount=0;
        score=0;
        camera=new OrthographicCamera();
        gamePort=new FitViewport(MGame.V_WIDTH,MGame. V_HEIGTH,camera);
        hudStage=new Stage(gamePort,batch);
        table=new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel=new Label(String.format("%03d",worldTimer),new Label.LabelStyle(new BitmapFont(), Color.CYAN));
        scoreLabel=new Label(String.format("%06d",score),new Label.LabelStyle(new BitmapFont(), Color.CYAN));
        timeLabel=new Label("TIME",new Label.LabelStyle(new BitmapFont(), Color.CYAN));
        levelLabel=new Label("1-1",new Label.LabelStyle(new BitmapFont(), Color.CYAN));
        islanderBoyLabel=new Label("Islander",new Label.LabelStyle(new BitmapFont(), Color.CYAN));
        worldLabel=new Label("world",new Label.LabelStyle(new BitmapFont(), Color.CYAN));


        table.add(islanderBoyLabel).expandX().padTop(5);
        table.add(worldLabel).expandX().padTop(5);
        table.add(timeLabel).expandX().padTop(5);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();

        hudStage.addActor(table);






    }
}
