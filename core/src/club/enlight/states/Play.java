
package club.enlight.states;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import com.badlogic.gdx.graphics.GL20;


/**
 * Created by Steve on 10/11/2015.
 */
public class Play extends State {

    SpriteBatch batch;
    Texture img;
    Texture dogImg;
    Sprite dogSprite;
    Texture city;
    Sprite citySprite;
    Texture car;
    Sprite carSprite;

    float smile_x;
    float smile_y;
    float smilexdirection;
    float smileydirection;
    float gravity;
    float velocity;
    boolean onGround;
    float jump;

    float x;
    float y;
    float cityy;
    float cityx;
    float cityy2;
    float cityx2;
    float carx;
    float carDrive;
    boolean carGo;
    float drivespeed;

    Random startcar = new Random();
    Random speed = new Random();


    public Play () {

        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        dogImg = new Texture("Doge/Dog.jpg");
        dogSprite = new Sprite(dogImg);
        city = new Texture("city.jpg");
        citySprite = new Sprite(city);
        car = new Texture("car.jpg");
        carSprite = new Sprite(city);


        smile_x=200;
        smile_y=10;
        gravity=800;
        velocity=jump;
        onGround=true;
        jump=900;
        carDrive=1;
        carGo=false;

        x=0;

        cityy=0;
        cityx=0;
        carx=200;

        cityy2=cityy;
        cityx2=3000;




        smilexdirection = -1;
        smileydirection = -1;

    }

    @Override
    public void render () {
        float dt = Gdx.graphics.getDeltaTime();



        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT))
            smile_x += Gdx.graphics.getDeltaTime() *-200;
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))
            smile_x += Gdx.graphics.getDeltaTime() * 200;
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP))
            onGround=false;

        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN))
            smile_y += Gdx.graphics.getDeltaTime() * -200;

        if (onGround==false){

            jump(dt);
        }

        update(dt);


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        batch.draw(citySprite,cityx,cityy,3000,500);
        batch.draw(citySprite,cityx2,cityy2,3000,500);
        batch.draw(img,smile_x,smile_y,100,100);
        batch.draw(car,carx,5,150,60);

        batch.end();
    }

    public void update(float dt) {

        if (carDrive==1){
            if(startcar.nextInt(35)==1){
                carx=800;
                carGo=true;
                drivespeed=speed.nextInt(9);
            }
        }
        if (carGo== true){
            car();
        }



        cityx=cityx-3;
        cityx2=cityx2-3;




        if (cityx<-3100){
            cityx=2900;
        }
        if (cityx2<-3100){
            cityx2=2900;
        }

        if (smile_x>x-5);{
            if(smile_x<x+5);{
                if(smile_y>y-5);{
                    if(smile_y <y+5);{
                        x=10;
                        y=10;
                    }
                }

            }


        }


        if(smile_x<=3){

            smile_x=3;
        }
        if(smile_x>=400){

            smile_x=400;
        }




    }


    public void jump(float dt) {
        smile_y=smile_y+velocity*dt;
        velocity= velocity-gravity*3*dt;
        if (smile_y<=0){
            velocity=jump;
            onGround=true;
        }

    }

    public void car(){
        carDrive=0;
        carx=carx-drivespeed;
        if (carx<=-150){
            carGo=false;
            carDrive=1;
                    }
    }



}











/*

*/