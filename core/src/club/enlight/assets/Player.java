package club.enlight.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


/**
 * Created by Kyle on 10/13/2015.
 */
public class Player extends Render {
    private float height;
    private float width = 10;
    private ShapeRenderer playerImage;
    private float x;
    private float y;
    private float velocity;
    private float gravity;
    private float time;

    public Player(){
        height = 0;
        x = Gdx.graphics.getWidth() / 2;
        y = 0;
        playerImage = new ShapeRenderer();
        velocity = 9.8f;
        gravity = -9.8f;
        //time =

    }

    public void render(){
        // draw the player image
        playerImage.begin();
        playerImage.setColor(Color.CYAN);
        playerImage.rect(x, y, width, height);
        playerImage.end();
    }

    public void update(float dt){

    }

    public void jump(){
        while(inAir()){
            if(isRising()){
                y = velocity * time + (1/2 * gravity * time *time);
            }
            else{
                //y =
            }
        }
    }

    private boolean inAir(){
        return (y != 0);
    }

    private boolean isRising(){
        return false;
    }
}