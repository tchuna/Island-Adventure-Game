package com.islandboys.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

public class Spike extends InteractiveTileObject {
    public Spike(World world, TiledMap map, com.badlogic.gdx.math.Rectangle rect){
        super(world,map,rect);
        fixture.setUserData(this);

    }


    @Override
    public void onHeadHit() {
        Gdx.app.log("Spike","Co");

    }
}
