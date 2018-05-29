package com.islandboys.game.model;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.islandboys.game.MGame;
import com.islandboys.game.view.PlayScreen;

public class Orc extends Enemy {

    private float stateTime;
    private Animation idleAnimation, runAnimation, attackAnimation, deadAnimation;
    private Array<TextureRegion> frames;
    private Texture idle, run, attack, dead;
    private PolygonShape shape;
    Enemy.State state;
    int count = 0;

    public Orc(PlayScreen screen, float x_position, float y_position) {
        super(screen, x_position, y_position);
    }


    @Override
    protected void defineEnemyBody(float x_position, float y_position) {
        this.state = State.DEAD;

        BodyDef bdf = new BodyDef();
        bdf.position.set(x_position, y_position);
        bdf.type = BodyDef.BodyType.DynamicBody;
        enemyBody = world.createBody(bdf);

        FixtureDef fdef = new FixtureDef();

        shape = new PolygonShape();
        shape.setAsBox(12 / GameInfo.PIXEL_METER, 21 / GameInfo.PIXEL_METER);
        fdef.filter.categoryBits = GameInfo.ENEMY_BIT;

        fdef.filter.maskBits = GameInfo.GROUND_BIT | GameInfo.ISLANDER_BIT | GameInfo.ENEMY_BIT;

        fdef.shape = shape;
        enemyBody.createFixture(fdef);

        creatSprite(shape);
    }


    protected void creatSprite(PolygonShape shape) {
        stateTime = 0;

        idle = new Texture("idle_Oc.png");
        run = new Texture("run_Oc.png");
        attack = new Texture("attack_Oc.png");
        dead = new Texture("dead_Oc.png");
        Array<TextureRegion> frames = new Array<TextureRegion>();


        for (int i = 0; i < 10; i++) {
            frames.add(new TextureRegion(idle, i * 32, 0, 32, 32));
        }

        idleAnimation = new Animation(0.1f, frames);
        frames.clear();

        for (int i = 0; i <8; i++) {
            frames.add(new TextureRegion(run, i * 32, 0, 32, 32));
        }
        runAnimation = new Animation(0.2f, frames);
        frames.clear();


        for (int i = 0; i < 10; i++) {
            frames.add(new TextureRegion(attack, i * 32, 0, 32, 32));
        }

        attackAnimation = new Animation(0.09f, frames);
        frames.clear();


        for (int i = 0; i < 8; i++) {
            frames.add(new TextureRegion(dead, i * 32, 0, 32, 32));
        }

        deadAnimation = new Animation(0.2f, frames);
        frames.clear();


    }


    public TextureRegion getFrames(float delta) {

        if (state == Enemy.State.IDLE) {
            setBounds(getX(), getY(), 60 / GameInfo.PIXEL_METER, 44 / GameInfo.PIXEL_METER);
            return (TextureRegion) idleAnimation.getKeyFrame(stateTime, true);
        }

        if (state == State.RUNNING) {
            setBounds(getX(), getY(), 60 / GameInfo.PIXEL_METER, 44 / GameInfo.PIXEL_METER);
            return (TextureRegion) runAnimation.getKeyFrame(stateTime, true);
        }

        if (state == State.DEAD) {
            setBounds(getX(), getY(), 60 / GameInfo.PIXEL_METER, 44 / GameInfo.PIXEL_METER);
            return (TextureRegion) deadAnimation.getKeyFrame(stateTime, true);
        }


        setBounds(getX(), getY(), 60 / GameInfo.PIXEL_METER, 44 / GameInfo.PIXEL_METER);
        return (TextureRegion) attackAnimation.getKeyFrame(stateTime, true);

    }


    @Override
    public void update(float delta) {

        stateTime += delta;

        setRegion(getFrames(delta));

        if (Enemy.State.IDLE == state) {
            setPosition(enemyBody.getPosition().x - getWidth() / 1.9f, (enemyBody.getPosition().y - getHeight() / 2f));
        }

        if (state == Enemy.State.RUNNING) {
            setPosition(enemyBody.getPosition().x - getWidth() / 1.9f, (enemyBody.getPosition().y - getHeight() / 2f));
        }

        if (State.ATTACK == state) {
            setPosition(enemyBody.getPosition().x - getWidth() / 2.2f, (enemyBody.getPosition().y - getHeight() / 2f));
        }

        if (State.DEAD == state) {
            setPosition(enemyBody.getPosition().x - getWidth() / 2f, (enemyBody.getPosition().y - getHeight() / 2f));
        }

    }
}