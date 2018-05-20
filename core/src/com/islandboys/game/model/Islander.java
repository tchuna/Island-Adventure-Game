package com.islandboys.game.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Islander extends Sprite {

    public World world;
    public Body body;

    public Islander(World world){
        this.world =world;

        defineIslander();


    }

    public void defineIslander(){
        BodyDef bdf=new BodyDef();
        bdf.position.set(48/GameInfo.PIXEL_METER,48/GameInfo.PIXEL_METER);
        bdf.type= BodyDef.BodyType.DynamicBody;
        body=world.createBody(bdf);

        FixtureDef fdef=new FixtureDef();
        CircleShape shape= new CircleShape();
        shape.setRadius(20/GameInfo.PIXEL_METER);

        fdef.shape=shape;
        body.createFixture(fdef);


    }

}
