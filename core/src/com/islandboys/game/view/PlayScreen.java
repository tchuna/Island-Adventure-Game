package com.islandboys.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
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
import com.islandboys.game.model.GameInfo;
import com.islandboys.game.model.Hud;
import com.islandboys.game.model.Islander;

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


    public PlayScreen(MGame game){
        this.game=game;
        gamecam = new OrthographicCamera();
        gamePort=new FitViewport(GameInfo.V_WIDTH/GameInfo.PIXEL_METER,GameInfo.V_HEIGHT/ GameInfo.PIXEL_METER,gamecam);
        stage=new Stage(gamePort);
        hudgame=new Hud(game.batch, GameInfo.ISLANDER_1);

        mapLoader=new TmxMapLoader();
        map=mapLoader.load("level1.tmx");
        renderer=new OrthogonalTiledMapRenderer(map,1/GameInfo.PIXEL_METER);

        gamecam.position.set(gamePort.getScreenWidth()/2,gamePort.getScreenHeight()/2,0);

        world=new World(new Vector2(0,-10),true);
        box2DDebugRenderer=new Box2DDebugRenderer();
        worldCreator=new Box2DWorldCreator(world,map);
        islander=new Islander(world,this);





    }





    public void handleInput(float delta){

        if(Gdx.input.isTouched()){
            islander.body.applyLinearImpulse(new Vector2(0.1f,0),islander.body.getWorldCenter(),true);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            islander.body.applyLinearImpulse(new Vector2(0,4f),islander.body.getWorldCenter(),true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && islander.body.getLinearVelocity().x<=2){
            islander.body.applyLinearImpulse(new Vector2(0.1f,0),islander.body.getWorldCenter(),true);
        }


        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && islander.body.getLinearVelocity().x>=-2){
            islander.body.applyLinearImpulse(new Vector2(-0.1f,0),islander.body.getWorldCenter(),true);
        }




    }
    public void update(float delta){

        handleInput(delta);
        islander.update(delta);

        world.step(1/60f,6,2);
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
        game.batch.end();

        game.batch.setProjectionMatrix(hudgame.hudStage.getCamera().combined);
        hudgame.hudStage.draw();
        hudgame.draw();



    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height,    true);
        hudgame.hudStage.getViewport().update(width, height);
        // gamePort.update(width,height);

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
