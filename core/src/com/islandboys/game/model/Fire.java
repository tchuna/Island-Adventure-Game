package com.islandboys.game.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

public class Fire extends  InteractiveTileObject {

    public Fire(World world, TiledMap map, com.badlogic.gdx.math.Rectangle rect){
        super(world,map,rect);

    }
}
