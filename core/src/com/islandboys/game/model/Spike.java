package com.islandboys.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.islandboys.game.MGame;
import com.islandboys.game.view.PlayScreen;

public class Spike extends InteractiveTileObject {
    public Spike(PlayScreen screen, com.badlogic.gdx.math.Rectangle rect, Hud hud){
        super(screen,rect,hud);
        fixture.setUserData(this);
        setCategoryFilter(GameInfo.SPIKE_BIT);

    }


    @Override
    public void onContactBodys() {
        hud.setLiveLevel(1);
        MGame.assetManager.get("hurt.wav",Sound.class).play();

    }

}
