
package club.enlight.states;

import java.util.Random;

import club.enlight.ExternalInputListener;
import club.enlight.InputEnum;
import club.enlight.InputManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import com.badlogic.gdx.graphics.GL20;


/**
 * Created by Steve on 10/11/2015.
 */
public class Play extends State implements ExternalInputListener {

    SpriteBatch batch;              //defining all variables and sprites//
    Texture img;
    Texture dogImg;
    Sprite dogSprite;
    Texture city;
    Sprite citySprite;


    Texture run1;                               //running sprites start define
    Sprite runsprite1;
    Texture run2;
    Sprite runsprite2;
    Texture run3;
    Sprite runsprite3;
    Texture run4;
    Sprite runsprite4;
    Texture run5;
    Sprite runsprite5;
    Texture run6;
    Sprite runsprite6;                          //running sprites end defined


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
    float cityy;                                    //used as background part 1 y variable
    float cityx;                                       //used as background part 1 x variable
    float cityy2;                                   //used as background part 2 y variable
    float cityx2;                                       //used as background part 2 y variable
    int spriteCount;                                //finish definig variables


    public Play() {
    }

    @Override
    public void onCreate() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        dogImg = new Texture("Doge/Dog.jpg");
        dogSprite = new Sprite(dogImg);
        city = new Texture("skyline.jpg");
        //city = new Texture("tunnel.png");
        citySprite = new Sprite(city);

        run1 = new Texture("run 1.png");                    //defines sprite file locations
        runsprite1 = new Sprite(run1);
        run2 = new Texture("run 2.png");
        runsprite2 = new Sprite(run2);
        run3 = new Texture("run 3.png");
        runsprite3 = new Sprite(run3);
        run4 = new Texture("run 4.png");
        runsprite4 = new Sprite(run4);
        run5 = new Texture("run 5.png");
        runsprite5 = new Sprite(run5);
        run6 = new Texture("run 6.png");
        runsprite6 = new Sprite(run6);

        InputManager.getInstance().addExternalListener(this);

        spriteCount = 0;                      //sets inital variables
        smile_x = 200;
        smile_y = 10;
        gravity = 800;
        velocity = jump;
        onGround = true;
        jump = 900;

        x = 0;

        cityy = 0;
        cityx = 0;

        cityy2 = cityy;
        cityx2 = 3000;

        smilexdirection = -1;
        smileydirection = -1;
    }


    @Override
    public void onUpdate(float dt) {
        cityx = cityx - 3;                  //makes background images scroll
        cityx2 = cityx2 - 3;

        spriteCount = spriteCount + 1;      // increases sprite counter to determine which sprite to draw
        if (spriteCount == 60) {
            spriteCount = 2;
        }


        if (cityx < -3100) {               //loops city drawing
            cityx = 2900;
        }
        if (cityx2 < -3100) {
            cityx2 = 2900;
        }

        if (smile_x > x - 5) {
            if (smile_x < x + 5) {
                if (smile_y > y - 5) {
                    if (smile_y < y + 5) {
                        x = 10;
                        y = 10;
                    }
                }
            }
        }

        if (smile_x <= 3) {                 //defines on screen boundries for player
            smile_x = 3;
        }

        if (smile_x >= 400) {
            smile_x = 400;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT))
            smile_x += dt * -200;
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))
            smile_x += dt * 200;
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP))                      //up arrow calls jump funtion
            onGround = false;

        if (onGround == false) {                                           //calls jump function
            spriteCount = 71;
            jump(dt);
        }
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onDraw(float dt) {

        //displays game
        batch.begin();

        batch.draw(citySprite, cityx, cityy, 3000, 500);                        //draws background
        batch.draw(citySprite, cityx2, cityy2, 3000, 500);
        //batch.draw(img,smile_x,smile_y,100,100);//

        //starts cycleing through run sprites
        if (spriteCount > 1) {
            if (spriteCount < 12) {
                batch.draw(run1, smile_x, smile_y, 100, 100);
            }
        }
        if (spriteCount > 11) {
            if (spriteCount < 22) {
                batch.draw(run2, smile_x, smile_y, 100, 100);
            }
        }
        if (spriteCount > 21) {
            if (spriteCount < 32) {
                batch.draw(run3, smile_x, smile_y, 100, 100);
            }
        }
        if (spriteCount > 31) {
            if (spriteCount < 42) {
                batch.draw(run4, smile_x, smile_y, 100, 100);
            }
        }
        if (spriteCount > 41) {
            if (spriteCount < 52) {
                batch.draw(run5, smile_x, smile_y, 100, 100);
            }
        }
        if (spriteCount > 51) {
            if (spriteCount < 62) {
                batch.draw(run6, smile_x, smile_y, 100, 100);
            }
        }

        if (spriteCount >= 71) {
            batch.draw(run4, smile_x, smile_y, 100, 100);
        }

        batch.end();
    }

    //jump function
    public void jump(float dt) {
        smile_y = smile_y + velocity * dt;
        velocity = velocity - gravity * 3 * dt;

        if (smile_y <= 0) {
            velocity = jump;
            onGround = true;
            spriteCount = 2;
        }
    }

    @Override
    public void onButtonDown(InputEnum buttonType) {
        if (buttonType == InputEnum.ACTION) {
            onGround = false;
        }
    }

    @Override
    public void onButtonUp(InputEnum buttonType) {
    }

    @Override
    public void onTouchDown(int screenX, int screenY, int pointer, int button) {
    }

    @Override
    public void onTouchUp(int screenX, int screenY, int pointer, int button) {
    }

    @Override
    public void onTouchDragged(int screenX, int screenY, int pointer) {
    }
}











/*

*/