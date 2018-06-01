package com.islandboys.game.model;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class GameInfo implements Disposable {



    public static final int WIDTH=800;
    public static final int HEIGHT=480;

    public static final int C_WIDTH=500;
    public static final int C_HEIGHT=300;


    public static final int V_WIDTH=400;
    public static final int V_HEIGHT=208;
    public static final float PIXEL_METER=85;


    public static final int GROUND=2;
    public static final int BRICKS=3;
    public static final int SPIKE=4;
    public static final int FIRE=5;
    public static final int COINS=6;
    public static final int OGRES=7;
    public static final int FLAME=8;
    public static final int SKELETON=9;
    public static final int ORCS=10;
    public static final int UNDEAD=11;
    public static final int HELLD=12;
    public static final int KEY=13;
    public static final int DOR=14;


    public static final short GROUND_BIT=1;
    public static final short ISLANDER_BIT=2;
    public static final short BRICKS_BIT=4;
    public static final short ENEMY_BIT=6;
    public static final short COINS_BIT=8;
    public static final short KEY_BIT=10;
    public static final short DOR_BIT=12;
    public static final short FIRE_BIT=16;
    public static final short SPIKE_BIT=32;


    public static final short DESTROED_BIT=64;

    public static final  float ARROW_SPEED=0.06f;



    @Override
    public void dispose() {

    }
}
