package com.islandboys.game.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import com.islandboys.game.view.PlayScreen;

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


    public void flipSprite(){
        if(fireRight && sprite.isFlipX()){
            sprite.flip(true,false);
        }else if(fireRight==false  && !sprite.isFlipX()){

            sprite.flip(true,false);

        }
    }


    public boolean getDestroy() {
        return destroy;
    }

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

    public void render(SpriteBatch batch){
        batch.draw(sprite,x,y,w,h);
    }


    public boolean contacEnemy(Enemy enemy){
        if(destroy==false && enemy.getState()!= Enemy.State.DEAD){
            if(x<enemy.getEnemyBody().getPosition().x+enemy.getW() && y<enemy.getEnemyBody().getPosition().y+enemy.getH()
                    && x+w >enemy.getEnemyBody().getPosition().x
                    && y+h>enemy.getEnemyBody().getPosition().y){
                System.out.println("Enemy Dead");
                destroy=true;
                return  true;
            }
        }

        return false;
    }
}