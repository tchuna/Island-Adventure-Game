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

        width=(int)(20/GameInfo.PIXEL_METER);//largura
        heigth=(int)(12/GameInfo.PIXEL_METER);//altura


        fdef.filter.maskBits=GameInfo.GROUND_BIT| GameInfo.BRICKS_BIT;

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

        TextureRegion region=new TextureRegion();
        if(state==State.IDLE){
            region=(TextureRegion)idleAnimation.getKeyFrame(stateTime,true);
        }

        if(state==State.RUNNING){
         region =(TextureRegion)runAnimation.getKeyFrame(stateTime,true);
        }

        if(state==State.DEAD){
            region =(TextureRegion)idleAnimation.getKeyFrame(stateTime,true);
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

        if(State.IDLE==state){
            setPosition(enemyBody.getPosition().x-getWidth()/2.2f,(enemyBody.getPosition().y-getHeight()/2.5f));
        }

        if(state==State.RUNNING){
            setPosition(enemyBody.getPosition().x-getWidth()/1.8f,(enemyBody.getPosition().y-getHeight()/2.5f));
        }


    }

    public void setSt(State state){
        this.state=state;
    }


    @Override
    public void update(float delta) {

        stateTime+=delta;
        count++;
        float result=screen.getIslander().getX()-enemyBody.getPosition().x;



        if(state!=State.DEAD){
            if(result<0.4 && result>-0.7 && screen.getIslander().getY()<=enemyBody.getPosition().y){
                state=State.IDLE;
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
            System.out.println("DEAD");
            world.destroyBody(enemyBody);
            destroy=true;
            stateTime=0;
        }



        setRegion(getFrames(delta));
        spritePosition();



       /* if(count==10){
            MGame.assetManager.get("attack_D.mp3",Sound.class).play();
        }*/


    }

    @Override
    public State getState() {
        return state;
    }
    @Override
    public void draw(Batch batch) {
        if(state!=State.DEAD || stateTime<1f){
            super.draw(batch);

        }

    }


}
