package com.islandboys.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

public class Coins extends InteractiveTileObject {
    protected Hud hud;
    public Coins(World world, TiledMap map, com.badlogic.gdx.math.Rectangle rect,Hud hud){
        super(world,map,rect);
        this.hud=hud;
        fixture.setUserData(this);
        setCategoryFilter(GameInfo.COINS_BIT);



    }



    @Override
    public void onHeadHit() {
        Gdx.app.log("Coins","Coins");
        setCategoryFilter(GameInfo.DESTROED_BIT);
        hud.setScore(1);
        getCell().setTile(null);

    }
}





















