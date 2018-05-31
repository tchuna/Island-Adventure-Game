package com.islandboys.game.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.islandboys.game.view.PlayScreen;

public  abstract class Enemy extends Sprite {



    public enum  State{IDLE,RUNNING,ATTACK,DEAD}
    //public Vector2 velocity;

    protected World world;
    protected PlayScreen screen;


    protected State state;
    protected boolean destroy=false;
    protected float stateTime;
    protected  boolean directionRigh=true;



    protected Body enemyBody;
    protected  float heigth,width;

    public Enemy(PlayScreen screen,float x_position,float y_position){
        this.screen=screen;
        this.world=screen.getWorld();


        defineEnemyBody(x_position,y_position);

    }

    public Body getEnemyBody() {
        return enemyBody;
    }

    public  float getH(){
        return heigth;
    }
    public float getW(){
        return width;
    }


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }



    protected  abstract void  defineEnemyBody(float x_position,float y_position);
    public abstract void update(float delta);



}
