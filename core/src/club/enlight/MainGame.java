package club.enlight;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture dogImg;
	Sprite dogSprite;
	float x;
	float y;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		dogImg = new Texture("Doge/dog.jpg");
		dogSprite = new Sprite(dogImg);
		x = 0;
		y = 0;
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();

		update(dt);

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(dogSprite, x, y);
		batch.end();
	}

	public void update(float dt)
	{
		x += dt * 50;
		y += dt * 50;

		System.out.println(dt);

		if(x >= Gdx.graphics.getWidth())
		{
			x = 0.f;
		}

		if(y >= Gdx.graphics.getHeight())
		{
			y = 0.f;
		}
	}
}
