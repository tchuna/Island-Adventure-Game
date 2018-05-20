package com.islandboys.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.islandboys.game.view.PlayScreen;

public class Islander extends Sprite {

    public enum  State{IDLE,JUMPING,RUNNING,FALLING,DEAD}

    public World world;
    public Body body;
    private Texture idle;
    private Texture jump;
    private Texture running;
    private Texture fail;
    private TextureRegion standImage;

    public State currentState;
    public State previousState;

    private Animation islanderRunning;
    private Animation islanderIdle;
    private Animation islanderJump;
    private Animation islanderFailng;

    private float stateTimer;
    private boolean directionRigh;




    public Islander(World world, PlayScreen screen){
        this.world =world;

        idle=new Texture("idle.png");
        running=new Texture("r.png");
        jump=new Texture("jump.png");
        fail=new Texture("failling.png");

        currentState=State.IDLE;
        previousState=State.IDLE;
        directionRigh=true;
        stateTimer=0;

        Array<TextureRegion> frames =new Array<TextureRegion>();

        for(int i=0;i<9;i++){
            frames.add(new TextureRegion(idle,i*24,0,24,36));
        }

        islanderIdle=new Animation(0.2f,frames);
        frames.clear();


       for(int i=0;i<8;i++){
            frames.add(new TextureRegion(running,i*25,0,25,36));
        }
        islanderRunning=new Animation(0.1f,frames);
        frames.clear();


        for(int i=0;i<4;i++){
            frames.add(new TextureRegion(jump,i*26,0,26,36));
        }
        islanderJump=new Animation(0.1f,frames);
        frames.clear();

        for(int i=0;i<2;i++){
            frames.add(new TextureRegion(fail,i*25,0,25,36));
        }
        islanderFailng=new Animation(0.1f,frames);
        frames.clear();


        setBounds(0,0,25/GameInfo.PIXEL_METER,36/GameInfo.PIXEL_METER);
        defineIslander();


    }


    public TextureRegion getFrames(float delta){

        TextureRegion region;
        currentState=getState();

        switch (currentState){
            case JUMPING: region=(TextureRegion) islanderJump.getKeyFrame(stateTimer);break;
            case RUNNING: region=(TextureRegion) islanderRunning.getKeyFrame(stateTimer,true); break;
            case FALLING: region=(TextureRegion) islanderFailng.getKeyFrame(stateTimer,true);break;
            default: region=(TextureRegion) islanderIdle.getKeyFrame(stateTimer,true);break;

        }

        if((body.getLinearVelocity().x<0 || !directionRigh) && !region.isFlipX()){
            region.flip(true,false);
            directionRigh=false;

        }else if((body.getLinearVelocity().x>0|| directionRigh) && region.isFlipX()){
            region.flip(true,false);
            directionRigh=true;
        }

        stateTimer=currentState==previousState?stateTimer+delta:0;
        previousState=currentState;

        return region;


    }


    public State getState(){
        if(body.getLinearVelocity().y>0 || (body.getLinearVelocity().y< 0 && previousState==State.JUMPING )){
            return  State.JUMPING;
        }else if(body.getLinearVelocity().y<0 ){
            return State.FALLING;


        }else if(body.getLinearVelocity().x==0){
            return State.IDLE;
        }else if(body.getLinearVelocity().x!=0) {
            return State.RUNNING;
        }

        return  State.DEAD;
    }

    public void update(float delta){
        setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight()/2);
        setRegion(getFrames(delta));

    }

    public void defineIslander(){
        BodyDef bdf=new BodyDef();
        bdf.position.set(200/GameInfo.PIXEL_METER,48/GameInfo.PIXEL_METER);
        bdf.type= BodyDef.BodyType.DynamicBody;
        body=world.createBody(bdf);

        FixtureDef fdef=new FixtureDef();
        CircleShape shape= new CircleShape();
        shape.setRadius(15/GameInfo.PIXEL_METER);

        fdef.shape=shape;
        body.createFixture(fdef);


    }

}
