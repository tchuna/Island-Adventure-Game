package com.islandboys.game.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.islandboys.game.model.Bricks;
import com.islandboys.game.model.Coins;
import com.islandboys.game.model.Fire;
import com.islandboys.game.model.GameInfo;
import com.islandboys.game.model.Spike;

public class Box2DWorldCreator {




    public Box2DWorldCreator(World world, TiledMap map) {
        CreatGroundBod(world,map);
        createBodys(world,map);


    }

    public void CreatGroundBod(World world, TiledMap map){

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //ground bdx
        for (MapObject object : map.getLayers().get(GameInfo.GROUND).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;

            bdef.position.set((rect.getX() + rect.getWidth() / 2) / GameInfo.PIXEL_METER, (rect.getY() + rect.getHeight() / 2) / GameInfo.PIXEL_METER);
            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / GameInfo.PIXEL_METER, rect.getHeight() / 2 / GameInfo.PIXEL_METER);
            fdef.shape = shape;
            body.createFixture(fdef);

        }

    }


    public void createBodys(World world, TiledMap map) {

        //bricks bdx
        for (MapObject object : map.getLayers().get(GameInfo.BRICKS).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Bricks(world,map,rect);

        }

        //spikes bdx
        for (MapObject object : map.getLayers().get(GameInfo.SPIKE).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Spike(world,map,rect);

        }

        //fire bdx
        for (MapObject object : map.getLayers().get(GameInfo.FIRE).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Fire(world,map,rect);

        }

        //coins bdx
        for (MapObject object : map.getLayers().get(GameInfo.COINS).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Coins(world, map, rect);

        }


    }
}
