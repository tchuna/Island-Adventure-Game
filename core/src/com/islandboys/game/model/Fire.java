package com.islandboys.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.islandboys.game.MGame;
import com.islandboys.game.view.PlayScreen;


/**
 *  Fire class in game
 */
public class Fire extends  InteractiveTileObject {

    private int counthurt=0;

    /**
     *  Fire  constructor
     *  @param screen
     *  @param rect
     *  @param hud
     */
    public Fire(PlayScreen screen, com.badlogic.gdx.math.Rectangle rect, Hud hud){
        super(screen,rect,hud);
        fixture.setUserData(this);
        setCategoryFilter(GameInfo.FIRE_BIT);

    }



    /**
     * Check if the islander  touched the fire
     */
    @Override
    public void onContactBodys() {
        counthurt++;
        if(counthurt==20){
            hud.setLiveLevel(1);
        }

        if(counthurt>20){
            counthurt=0;
        }
        hud.setLiveLevel(1);
        MGame.assetManager.get("hurt.wav",Sound.class).play();

    }


}
