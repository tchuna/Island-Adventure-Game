package com.islandboys.game.model;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.islandboys.game.MGame;
import com.islandboys.game.view.PlayScreen;

public class HellDog extends Enemy {

    private float stateTime;
    private Animation idleAnimation;
    private Animation runAnimation;
    private Array<TextureRegion> frames;
    private Texture idle;
    private Texture run;
    private PolygonShape shape;
    State state;
    int count=0;

    public HellDog(PlayScreen screen, float x_position, float y_position) {
        super(screen, x_position, y_position);
    }




    @Override
    protected void defineEnemyBody(float x_position, float y_position) {
        this.state=State.RUNNING;

        BodyDef bdf=new BodyDef();
        bdf.position.set(x_position,y_position);
        bdf.type= BodyDef.BodyType.DynamicBody;
        enemyBody=world.createBody(bdf);

        FixtureDef fdef=new FixtureDef();

        shape=new PolygonShape();
        shape.setAsBox(20/GameInfo.PIXEL_METER,12/GameInfo.PIXEL_METER);
        fdef.filter.categoryBits=GameInfo.ENEMY_BIT;

        fdef.filter.maskBits=GameInfo.GROUND_BIT|GameInfo.ISLANDER_BIT|GameInfo.ENEMY_BIT;

        fdef.shape=shape;
        enemyBody.createFixture(fdef);

        creatSprite(shape);
    }



    protected void creatSprite(PolygonShape shape) {
        stateTime=0;

        idle=new Texture("idle_D.png");
        run=new Texture("run_D.png");
        Array<TextureRegion> frames =new Array<TextureRegion>();



        for(int i=0;i<1;i++){
            frames.add(new TextureRegion(idle,i*65,0,65,32));
        }

        idleAnimation=new Animation(3f,frames);
        frames.clear();

        for(int i=0;i<5;i++){
            frames.add(new TextureRegion(run,i*65,0,65,32));
        }

        runAnimation=new Animation(0.08f,frames);

        setBounds(getX(),getY(),64/GameInfo.PIXEL_METER,32/GameInfo.PIXEL_METER);
    }


    public TextureRegion getFrames(float delta){

        TextureRegion region;
        if(state==State.IDLE){
            return (TextureRegion)idleAnimation.getKeyFrame(stateTime,true);
    }
        return  (TextureRegion)runAnimation.getKeyFrame(stateTime,true);

    }


    @Override
    public void update(float delta) {

        stateTime+=delta;
        count++;

        System.out.println(count);
        if(count==10){
            MGame.assetManager.get("attack_D.mp3",Sound.class).play();
        }
        setRegion(getFrames(delta));
        if(State.IDLE==state){
            setPosition(enemyBody.getPosition().x-getWidth()/2.2f,(enemyBody.getPosition().y-getHeight()/2.5f));
        }

        if(state==State.RUNNING){
            setPosition(enemyBody.getPosition().x-getWidth()/1.8f,(enemyBody.getPosition().y-getHeight()/2.5f));
        }

    }
}
