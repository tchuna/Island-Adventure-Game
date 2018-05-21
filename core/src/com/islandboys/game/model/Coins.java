package com.islandboys.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

public class Coins extends InteractiveTileObject {
    public Coins(World world, TiledMap map, com.badlogic.gdx.math.Rectangle rect){
        super(world,map,rect);
        fixture.setUserData(this);
        setCategoryFilter(GameInfo.COINS_BIT);


    }



    @Override
    public void onHeadHit() {
        Gdx.app.log("Coins","Coins");
        setCategoryFilter(GameInfo.DESTROED_BIT);


    }
}
