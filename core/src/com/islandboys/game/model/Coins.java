package com.islandboys.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.islandboys.game.MGame;
import com.islandboys.game.view.PlayScreen;

public class Coins extends InteractiveTileObject {
    private Hud hud;
    public Coins(PlayScreen screen, com.badlogic.gdx.math.Rectangle rect, Hud hud){
        super(screen,rect,hud);
        fixture.setUserData(this);
        setCategoryFilter(GameInfo.COINS_BIT);



    }



    @Override
    public void  onContactBodys() {
        Gdx.app.log("Coins","Coins");
        MGame.assetManager.get("coin.wav",Sound.class).play();
        setCategoryFilter(GameInfo.DESTROED_BIT);
        hud.setScore(1);
        getCell().setTile(null);

    }


}





















