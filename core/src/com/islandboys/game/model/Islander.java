package com.islandboys.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.islandboys.game.view.PlayScreen;

import org.omg.CORBA.UNKNOWN;

import java.util.ArrayList;

public class Islander extends Sprite {

    public enum State {IDLE, JUMPING, RUNNING, FALLING, DEAD}


    private Body body;
    private Texture idle;
    private Texture jump;
    private Texture running;
    private Texture fail;
    private TextureRegion standImage;
    private int countHurt = 0;



    private boolean win=false;
    private int  time=0;



    private boolean key=false;


    private ArrayList<Arrow> arrows;

    private State currentState;
    private State previousState;

    private Animation islanderRunning;
    private Animation islanderIdle;
    private Animation islanderJump;
    private Animation islanderFailng;
    private PlayScreen screen;


    private float stateTimer;
    private boolean directionRigh;
    public boolean isInAir;

    private boolean isAlive = true;
    private int numWeapon = 8;
    private int live = 0;
    private int coins = 0;
    private int score = 0;
    private float width;
    private float heigth;


    public Islander(PlayScreen screen) {
        this.world = screen.getWorld();
        this.screen = screen;

        width = 12 / GameInfo.PIXEL_METER;
        heigth = 13 / GameInfo.PIXEL_METER;
        idle = new Texture("idle.png");
        running = new Texture("runing.png");
        jump = new Texture("jump.png");
        fail = new Texture("failling.png");

        currentState = State.IDLE;
        previousState = State.IDLE;
        directionRigh = true;
        stateTimer = 0;
        arrows = new ArrayList<Arrow>(numWeapon);

        createSprites();
        defineIslander();


    }



    public boolean getWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public ArrayList<Arrow> getArrows() {
        return arrows;
    }

    public int getLive() {
        return this.live;
    }

    public int getNumWeapon() {
        return this.numWeapon;
    }

    public void setKey() {
        this.key = true;
    }

    public boolean getAlive() {
        return this.isAlive;
    }

    public int getCoins() {
        return this.coins;
    }

    public boolean getKey(){
        return key;
    }


    public void shoot() {
        arrows.add(new Arrow(screen, this.getX(), this.getY(), this.getDirectionRigh()));
    }


    public World getWorld() {
        return world;
    }

    private World world;

    public Body getBody() {
        return body;
    }

    public void setCoins(int coins) {
        this.coins += coins;
    }

    public void setLive(int live) {
        this.live = live;
    }

    public void setNumWeapon(int weapon) {
        this.numWeapon -= weapon;
        if (numWeapon <= 0) {
            numWeapon = 0;
        }
    }

    public void setWeapon(int weapon){
        this.numWeapon=weapon;
    }


    public void setIsAlive() {
        this.isAlive = false;
    }


    public void setScoree(int score) {
        this.score = score;

    }

    public void incLive(int v) {
        this.live+=v;
    }


    public void createSprites() {

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 0; i < 9; i++) {
            frames.add(new TextureRegion(idle, i * 24, 0, 24, 36));
        }

        islanderIdle = new Animation(0.2f, frames);
        frames.clear();


        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(running, i * 25, -3, 25, 36));
        }
        islanderRunning = new Animation(0.2f, frames);
        frames.clear();


        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(jump, i * 26, 0, 26, 36));
        }
        islanderJump = new Animation(0.3f, frames);
        frames.clear();

        for (int i = 0; i < 2; i++) {
            frames.add(new TextureRegion(fail, i * 25, 0, 25, 36));
        }
        islanderFailng = new Animation(0.2f, frames);
        frames.clear();

        setBounds(0, 0, 25 / GameInfo.PIXEL_METER, 36 / GameInfo.PIXEL_METER);


    }


    public TextureRegion getFrames(float delta) {

        TextureRegion region;
        currentState = getState();

        switch (currentState) {
            case JUMPING:
                region = (TextureRegion) islanderJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = (TextureRegion) islanderRunning.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
                region = (TextureRegion) islanderFailng.getKeyFrame(stateTimer, true);
                break;
            default:
                region = (TextureRegion) islanderIdle.getKeyFrame(stateTimer, true);
                break;

        }

        if ((body.getLinearVelocity().x < 0 || !directionRigh) && !region.isFlipX()) {
            region.flip(true, false);
            directionRigh = false;

        } else if ((body.getLinearVelocity().x > 0 || directionRigh) && region.isFlipX()) {
            region.flip(true, false);
            directionRigh = true;
        }

        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;

        return region;


    }


    public State getState() {
        if (body.getLinearVelocity().y > 0 || (body.getLinearVelocity().y < 0 && previousState == State.JUMPING)) {
            return State.JUMPING;
        } else if (body.getLinearVelocity().y < 0) {
            return State.FALLING;


        } else if (body.getLinearVelocity().x == 0) {
            return State.IDLE;
        } else if (body.getLinearVelocity().x != 0) {
            return State.RUNNING;
        }

        return State.DEAD;
    }

    public void update(float delta) {
        setPosition(body.getPosition().x - getWidth() / 2.2f, (body.getPosition().y - getHeight() / 3f));
        setRegion(getFrames(delta));

        if(this.getY()<0){
            this.setIsAlive();
        }
        if(this.getLive()>=4){
            this.setIsAlive();
        }



    }

    public void defineIslander() {
        BodyDef bdf = new BodyDef();
        bdf.position.set(200 / GameInfo.PIXEL_METER, 48 / GameInfo.PIXEL_METER);
        bdf.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdf);

        contactSensore();

    }


    public void contactSensore() {

        FixtureDef fdef = new FixtureDef();


        CircleShape shape = new CircleShape();
        shape.setRadius(10 / GameInfo.PIXEL_METER);

        fdef.filter.categoryBits = GameInfo.ISLANDER_BIT;

        fdef.filter.maskBits = GameInfo.BRICKS_BIT | GameInfo.COINS_BIT | GameInfo.FIRE_BIT |
                GameInfo.SPIKE_BIT | GameInfo.GROUND_BIT |GameInfo.KEY_BIT|GameInfo.DOR_BIT;

        fdef.shape = shape;
        body.createFixture(fdef);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-13 / GameInfo.PIXEL_METER, 22 / GameInfo.PIXEL_METER), new Vector2(13 / GameInfo.PIXEL_METER, 22 / GameInfo.PIXEL_METER));
        fdef.shape = head;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("head");


        EdgeShape foot = new EdgeShape();
        foot.set(new Vector2(-9 / GameInfo.PIXEL_METER, -11 / GameInfo.PIXEL_METER), new Vector2(9 / GameInfo.PIXEL_METER, -11 / GameInfo.PIXEL_METER));
        fdef.shape = foot;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("foot");

        EdgeShape fbody = new EdgeShape();
        fbody.set(new Vector2(11 / GameInfo.PIXEL_METER, -11 / GameInfo.PIXEL_METER), new Vector2(11 / GameInfo.PIXEL_METER, 11 / GameInfo.PIXEL_METER));
        fdef.shape = fbody;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("fbody");


        EdgeShape bbody = new EdgeShape();
        bbody.set(new Vector2(-12 / GameInfo.PIXEL_METER, -11 / GameInfo.PIXEL_METER), new Vector2(-12 / GameInfo.PIXEL_METER, 11 / GameInfo.PIXEL_METER));
        fdef.shape = bbody;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("bbody");

    }



    public boolean orcVsislander(Orc enemy) {

        float result = this.getX() - enemy.getEnemyBody().getPosition().x;

        if (isAlive) {

            if(enemy.getState()!= Enemy.State.DEAD ) {
                if (result < 0.4 && result > -0.6 && screen.getIslander().getY() <= enemy.getEnemyBody().getPosition().y) {
                    countHurt++;
                    if (countHurt == 42) {
                        live++;
                        countHurt = 0;
                    }

                    if (live >= 4) {
                        isAlive = false;
                    }

                    return true;
                }
            }

        }
        return false;
    }


    public boolean ogreVsislander(Ogre enemy) {

        float result = this.getX() - enemy.getEnemyBody().getPosition().x;

        if (isAlive) {
            if(enemy.getState()!= Enemy.State.DEAD) {
                if (result < 0.4 && result > -0.6 && screen.getIslander().getY() <= enemy.getEnemyBody().getPosition().y) {
                    countHurt++;
                    System.out.println("Kileeeeeeed");
                    if (countHurt == 42) {
                        live++;
                        countHurt = 0;
                    }

                    if (live >= 4) {
                        isAlive = false;
                    }

                    return true;
                }
            }

        }
        return false;
    }


    public boolean undeadVsislander(Undead enemy) {

        float result = this.getX() - enemy.getEnemyBody().getPosition().x;

        if (isAlive) {
            if(enemy.getState()!= Enemy.State.DEAD) {
                if (result < 0.3 && result > -0.6 && screen.getIslander().getY() <= enemy.getEnemyBody().getPosition().y) {
                    countHurt++;
                    System.out.println("Kileeeeeeed");
                    if (countHurt == 50) {
                        live++;
                        countHurt = 0;
                    }

                    if (live >= 4) {
                        isAlive = false;
                    }

                    return true;
                }
            }

        }
        return false;
    }


    public boolean skeletondVsislander(Skeleton enemy) {

        float result = this.getX() - enemy.getEnemyBody().getPosition().x;

        if (isAlive) {
            if(enemy.getState()!= Enemy.State.DEAD){
                if (result < 0.4 && result > -0.7 && screen.getIslander().getY() <= enemy.getEnemyBody().getPosition().y) {
                    countHurt++;
                    System.out.println("Kileeeeeeed");
                    if (countHurt == 40) {
                        live++;
                        countHurt = 0;
                    }

                    if (live >= 4) {
                        isAlive = false;
                    }

                    return true;
                }
            }


        }
        return false;
    }




    public void contacEnemy(Enemy enemy){

        if (enemy instanceof Ogre){
            ogreVsislander((Ogre)enemy);
        }else if(enemy instanceof Orc){
            orcVsislander((Orc) enemy);
        }else if(enemy instanceof Undead){
            undeadVsislander((Undead) enemy);
        }else if(enemy instanceof Skeleton){
            skeletondVsislander((Skeleton) enemy);
        }

    }

    public boolean getDirectionRigh(){
        return directionRigh;
    }









}
