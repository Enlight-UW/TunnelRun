package club.enlight.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;


/**
 * Created by Kyle on 10/23/2015.
 */
public class Obstacles extends  Render {

    private float x;
    private float y;
    private float width;
    private float height;
    private ShapeRenderer obstacleImage;
    private Random rng = new Random();

    public Obstacles(){
        // obstacles start from the top
        y = Gdx.graphics.getHeight();
        // generate random x coordinate for obstacles
        x = rng.nextFloat(); // x coordinate verification needed (out of screen)
        width = 20;
        height = 20;
        obstacleImage = new ShapeRenderer();
    }



    public void update(float dt){
        //x += 600.f * dt;
        y -= 600.f * dt;

//        if(x > Gdx.graphics.getWidth())
//        {
//            x = 0.f;
//        }

        if(y < 0)
        {
            y = Gdx.graphics.getHeight(); // start over from the top
        }
    }

    public void render(){
        // draw the obstacle image
        obstacleImage.begin();
        obstacleImage.setColor(Color.CYAN);
        obstacleImage.rect(x, y, width, height);// x coordinate needs verification
        obstacleImage.end();
    }
}