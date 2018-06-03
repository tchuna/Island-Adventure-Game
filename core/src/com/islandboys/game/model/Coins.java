package com.islandboys.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.islandboys.game.MGame;
import com.islandboys.game.view.PlayScreen;


/**
 *  Class  for Coins  in the  Game
 *
 */
public class Coins extends InteractiveTileObject {
    private Hud hud;
    /**
     * Coins constructor
     *   @param screen
     *  @param rect
     *  @param hud
     */
    public Coins(PlayScreen screen, com.badlogic.gdx.math.Rectangle rect, Hud hud){
        super(screen,rect,hud);
        fixture.setUserData(this);
        setCategoryFilter(GameInfo.COINS_BIT);



    }




    /**
     * Check if the islander catch coins
     */
    @Override
    public void  onContactBodys() {

        MGame.assetManager.get("coin.wav",Sound.class).play();
        setCategoryFilter(GameInfo.DESTROED_BIT);
        hud.setScore(1);
        getCell().setTile(null);

    }


}





















