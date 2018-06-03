package com.islandboys.game.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.islandboys.game.view.PlayScreen;


/**
 *  Enemy Class in game
 */
public  abstract class Enemy extends Sprite {



    public enum  State{IDLE,RUNNING,ATTACK,DEAD}
    protected World world;
    protected PlayScreen screen;


    protected State state;
    protected boolean destroy=false;
    protected  boolean directionRigh=true;



    protected Body enemyBody;
    protected  float heigth,width;

    /**
     * Enemy Constructor
     * @param screen
     * @param x_position
     * @param y_position
     */
    public Enemy(PlayScreen screen,float x_position,float y_position){
        this.screen=screen;
        this.world=screen.getWorld();


        defineEnemyBody(x_position,y_position);

    }

    /**
     * Get Enemy Body
     * @return enemyBody
     */
    public Body getEnemyBody() {
        return enemyBody;
    }

    /**
     * Get Enemy Height
     * @return height
     */
    public  float getH(){
        return heigth;
    }

    /**
     * Get Enemy Width
     * @return width
     */
    public float getW(){
        return width;
    }


    /**
     * Get Enemy State
     * @return state
     */
    public State getState() {
        return state;
    }


    /**
     * Set Enemy State
     * @param  state
     */
    public void setState(State state) {
        this.state = state;
    }



    /**
     * Abstract method to define the characteristics the enemy-s body
     * @param x_position
     * @param y_position
     */
    protected  abstract void  defineEnemyBody(float x_position,float y_position);

    /**
     * Update the enemy state
     * @param delta
     */
    public abstract void update(float delta);



}
