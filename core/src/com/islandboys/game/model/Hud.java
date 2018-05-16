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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.islandboys.game.MGame;

public class Hud extends Stage {

    public Stage hudStage;
    private Viewport gamePort;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Image pauseButton;
    private Array<Texture>liveLevel;
    private Texture scoreCoin_T,weapon_T,time_T,islander_T;

    private int timeCount=0;
    private int score=0;
    private int live=4;
    private int weapon=5;


    Label scoreLabel;
    Label timeLabel;
    Label weaponLabel;

    public Hud(SpriteBatch batch,int islander){
        camera=new OrthographicCamera() ;
        this.batch=batch;
        gamePort=new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),camera);
        hudStage=new Stage(gamePort,batch);
        scoreCoin_T=new Texture("scoreCoin.png");
        weapon_T=new Texture("weapon.png");
        time_T=new Texture("time.png");

        setupHudLabels();
        selectIs(islander);
        loadLiveLevel();

    }




    public void setupHudLabels(){

        scoreLabel=new Label(String.format("%03d",score),new Label.LabelStyle(new BitmapFont(), Color.CYAN));
        scoreLabel.setFontScale(1.8f);
        scoreLabel.setAlignment(Align.topLeft);
        scoreLabel.setBounds(300,370,100,100);


        weaponLabel=new Label(String.format("%02d",weapon),new Label.LabelStyle(new BitmapFont(), Color.CYAN));
        weaponLabel.setFontScale(1.8f);
        weaponLabel.setBounds(500,402,100,100);


        timeLabel=new Label(String.format("%03d",timeCount),new Label.LabelStyle(new BitmapFont(), Color.CYAN));
        timeLabel.setFontScale(1.8f);
        timeLabel.setBounds(705,402,100,100);


        hudStage.addActor(scoreLabel);
        hudStage.addActor(weaponLabel);
        hudStage.addActor(timeLabel);


    }


    public void loadLiveLevel(){
        liveLevel=new Array<Texture>();
        liveLevel.add(new Texture("liveLevel_4.png"));
        liveLevel.add(new Texture("liveLevel_3.png"));
        liveLevel.add(new Texture("liveLevel_2.png"));
        liveLevel.add(new Texture("liveLevel_1.png"));
        liveLevel.add(new Texture("liveLevel_0.png"));

    }

    public void setLiveLevel(int live){
        this.live=live;

    }
    public void setScore(int score){
        this.score=score;
    }
    public void numWeapon(int weapon){
        this.weapon=weapon;


    }

    public void selectIs(int islander){
        switch (islander){
            case MGame.ISLANDER_1:this.islander_T=new Texture("isl_1.png");break;
            case MGame.ISLANDER_2:this.islander_T=new Texture("isl_2.png");break;
            case MGame.ISLANDER_3:this.islander_T=new Texture("isl_3.png");break;

        }
    }



    @Override

    public void draw(){
        super.draw();
        batch.begin();
        batch.draw(liveLevel.get(live),Gdx.graphics.getWidth()-755, Gdx.graphics.getHeight()- 65,200,65);
        batch.draw(islander_T,5, Gdx.graphics.getHeight()-50,45,45);
        batch.draw(scoreCoin_T,Gdx.graphics.getWidth()-550, Gdx.graphics.getHeight()-52,42,45);
        batch.draw(weapon_T,Gdx.graphics.getWidth()-350, Gdx.graphics.getHeight()-54,42,50);
        batch.draw(time_T,Gdx.graphics.getWidth()-150, Gdx.graphics.getHeight()-58,55,60);
        batch.end();
        
    }

}
