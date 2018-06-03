package com.islandboys.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.islandboys.game.view.PlayScreen;

/**
 *  Class  for Bricks  in the  Game
 *
 */
public class Bricks extends InteractiveTileObject {
    public Bricks(PlayScreen screen, com.badlogic.gdx.math.Rectangle rect, Hud hud){
        super(screen,rect,hud);
        fixture.setUserData(this);
        setCategoryFilter(GameInfo.BRICKS_BIT);

    }



    @Override
    public void onContactBodys() {

    }


}
