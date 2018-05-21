package com.islandboys.game.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Rectangle;


public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle  bounds;
    protected Body body;
    protected Fixture fixture;


    public InteractiveTileObject(World world, TiledMap map, com.badlogic.gdx.math.Rectangle rect){
        this.world=world;
        this.map=map;
        this.bounds=rect;

        BodyDef bdef=new BodyDef();
        FixtureDef fdef=new FixtureDef();
        PolygonShape shape=new PolygonShape();


        bdef.type = BodyDef.BodyType.StaticBody;

        bdef.position.set((rect.getX() + rect.getWidth() / 2) / GameInfo.PIXEL_METER, (rect.getY() + rect.getHeight() / 2) / GameInfo.PIXEL_METER);
        body = world.createBody(bdef);

        shape.setAsBox(rect.getWidth() / 2 / GameInfo.PIXEL_METER, rect.getHeight() / 2 / GameInfo.PIXEL_METER);
        fdef.shape = shape;
        fixture=body.createFixture(fdef);

    }


    public abstract void onHeadHit();

    public void setCategoryFilter(short filterBIt){
        Filter filter =new Filter();
        filter.categoryBits=filterBIt;
        fixture.setFilterData(filter);


    }


}
