package com.islandboys.game.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

public class Bricks extends InteractiveTileObject {
    public Bricks(World world, TiledMap map, com.badlogic.gdx.math.Rectangle rect){
        super(world,map,rect);

    }
}
