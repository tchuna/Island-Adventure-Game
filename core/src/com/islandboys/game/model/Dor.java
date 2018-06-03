package com.islandboys.game.model;

import com.badlogic.gdx.Gdx;
import com.islandboys.game.view.PlayScreen;

/**
 *  Dor Class in game
 */
public class Dor extends InteractiveTileObject {
    private PlayScreen screen;

    /**
     *  Dor  constructor
     *  @param screen
     *  @param rect
     *  @param hud
     */
    public Dor(PlayScreen screen, com.badlogic.gdx.math.Rectangle rect, Hud hud){
        super(screen,rect,hud);
        this.screen=screen;
        fixture.setUserData(this);
        setCategoryFilter(GameInfo.DOR_BIT);



    }



    /**
     * Check if the islander  arrived the dor
     */
    @Override
    public void  onContactBodys() {


        if(screen.getIslander().getKey()){
            setCategoryFilter(GameInfo.DESTROED_BIT);
            screen.getIslander().setWin(true);
        }


    }


}