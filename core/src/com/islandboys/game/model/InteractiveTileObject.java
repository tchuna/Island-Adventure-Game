package com.islandboys.game.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Rectangle;
import com.islandboys.game.view.PlayScreen;



/**
 * Abstract Class InteractiveTileObject in Game
 */
public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Hud hud;
    protected Rectangle  bounds;
    protected Body body;
    protected Fixture fixture;



    /**
     * InteractiveTileObject Constructor
     * @param screen
     * @param rect
     * @param hud
     */
    public InteractiveTileObject(PlayScreen screen, com.badlogic.gdx.math.Rectangle rect, Hud hud){
        this.world=screen.getWorld();
        this.map=screen.getMap();
        this.bounds=rect;
        this.hud=hud;

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


    public abstract void onContactBodys();



    /**
     * Set filter Bit
     * @param filterBIt
     */
    public void setCategoryFilter(short filterBIt){
        Filter filter =new Filter();
        filter.categoryBits=filterBIt;
        fixture.setFilterData(filter);


    }


    /**
     * Get Tile Cell
     * @return tile
     */
    public TiledMapTileLayer.Cell getCell(){

        TiledMapTileLayer layer=(TiledMapTileLayer) map.getLayers().get(1);

        return layer.getCell((int)(body.getPosition().x*GameInfo.PIXEL_METER/16),
                (int)(body.getPosition().y*GameInfo.PIXEL_METER/16));
    }
















}
