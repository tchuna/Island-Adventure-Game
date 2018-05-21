package com.islandboys.game.model;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class GameInfo implements Disposable {

   private static Texture islanderImages;

    private static TextureRegion[] runningFrames;
    private static TextureRegion[] startingFrames;
    private static TextureRegion[] fallingFrames;
    private static TextureRegion[] deadFrames;
    private  static TextureRegion stand;





    public static final int WIDTH=800;
    public static final int HEIGHT=480;

    public static final int V_WIDTH=400;
    public static final int V_HEIGHT=208;
    public static final float PIXEL_METER=85;

    public static final int ISLANDER_1=1;
    public static final int ISLANDER_2=2;
    public static final int ISLANDER_3=3;

    public static final int GROUND=2;
    public static final int BRICKS=3;
    public static final int SPIKE=4;
    public static final int FIRE=5;
    public static final int COINS=6;

    public static final short DEFAULT_BIT=1;
    public static final short ISLANDER_BIT=2;
    public static final short BRICKS_BIT=4;
    public static final short COINS_BIT=8;
    public static final short FIRE_BIT=16;
    public static final short SPIKE_BIT=32;

    public static final short DESTROED_BIT=64;





    private static Texture getIslanderImages(){
        if(islanderImages==null){
            islanderImages=new Texture("sprites.png");
        }

        return islanderImages;

    }







    @Override
    public void dispose() {

    }
}
