package com.islandboys.game.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import com.islandboys.game.view.PlayScreen;

/**
 *  Class Arrow  in  the Game
 *
 */
public class Arrow extends Sprite {

    private float stateTime;
    private Texture arrow;
    private PlayScreen screen;
    private World world;
    private boolean destroy;
    private boolean fireRight;
    private float x,y;
    private Sprite sprite;
    private float endSprite;
    private float h,w;



    /**
     * Arrow Constructor
     * @param screen
     * @param x_position
     * @param y_position
     * @param isRigth
     */
    public Arrow(PlayScreen screen, float x_position, float y_position,boolean isRigth) {

        this.stateTime=0;
        this.screen=screen;
        this.x=x_position;
        this.y=y_position;
        this.fireRight=isRigth;
        arrow=new Texture("atc.png");
        sprite=new Sprite(arrow);
        endSprite=x+3;
        destroy=false;
        this.w=35/GameInfo.PIXEL_METER;
        this.h=28/GameInfo.PIXEL_METER;


    }



    /**
     * flip Image Sprite
     *
     */
    public void flipSprite(){
        if(fireRight && sprite.isFlipX()){
            sprite.flip(true,false);
        }else if(fireRight==false  && !sprite.isFlipX()){

            sprite.flip(true,false);

        }
    }


    /**
     * Get  the destroy variable
     * @return destroy
     */
    public boolean getDestroy() {
        return destroy;
    }



    /**
     * Update the Arrow state
     * @param  delta
     */
    public void update(float delta){
        if(x>endSprite){
            destroy=true;
        }
       flipSprite();
        if(fireRight){
            x+=GameInfo.ARROW_SPEED;
        }else if(fireRight==false ){
            x-=GameInfo.ARROW_SPEED;

        }

    }


    /**
     * Render the Arrow sprite image
     * @param batch
     */
    public void render(SpriteBatch batch){
        batch.draw(sprite,x,y,w,h);
    }



    /**
     * Check if the arrow reached the enemy
     * @param  enemy
     */
    public boolean contacEnemy(Enemy enemy){
        if(destroy==false && enemy.getState()!= Enemy.State.DEAD){
            if(x<enemy.getEnemyBody().getPosition().x+enemy.getW() && y<enemy.getEnemyBody().getPosition().y+enemy.getH()
                    && x+w >enemy.getEnemyBody().getPosition().x
                    && y+h>enemy.getEnemyBody().getPosition().y){
                destroy=true;
                return  true;
            }
        }

        return false;
    }
}