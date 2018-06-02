package com.islandboys.game.model;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.islandboys.game.MGame;
import com.islandboys.game.view.PlayScreen;


public class Ogre extends Enemy {
    private float stateTime;
    private float waltTime;
    private float idleTime;
    private float timeCount;


    private Animation idleAnimation;
    private Animation walkAnimation;
    private Array<TextureRegion>frames;
    private Texture idle;
    private float count=300;
    private PolygonShape shape;


    public Ogre(PlayScreen screen, float x_position, float y_position) {
        super(screen, x_position, y_position);
    }

    @Override
    protected void defineEnemyBody(float x_position,float y_position) {
        state=State.IDLE;
        BodyDef bdf=new BodyDef();
        bdf.position.set(x_position,y_position);
        bdf.type= BodyDef.BodyType.DynamicBody;
        enemyBody=world.createBody(bdf);

        FixtureDef fdef=new FixtureDef();

        shape=new PolygonShape();
        shape.setAsBox(9/GameInfo.PIXEL_METER,14.8f/GameInfo.PIXEL_METER);
        fdef.filter.categoryBits=GameInfo.ENEMY_BIT;
        width=9/GameInfo.PIXEL_METER;
        heigth=14.8f/GameInfo.PIXEL_METER;

        fdef.filter.maskBits=GameInfo.GROUND_BIT| GameInfo.BRICKS_BIT;

        fdef.shape=shape;
        enemyBody.createFixture(fdef);

        creatSprite(shape);


    }




    public TextureRegion getFrames(float delta){

        TextureRegion region;

        region=(TextureRegion)walkAnimation.getKeyFrame(stateTime,true);



        if(enemyBody.getLinearVelocity().x<0 && !region.isFlipX()){
            region.flip(true,false);


        }else if(enemyBody.getLinearVelocity().x>0 && region.isFlipX()){
            region.flip(true,false);
        }

        return region;


    }



    protected void creatSprite(PolygonShape shape) {
        stateTime=0;
        idle=new Texture("idle_O.png");
        Texture walk=new Texture("walk_O.png");
        Array<TextureRegion> frames =new Array<TextureRegion>();



        //IDLE
        if(State.IDLE==State.IDLE){

        }
        for(int i=0;i<4;i++){
            frames.add(new TextureRegion(idle,i*37,0,37,28));
        }

        idleAnimation=new Animation(0.2f,frames);
        frames.clear();

        //WALK
        for(int i=0;i<8;i++){
            frames.add(new TextureRegion(walk,i*47,0,47,28));
        }
        walkAnimation=new Animation(0.09f,frames);


        setBounds(getX(),getY(),55/GameInfo.PIXEL_METER,45/GameInfo.PIXEL_METER);
    }



    public void update(float delta){
        stateTime+=delta;


        count--;
        Vector2 vect=new Vector2();


        if(count>0 && count>50){
           vect=new Vector2(0.4f,0);
        }

        if( count<=50 && count >0){
            vect=new Vector2(2,0);
            if(count==50 && state!=State.DEAD &&(screen.getIslander().getX()+3>enemyBody.getPosition().x && screen.getIslander().getX()-3<enemyBody.getPosition().x ) ){
                MGame.assetManager.get("attack_O.ogg",Sound.class).play();
            }

    }

        if(count<=0 && count> -250){
            if(count<-300){
                count=300;
            }
            vect=new Vector2(-0.4f,0);
        }


        if(count<= -250){
            if(count<-300){
                count=300;
            }

            vect=new Vector2(-2,0);
            if(count==-250 && state!=State.DEAD &&  (screen.getIslander().getX()+3>enemyBody.getPosition().x && screen.getIslander().getX()-3<enemyBody.getPosition().x )){
                MGame.assetManager.get("attack_O.ogg",Sound.class).play();
            }

        }

        enemyBody.setLinearVelocity(vect);

        setRegion(getFrames(delta));

        if(count>0){
            setPosition(enemyBody.getPosition().x-getWidth()/2.7f,(enemyBody.getPosition().y-getHeight()/2.6f));

        }else if(count<=0){
            setPosition(enemyBody.getPosition().x-getWidth()/1.6f,(enemyBody.getPosition().y-getHeight()/2.6f));

        }


        if(destroy==false && state==State.DEAD ){
            world.destroyBody(enemyBody);
            destroy=true;
            stateTime=0;
        }


    }



    @Override
    public State getState() {
        return state;
    }

    @Override
    public void draw(Batch batch) {
        if(state!=State.DEAD||stateTime<0.2f ){
            super.draw(batch);

        }

    }
}
