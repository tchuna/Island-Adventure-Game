package com.islandboys.game.view;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.islandboys.game.MGame;
import com.islandboys.game.controller.Box2DWorldCreator;
import com.islandboys.game.controller.WorldContactListener;
import com.islandboys.game.model.Arrow;
import com.islandboys.game.model.Controller;
import com.islandboys.game.model.Enemy;
import com.islandboys.game.model.Flame;
import com.islandboys.game.model.GameInfo;
import com.islandboys.game.model.HellDog;
import com.islandboys.game.model.Hud;
import com.islandboys.game.model.Islander;
import com.islandboys.game.model.Ogre;
import com.islandboys.game.model.Orc;
import com.islandboys.game.model.Skeleton;
import com.islandboys.game.model.Undead;

import java.util.ArrayList;

public class PlayScreen implements Screen{
    private MGame game;
    private Hud hudgame;
    private Viewport gamePort;
    private OrthographicCamera gamecam;
    private Texture texture;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Stage stage;
    private Box2DWorldCreator worldCreator;

    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private Islander islander;
    private WorldContactListener contacListener;

    private Music music;
    private Music coinSong;

    private Controller control;

    //temporarios

    private HellDog dog;


    public PlayScreen(MGame game,int level){
        this.game=game;
        gamecam = new OrthographicCamera();
        gamePort=new FitViewport(GameInfo.V_WIDTH/GameInfo.PIXEL_METER,GameInfo.V_HEIGHT/ GameInfo.PIXEL_METER,gamecam);
        stage=new Stage(gamePort);

        mapLoader=new TmxMapLoader();
        map = mapLoader.load("level"+level+".tmx");
        renderer=new OrthogonalTiledMapRenderer(map,1/GameInfo.PIXEL_METER);

        gamecam.position.set(gamePort.getScreenWidth()/2,gamePort.getScreenHeight()/2,0);

        world=new World(new Vector2(0,-10),true);
        box2DDebugRenderer=new Box2DDebugRenderer();
        worldCreator=new Box2DWorldCreator(this,hudgame);
        islander=new Islander(this);
        contacListener=new WorldContactListener();

        world.setContactListener(contacListener);


        music= game.assetManager.get("song.wav",Music.class);
        music.setLooping(true);
        //music.play();
        music.setVolume(0.05f);

        hudgame=new Hud(game.batch,islander);


        this.control=new Controller();


        ///temporarios

        dog=new HellDog(this,.32f,.32f);


    }


    public TiledMap getMap() {
        return map;
    }

    public World getWorld() {
        return world;
    }

    public OrthographicCamera getGamecam(){
        return gamecam;
    }

    public Islander getIslander() {
        return islander;
    }



    public void input(float delta){

        if(control.isRightPressed())
            islander.getBody().setLinearVelocity(new Vector2(2, islander.getBody().getLinearVelocity().y));
        else if (control.isLeftPressed())
            islander.getBody().setLinearVelocity(new Vector2(-2, islander.getBody().getLinearVelocity().y));
        else
            islander.getBody().setLinearVelocity(new Vector2(0, islander.getBody().getLinearVelocity().y));
        if (control.isUpPressed() && islander.getBody().getLinearVelocity().y == 0)
            islander.getBody().applyLinearImpulse(new Vector2(0, 4f), islander.getBody().getWorldCenter(), true);

        else  if(control.isSpacePressed()){
            islander.shoot();
        }



    }

    public void attackInput(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {


            if (islander.getNumWeapon() > 0) {
                MGame.assetManager.get("attack.wav", Sound.class).play();
                hudgame.setWeaponn();
                islander.shoot();
            }


        }
    }


    public void updateEnemy(float delta){
        dog.update(delta);

        for(Enemy ogre:worldCreator.getOgres()){
            ogre.update(delta);
            islander.contacEnemy(ogre);
        }

        for(Enemy sk:worldCreator.getSkeletons()){
            sk.update(delta);
            islander.contacEnemy(sk);
        }

        for(Enemy orc:worldCreator.getOrcs()){
            orc.update(delta);
            islander.contacEnemy(orc);
        }

        for(Enemy und:worldCreator.getUndeads()){
            und.update(delta);
            islander.undeadVsislander((Undead) und);
        }

        for(Enemy dog:worldCreator.getHelldog()){
            dog.update(delta);
        }


        ArrayList<Arrow>removeArrows=new ArrayList<Arrow>();

        for(Arrow arrow:islander.getArrows()){
            arrow.update(delta);
            if(arrow.getDestroy()){
                removeArrows.add(arrow);
            }


            for(Enemy ogre:worldCreator.getOgres()){
                if(arrow.contacEnemy(ogre)) {
                    ogre.setState(Enemy.State.DEAD);
                }
            }

            for(Enemy sk:worldCreator.getSkeletons()){
                if(arrow.contacEnemy(sk)) {
                    sk.setState(Enemy.State.DEAD);
                }
            }

            for(Orc orcs:worldCreator.getOrcs()){
                if(arrow.contacEnemy(orcs)) {
                    orcs.setSt();
                }
            }

            for(Undead unde:worldCreator.getUndeads()){
                if(arrow.contacEnemy(unde)) {
                    unde.setSt(Enemy.State.DEAD);
                }
            }

            for(HellDog dog:worldCreator.getHelldog()){
                if(arrow.contacEnemy(dog)) {
                    dog.setSt(Enemy.State.DEAD);
                }
            }

        }


        islander.getArrows().removeAll(removeArrows);




        for(Enemy flame:worldCreator.getFlames()){
            flame.update(delta);
        }


    }


    public void update(float delta){

        input(delta);
        attackInput(delta);
        islander.update(delta);

        updateEnemy(delta);

        world.step(1.2f/60f,6,2);
        islander.update(delta);
        hudgame.update(delta);

        gamecam.position.x=islander.getBody().getPosition().x;


        gamecam.update();
        renderer.setView(gamecam);

    }



    public void drawEnemys(){
        for(Arrow arrow:islander.getArrows()){
            arrow.render(game.batch);
        }


        for(Enemy enemy:worldCreator.getOgres()){
            enemy.draw(game.batch);
        }

        for(Enemy enemy:worldCreator.getFlames()){
            enemy.draw(game.batch);
        }

        for(Enemy sk:worldCreator.getSkeletons()){
            sk.draw(game.batch);
        }

        for(Enemy orcs:worldCreator.getOrcs()){
            orcs.draw(game.batch);
        }

        for(Enemy unde:worldCreator.getUndeads()){
           unde.draw(game.batch);
        }

        for(Enemy dog:worldCreator.getHelldog()){
           dog.draw(game.batch);
        }

    }


    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        box2DDebugRenderer.render(world,gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        islander.draw(game.batch);
        dog.draw(game.batch);
        drawEnemys();

        game.batch.end();

        game.batch.setProjectionMatrix(hudgame.hudStage.getCamera().combined);
        hudgame.hudStage.draw();
        hudgame.draw();

        if(Gdx.app.getType() == Application.ApplicationType.Android){
            control.draw();
        }


    }


    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height,    true);
        hudgame.hudStage.getViewport().update(width, height);
        control.resize(width,height);

    }

    @Override
    public void dispose() {
        map.dispose();
        world.dispose();
        renderer.dispose();
        box2DDebugRenderer.dispose();
        hudgame.dispose();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


    @Override
    public void show() {

    }



}
