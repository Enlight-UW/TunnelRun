package club.enlight;

import club.enlight.handlers.StateManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends ApplicationAdapter implements ExternalInputListener{
	
	@Override
	public void create () {
		InputManager.getInstance().addExternalListener(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		StateManager.SM.update();
	}

	@Override
	public void onButtonDown(InputEnum buttonType) {

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
