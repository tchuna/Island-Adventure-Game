package com.islandboys.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.islandboys.game.model.InteractiveTileObject;

public class WorldContactListener implements ContactListener {


    public  void headContact(Contact contact){

        Fixture fixA=contact.getFixtureA();
        Fixture fixB=contact.getFixtureB();

        if(fixA.getUserData()=="head"|| fixA.getUserData()=="head"){
            Fixture head=fixA.getUserData()=="head"? fixA:fixB;
            Fixture object=head==fixA? fixB:fixA;


            if(object.getUserData()!=null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveTileObject)object.getUserData()).onHeadHit();
            }
        }



    }


    public void footContact(Contact contact){

        Fixture fixA=contact.getFixtureA();
        Fixture fixB=contact.getFixtureB();

        if(fixA.getUserData()=="foot"|| fixA.getUserData()=="foot"){
            Fixture head=fixA.getUserData()=="foot"? fixA:fixB;
            Fixture object=head==fixA? fixB:fixA;


            if(object.getUserData()!=null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveTileObject)object.getUserData()).onHeadHit();
            }
        }




    }




    public void fbodyContact(Contact contact){

        Fixture fixA=contact.getFixtureA();
        Fixture fixB=contact.getFixtureB();

        if(fixA.getUserData()=="fbody"|| fixA.getUserData()=="fbody"){
            Fixture head=fixA.getUserData()=="fbody"? fixA:fixB;
            Fixture object=head==fixA? fixB:fixA;


            if(object.getUserData()!=null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveTileObject)object.getUserData()).onHeadHit();
            }
        }


    }

    public void bbodyContact(Contact contact){

        Fixture fixA=contact.getFixtureA();
        Fixture fixB=contact.getFixtureB();

        if(fixA.getUserData()=="bbody"|| fixA.getUserData()=="bbody"){
            Fixture head=fixA.getUserData()=="bbody"? fixA:fixB;
            Fixture object=head==fixA? fixB:fixA;


            if(object.getUserData()!=null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveTileObject)object.getUserData()).onHeadHit();
            }
        }


    }

    @Override
    public void beginContact(Contact contact) {
        headContact(contact);
        footContact(contact);
        fbodyContact(contact);
         bbodyContact(contact);


    }

    @Override
    public void endContact(Contact contact) {


    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
