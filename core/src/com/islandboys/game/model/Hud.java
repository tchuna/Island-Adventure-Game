package com.islandboys.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.islandboys.game.MGame;

public class Hud extends Stage {

    public Stage hudStage;
    private Viewport gamePort;
    private OrthographicCamera camera;
    private Table table;
    private SpriteBatch batch;


    private Image scoreFruts;
    private Image pauseButton;
    private Array<Texture>liveLevel;


    private Integer worldTimer=300;
    private float timeCount=0;
    private int score=0;
    private int live=4;


    Label scoreLabel;
    Label timeLabel;

    public Hud(SpriteBatch batch){
        camera=new OrthographicCamera();
        gamePort=new FitViewport(MGame.V_WIDTH,MGame. V_HEIGTH,camera);
        hudStage=new Stage(gamePort,batch);
        table=new Table();
        this.batch=batch;

        loadLiveLevel();
        setupHud();

    }



    public void setupHud(){
        table.top();
        table.setFillParent(true);
        scoreLabel=new Label(String.format("%03d",score),new Label.LabelStyle(new BitmapFont(), Color.CYAN));
        timeLabel=new Label("TIME",new Label.LabelStyle(new BitmapFont(), Color.CYAN));
        table.add(scoreLabel).expandX().padTop(5);
        table.add(timeLabel).expandX().padTop(5);
        table.row();

        hudStage.addActor(table);


    }


    public void loadLiveLevel(){
        liveLevel=new Array<Texture>();
        liveLevel.add(new Texture("liveLevel_4.png"));
        liveLevel.add(new Texture("liveLevel_3.png"));
        liveLevel.add(new Texture("liveLevel_2.png"));
        liveLevel.add(new Texture("liveLevel_1.png"));
        liveLevel.add(new Texture("liveLevel_0.png"));

    }



    @Override

    public void draw(){
        super.draw();
        batch.begin();

        batch.draw(liveLevel.get(4),0, Gdx.graphics.getHeight()- 150,233,100);
        batch.end();


    }

}
