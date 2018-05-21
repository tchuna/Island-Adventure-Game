package com.islandboys.game.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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

    private float timeCount=0;
    private static int score=0;
    private int live=0;
    private int weapon=5;
    private int worldTime=0;


    private static Label scoreLabel;
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
        camera.position.set(gamePort.getScreenWidth()/2,gamePort.getScreenHeight()/2,0);

    }




    public void update(float delta){
        timeCount+=delta;
        if(timeCount>=1){
            worldTime++;
            timeLabel.setText(String.format("%03d",worldTime));

            timeCount=0;
        }


    }


    public void setupHudLabels(){

        scoreLabel=new Label(String.format("%03d",score),new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        scoreLabel.setBounds(Gdx.graphics.getWidth()*375/1000,Gdx.graphics.getHeight()*838/1000,Gdx.graphics.getWidth()*125/1000,Gdx.graphics.getHeight()*209/1000);
        scoreLabel.setFontScale(2f);


        weaponLabel=new Label(String.format("%02d",weapon),new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        weaponLabel.setFontScale(2f);
        weaponLabel.setBounds(Gdx.graphics.getWidth()*625/1000,Gdx.graphics.getHeight()*838/1000,Gdx.graphics.getWidth()*125/1000,Gdx.graphics.getHeight()*209/1000);


        timeLabel=new Label(String.format("%03d",worldTime),new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        timeLabel.setFontScale(2f);
        timeLabel.setBounds(Gdx.graphics.getWidth()*829/1000,Gdx.graphics.getHeight()*838/1000,Gdx.graphics.getWidth()*125/1000,Gdx.graphics.getHeight()*209/1000);


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
    public static void  setScore(int v){

        score+=v;
        scoreLabel.setText(String.format("%03d",score));
    }

    public void numWeapon(int weapon){
        this.weapon=weapon;


    }

    public void selectIs(int islander){
        switch (islander){
            case GameInfo.ISLANDER_1:this.islander_T=new Texture("isl_1.png");break;
            case GameInfo.ISLANDER_2:this.islander_T=new Texture("isl_2.png");break;
            case GameInfo.ISLANDER_3:this.islander_T=new Texture("isl_3.png");break;

        }
    }







    @Override

    public void draw(){
        super.draw();
        batch.begin();
        batch.draw(liveLevel.get(live),Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*95/100), Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*13.6f/100),Gdx.graphics.getWidth()*25/100,Gdx.graphics.getHeight()*13.6f/100);
        batch.draw(islander_T,Gdx.graphics.getWidth()*7/1000, Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*105/1000),Gdx.graphics.getWidth()*56/1000,Gdx.graphics.getHeight()*94/1000);
        batch.draw(scoreCoin_T,Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*688/1000), Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*11/100),Gdx.graphics.getWidth()*52/1000,Gdx.graphics.getHeight()*94/1000);
        batch.draw(weapon_T,Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*438/1000), Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*1125/10000),Gdx.graphics.getWidth()*525/10000,Gdx.graphics.getHeight()*104/1000);
        batch.draw(time_T,Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*240/1000), Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*121/1000),Gdx.graphics.getWidth()*69/1000,Gdx.graphics.getHeight()*125/1000);
        batch.end();

    }





}
