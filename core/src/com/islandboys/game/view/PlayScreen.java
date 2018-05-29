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

     private Orc dog;


    public PlayScreen(MGame game){
        this.game=game;
        gamecam = new OrthographicCamera();
        gamePort=new FitViewport(GameInfo.V_WIDTH/GameInfo.PIXEL_METER,GameInfo.V_HEIGHT/ GameInfo.PIXEL_METER,gamecam);
        stage=new Stage(gamePort);

        mapLoader=new TmxMapLoader();
        map=mapLoader.load("level1.tmx");
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

        dog=new Orc(this,.32f,.32f);




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
            islander.body.setLinearVelocity(new Vector2(2, islander.body.getLinearVelocity().y));
        else if (control.isLeftPressed())
            islander.body.setLinearVelocity(new Vector2(-2, islander.body.getLinearVelocity().y));
        else
            islander.body.setLinearVelocity(new Vector2(0, islander.body.getLinearVelocity().y));
        if (control.isUpPressed() && islander.body.getLinearVelocity().y == 0)
            islander.body.applyLinearImpulse(new Vector2(0, 4f), islander.body.getWorldCenter(), true);


    }

    public void attackInput(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.A)){

            if(islander.getNumWeapon()>0)
            MGame.assetManager.get("attack.wav",Sound.class).play();
            hudgame.setWeaponn();
        }




    }


    public void handleInput(float delta){


        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) &&  islander.body.getLinearVelocity().y == 0){
                islander.body.applyLinearImpulse(new Vector2(0,4f),islander.body.getWorldCenter(),true);

        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && islander.body.getLinearVelocity().x<=2){
            islander.body.applyLinearImpulse(new Vector2(0.4f,0),islander.body.getWorldCenter(),true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && islander.body.getLinearVelocity().x>=-2){
            islander.body.applyLinearImpulse(new Vector2(-0.4f,0),islander.body.getWorldCenter(),true);
        }

    }


    public void update(float delta){

        input(delta);
        attackInput();
        islander.update(delta);
        dog.update(delta);

        for(Enemy ogre:worldCreator.getOgres()){
            ogre.update(delta);
        }

        for(Enemy flame:worldCreator.getFlames()){
            flame.update(delta);
        }

        world.step(1/60f,6,2);
        islander.update(delta);
        hudgame.update(delta);

        gamecam.position.x=islander.body.getPosition().x;


        gamecam.update();
        renderer.setView(gamecam);

    }

    @Override
    public void show() {


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
        for(Enemy enemy:worldCreator.getOgres()){
            enemy.draw(game.batch);
        }

        for(Enemy enemy:worldCreator.getFlames()){
            enemy.draw(game.batch);
        }

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
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        world.dispose();
        renderer.dispose();
        box2DDebugRenderer.dispose();
        hudgame.dispose();

    }
}
