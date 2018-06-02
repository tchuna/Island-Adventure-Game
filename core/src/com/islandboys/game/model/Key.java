package com.islandboys.game.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.islandboys.game.MGame;
import com.islandboys.game.view.PlayScreen;

public class Key extends InteractiveTileObject {
    private PlayScreen screen;
    public Key(PlayScreen screen, com.badlogic.gdx.math.Rectangle rect, Hud hud){
        super(screen,rect,hud);
        this.screen=screen;
        fixture.setUserData(this);
        setCategoryFilter(GameInfo.KEY_BIT);



    }



    @Override
    public void  onContactBodys() {
        MGame.assetManager.get("key_s.wav",Sound.class).play();
        setCategoryFilter(GameInfo.DESTROED_BIT);
        screen.getIslander().setKey();
        getCell().setTile(null);


    }


}
