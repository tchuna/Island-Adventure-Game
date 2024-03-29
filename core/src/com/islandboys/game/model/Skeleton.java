package com.islandboys.game.model;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.islandboys.game.MGame;
import com.islandboys.game.view.PlayScreen;
/**
 * Skeleton Class in the game
 */
public class Skeleton extends Enemy{

    private float stateTime;
    private Animation idleAnimation, runAnimation,attackAnimation,deadAnimation;
    private Array<TextureRegion> frames;
    private Texture idle,run,attack,dead;
    private PolygonShape shape;
    private boolean  directionRigh=true;

    int count=0;

    /**
     * Skeleton Constructor
     * @param  screen
     * @param x_position
     * @param y_position
     */
    public Skeleton(PlayScreen screen, float x_position, float y_position) {
        super(screen, x_position, y_position);
    }




    /**
     * method to define the characteristics the  Skeleton
     * @param x_position
     * @param y_position
     */
    @Override
    protected void defineEnemyBody(float x_position, float y_position) {
        state= State.RUNNING;
        destroy=false;

        BodyDef bdf=new BodyDef();
        bdf.position.set(x_position,y_position);
        bdf.type= BodyDef.BodyType.DynamicBody;
        enemyBody=world.createBody(bdf);

        FixtureDef fdef=new FixtureDef();

        shape=new PolygonShape();
        shape.setAsBox(12/GameInfo.PIXEL_METER,13/GameInfo.PIXEL_METER);
        fdef.filter.categoryBits=GameInfo.ENEMY_BIT;
        width=12/GameInfo.PIXEL_METER;
        heigth=13/GameInfo.PIXEL_METER;

        fdef.filter.maskBits=GameInfo.GROUND_BIT| GameInfo.BRICKS_BIT;

        fdef.shape=shape;
        enemyBody.createFixture(fdef);

        creatSprite(shape);
    }


    /**
     * create a Skeleton Sprite
     */
    protected void creatSprite(PolygonShape shape) {
        stateTime=0;

        idle=new Texture("a.png");
        run=new Texture("run_S.png");
        attack=new Texture("attack_S.png");
        dead=new Texture("dead_S.png");
        Array<TextureRegion> frames =new Array<TextureRegion>();



        for(int i=0;i<11;i++){
            frames.add(new TextureRegion(idle,i*24,0,24,33));
        }

        idleAnimation=new Animation(0.2f,frames);
        frames.clear();

        for(int i=0;i<12;i++){
            frames.add(new TextureRegion(run,i*22,0,22,33));
        }
        runAnimation=new Animation(0.1f,frames);
        frames.clear();


        for(int i=0;i<18;i++){
            frames.add(new TextureRegion(attack,i*43,0,43,37));
        }

        attackAnimation=new Animation(0.04f,frames);
        frames.clear();


        for(int i=0;i<15;i++){
            frames.add(new TextureRegion(dead,i*33,0,33,31));
        }

        deadAnimation=new Animation(0.1f,frames);
        frames.clear();



    }

    /**
     * Get Frame images from the sprite
     * @param delta
     */
    public TextureRegion getFrames(float delta){
        TextureRegion region=new TextureRegion();



        if(state== Enemy.State.IDLE){
            setBounds(getX(),getY(),38/GameInfo.PIXEL_METER,46/GameInfo.PIXEL_METER);
            region=(TextureRegion)idleAnimation.getKeyFrame(stateTime,true);
        }

        if(state== State.RUNNING){
            setBounds(getX(),getY(),38/GameInfo.PIXEL_METER,46/GameInfo.PIXEL_METER);
            region=(TextureRegion)runAnimation.getKeyFrame(stateTime,true);
        }

        if(state== State.DEAD){
            setBounds(getX(),getY(),38/GameInfo.PIXEL_METER,46/GameInfo.PIXEL_METER);
            region=(TextureRegion)deadAnimation.getKeyFrame(stateTime,true);
        }

        if(state==State.ATTACK){

            setBounds(getX(),getY(),55/GameInfo.PIXEL_METER,60/GameInfo.PIXEL_METER);
           region=(TextureRegion)attackAnimation.getKeyFrame(stateTime,true);

        }


        if((enemyBody.getLinearVelocity().x<0 || !directionRigh) && !region.isFlipX()){
            region.flip(true,false);
            directionRigh=false;


        }else if((enemyBody.getLinearVelocity().x>0||directionRigh) && region.isFlipX()){
            region.flip(true,false);
            directionRigh=true;
        }


        return region;


    }

    /**
     * Orc sprite position in a body
     */
    public  void spritePosition(){
        if(Enemy.State.IDLE==state && directionRigh==true){
            setPosition(enemyBody.getPosition().x-getWidth()/3f,(enemyBody.getPosition().y-getHeight()/2.9f));
        }else if(Enemy.State.IDLE==state && directionRigh==false){

            setPosition(enemyBody.getPosition().x-getWidth()/1.5f,(enemyBody.getPosition().y-getHeight()/2.9f));
        }

        if(state== Enemy.State.RUNNING && directionRigh==true){
            setPosition(enemyBody.getPosition().x-getWidth()/3f,(enemyBody.getPosition().y-getHeight()/2.9f));
        }else if(state== Enemy.State.RUNNING && directionRigh==false){

            setPosition(enemyBody.getPosition().x-getWidth()/1.5f,(enemyBody.getPosition().y-getHeight()/2.9f));
        }

        if(State.ATTACK==state && directionRigh==true){
            setPosition(enemyBody.getPosition().x-getWidth()/3.4f,(enemyBody.getPosition().y-getHeight()/3.7f));
        }else if(State.ATTACK==state && directionRigh==false){

            setPosition(enemyBody.getPosition().x-getWidth()/1.4f,(enemyBody.getPosition().y-getHeight()/3.7f));
        }

        if(State.DEAD==state){
            setPosition(enemyBody.getPosition().x-getWidth()/2f,(enemyBody.getPosition().y-getHeight()/2.9f));
        }

    }

    /**
     * Update Skeleton State
     * @param delta
     */
    @Override
    public void update(float delta) {
        stateTime+=delta;
        count++;
        float result=screen.getIslander().getX()-enemyBody.getPosition().x;



        if(state!=State.DEAD){
            if(result<0.4 && result>-0.7 && screen.getIslander().getY()<=enemyBody.getPosition().y){
                state=State.ATTACK;

            }else{
                state=State.IDLE.RUNNING;
            }

            if(count<70){

                if(state==State.RUNNING){
                    Vector2 vect=new Vector2(0.5f,0);
                    enemyBody.setLinearVelocity(vect);
                }

            }else if(count>70){
                if(state==State.RUNNING){
                    Vector2 vect=new Vector2(-0.5f,0);
                    enemyBody.setLinearVelocity(vect);
                }

            }

            if(count>140){
                count=0;
            }
        }




        if(destroy==false && state==State.DEAD ){
            world.destroyBody(enemyBody);
            MGame.assetManager.get("sk.mp3",Sound.class).play();
            destroy=true;
            stateTime=0;
        }



        setRegion(getFrames(delta));
        spritePosition();





    }


    /**
     * get Skeleton  state
     * @return state
     */
    @Override
    public State getState() {
        return state;
    }


    /**
     * draw method
     * @param
     */
   @Override
    public void draw(Batch batch) {
        if(state!=State.DEAD || stateTime<1.5f){
            super.draw(batch);

        }

    }
}
