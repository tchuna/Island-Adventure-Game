package com.islandboys.game.model;

import com.badlogic.gdx.Gdx;
import com.islandboys.game.view.PlayScreen;

public class Dor extends InteractiveTileObject {
    private PlayScreen screen;
    public Dor(PlayScreen screen, com.badlogic.gdx.math.Rectangle rect, Hud hud){
        super(screen,rect,hud);
        this.screen=screen;
        fixture.setUserData(this);
        setCategoryFilter(GameInfo.DOR_BIT);



    }



    @Override
    public void  onContactBodys() {
        Gdx.app.log("DOR","DOR");
        if(screen.getIslander().getKey()){
            setCategoryFilter(GameInfo.DESTROED_BIT);
            System.out.println("Opendorr");
        }else{

            System.out.println("closedorr");
        }


    }


}