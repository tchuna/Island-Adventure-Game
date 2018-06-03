package com.islandboys.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.islandboys.game.view.PlayScreen;

/**
 * Flame Class in the game
 */

public class Flame extends Enemy {
    private float stateTime;
    private Animation flameAnimation;
    private Array<TextureRegion> frames;
    private Texture flame;
    private PolygonShape shape;



    /**
     * Flame Constructor
     * @param  screen
     * @param x_position
     * @param y_position
     */
    public Flame(PlayScreen screen, float x_position, float y_position) {
        super(screen, x_position, y_position);
    }


    /**
     * method to define the characteristics the  flame
     * @param x_position
     * @param y_position
     */
    @Override
    protected void defineEnemyBody(float x_position, float y_position) {
        BodyDef bdf=new BodyDef();
        bdf.position.set(x_position,y_position);
        bdf.type= BodyDef.BodyType.DynamicBody;
        enemyBody=world.createBody(bdf);

        FixtureDef fdef=new FixtureDef();

        shape=new PolygonShape();
        shape.setAsBox(8/GameInfo.PIXEL_METER,7/GameInfo.PIXEL_METER);
        fdef.filter.categoryBits=GameInfo.ENEMY_BIT;

        fdef.filter.maskBits=GameInfo.GROUND_BIT|GameInfo.ISLANDER_BIT|GameInfo.ENEMY_BIT|GameInfo.FIRE_BIT;

        fdef.shape=shape;
        enemyBody.createFixture(fdef);

        creatSprite(shape);

    }



    /**
     * Create the flame sprite
     * @param shape
     */
    protected void creatSprite(PolygonShape shape) {
        stateTime=0;
        flame=new Texture("fl.png");
        Array<TextureRegion> frames =new Array<TextureRegion>();



        for(int i=0;i<9;i++){
            frames.add(new TextureRegion(flame,i*16,0,16,23));
        }

        flameAnimation=new Animation(0.2f,frames);
        setBounds(getX(),getY(),23/GameInfo.PIXEL_METER,28/GameInfo.PIXEL_METER);
    }


    /**
     * Get Frame images from the sprite
     * @param delta
     */
    public TextureRegion getFrames(float delta){

        TextureRegion region;

        region=(TextureRegion)flameAnimation.getKeyFrame(stateTime,true);

        return region;

    }


    /**
     * update flame state
     * @param delta
     */
    @Override
    public void update(float delta) {
        stateTime+=delta;
        setRegion(getFrames(delta));
        setPosition(enemyBody.getPosition().x-getWidth()/2f,(enemyBody.getPosition().y-getHeight()/2f));

    }
}
