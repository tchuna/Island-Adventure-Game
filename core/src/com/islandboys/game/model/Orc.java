package com.islandboys.game.model;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.islandboys.game.MGame;
import com.islandboys.game.view.PlayScreen;

import java.util.ArrayList;

public class Orc extends Enemy {

    private float stateTime;
    private Animation idleAnimation, runAnimation, attackAnimation, deadAnimation;
    private Array<TextureRegion> frames;
    private Texture idle, run, attack, dead;
    private PolygonShape shape;
    Enemy.State state;

    int count = 0;

    public Orc(PlayScreen screen, float x_position, float y_position) {
        super(screen, x_position, y_position);

    }



    @Override
    public State getState() {
        return state;
    }

    @Override
    protected void defineEnemyBody(float x_position, float y_position) {
        this.state = State.RUNNING;

        BodyDef bdf = new BodyDef();
        bdf.position.set(x_position, y_position);
        bdf.type = BodyDef.BodyType.DynamicBody;
        enemyBody = world.createBody(bdf);

        FixtureDef fdef = new FixtureDef();

        shape = new PolygonShape();
        shape.setAsBox(12 / GameInfo.PIXEL_METER, 21 / GameInfo.PIXEL_METER);
        fdef.filter.categoryBits = GameInfo.ENEMY_BIT;
        width=12 / GameInfo.PIXEL_METER;
        heigth=21 / GameInfo.PIXEL_METER;


        fdef.filter.maskBits = GameInfo.GROUND_BIT | GameInfo.ISLANDER_BIT | GameInfo.ENEMY_BIT;
        fdef.restitution=1f;
        fdef.shape = shape;
        enemyBody.createFixture(fdef);


        creatSprite(shape);
    }


    protected void creatSprite(PolygonShape shape) {
        stateTime = 0;

        idle = new Texture("idle_Oc.png");
        run = new Texture("run_Oc.png");
        attack = new Texture("attack_Oc.png");
        dead = new Texture("dead_Oc.png");
        Array<TextureRegion> frames = new Array<TextureRegion>();


        for (int i = 0; i < 10; i++) {
            frames.add(new TextureRegion(idle, i * 32, 0, 32, 32));
        }

        idleAnimation = new Animation(0.3f, frames);
        frames.clear();

        for (int i = 0; i <8; i++) {
            frames.add(new TextureRegion(run, i * 32, 0, 32, 32));
        }
        runAnimation = new Animation(0.2f, frames);
        frames.clear();


        for (int i = 0; i < 10; i++) {
            frames.add(new TextureRegion(attack, i * 32, 0, 32, 32));
        }

        attackAnimation = new Animation(0.09f, frames);
        frames.clear();


        for (int i = 0; i < 8; i++) {
            frames.add(new TextureRegion(dead, i * 32, 0, 32, 32));
        }

        deadAnimation = new Animation(0.2f, frames);
        frames.clear();


    }


    public TextureRegion getFrames(float delta) {

        TextureRegion region=new TextureRegion();

        if (state == Enemy.State.IDLE) {
            setBounds(getX(), getY(), 60 / GameInfo.PIXEL_METER, 44 / GameInfo.PIXEL_METER);
           region= (TextureRegion) idleAnimation.getKeyFrame(stateTime, true);
        }

        if (state == State.RUNNING) {
            setBounds(getX(), getY(), 60 / GameInfo.PIXEL_METER, 44 / GameInfo.PIXEL_METER);
            region= (TextureRegion) runAnimation.getKeyFrame(stateTime, true);
        }

        if (state == State.DEAD) {
            setBounds(getX(), getY(), 60 / GameInfo.PIXEL_METER, 44 / GameInfo.PIXEL_METER);
           region=(TextureRegion) deadAnimation.getKeyFrame(stateTime, true);
        }


        if(state==State.ATTACK){
            setBounds(getX(), getY(), 60 / GameInfo.PIXEL_METER, 44 / GameInfo.PIXEL_METER);
            region=(TextureRegion) attackAnimation.getKeyFrame(stateTime, true);
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

    public  void spritePosition(){

        if (Enemy.State.IDLE == state) {
            setPosition(enemyBody.getPosition().x - getWidth() / 1.9f, (enemyBody.getPosition().y - getHeight() / 2f));
        }

        if (state == Enemy.State.RUNNING) {
            setPosition(enemyBody.getPosition().x - getWidth() / 1.9f, (enemyBody.getPosition().y - getHeight() / 1.8f));
        }

        if (State.ATTACK == state) {
            setPosition(enemyBody.getPosition().x - getWidth() / 2.2f, (enemyBody.getPosition().y - getHeight() / 2f));
        }

        if (State.DEAD == state) {
            setPosition(enemyBody.getPosition().x - getWidth() / 2f, (enemyBody.getPosition().y - getHeight() / 2f));
        }

    }


    @Override
    public void update(float delta) {

        stateTime+=delta;
        count++;
        float result=screen.getIslander().getX()-enemyBody.getPosition().x;


        if(state!=Enemy.State.DEAD){

            if(result<0.4 && result>-0.6 && screen.getIslander().getY()<=enemyBody.getPosition().y){
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

            if(count>140 && state!=State.ATTACK){
                this.state=State.IDLE;
                Vector2 vect=new Vector2(0,0);
                enemyBody.setLinearVelocity(vect);
                if(count==146 && result<2.5 && result>-2.5f){
                    MGame.assetManager.get("orc_.mp3",Sound.class).play();
                }

            }

            if(count>340){
                count=0;
            }
        }



        if(destroy==false && state==State.DEAD ){

            world.destroyBody(enemyBody);
            screen.getWorld().destroyBody(enemyBody);
            destroy=true;

            stateTime=0;

        }



        setRegion(getFrames(delta));
        spritePosition();

    }

    @Override
    public void draw(Batch batch) {
        if(state!=State.DEAD || stateTime<1.5f){
            super.draw(batch);

        }

    }

    public void setSt(){
        this.state=State.DEAD;
    }

}