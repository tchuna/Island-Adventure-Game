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
import com.badlogic.gdx.utils.Array;
import com.islandboys.game.model.Bricks;
import com.islandboys.game.model.Coins;
import com.islandboys.game.model.Fire;
import com.islandboys.game.model.Flame;
import com.islandboys.game.model.GameInfo;
import com.islandboys.game.model.Hud;
import com.islandboys.game.model.Ogre;
import com.islandboys.game.model.Spike;
import com.islandboys.game.view.PlayScreen;

public class Box2DWorldCreator {

    protected  Hud hud;


    private Array<Ogre> ogres;
    private Array<Flame> flames;


    public Box2DWorldCreator(PlayScreen screen,Hud hud ) {
        this.hud=hud;
        World world=screen.getWorld();
        TiledMap map=screen.getMap();
        CreatGroundBody(screen.getWorld(),screen.getMap());
        createBodys(screen);


    }

    public void CreatGroundBody(World world, TiledMap map){

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



    public void createBodys(PlayScreen screen) {

        //plat bdx
        for (MapObject object : screen.getMap().getLayers().get(GameInfo.BRICKS).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Bricks(screen,rect,hud);

        }

        //spikes bdx
        for (MapObject object : screen.getMap().getLayers().get(GameInfo.SPIKE).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Spike(screen,rect,hud);

        }

        //fire bdx
        for (MapObject object : screen.getMap().getLayers().get(GameInfo.FIRE).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Fire(screen,rect,hud);

        }

        //coins bdx
        for (MapObject object : screen.getMap().getLayers().get(GameInfo.COINS).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Coins(screen, rect,hud);

        }


        //ogres bdx
        ogres=new Array<Ogre>();
        for (MapObject object : screen.getMap().getLayers().get(GameInfo.OGRES).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

           ogres.add(new Ogre(screen,rect.getX()/GameInfo.PIXEL_METER,rect.getY()/GameInfo.PIXEL_METER));

        }

        //ogres bdx
        flames=new Array<Flame>();
        for (MapObject object : screen.getMap().getLayers().get(GameInfo.FLAME).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            flames.add(new Flame(screen,rect.getX()/GameInfo.PIXEL_METER,rect.getY()/GameInfo.PIXEL_METER));

        }



    }

    public Array<Ogre> getOgres() {
        return ogres;
    }

    public Array<Flame> getFlames() {
        return flames;
    }



}
