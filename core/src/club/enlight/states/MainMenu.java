package club.enlight.states;

import club.enlight.InputManager;
import club.enlight.assets.MenuButtons;
import club.enlight.handlers.StateManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenu extends State {
    Stage stage;
    TextButton startButton;

    public MainMenu() {
    }

    @Override
    public void onCreate() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        startButton = new MenuButtons().MenuTextButton("Play");
        stage.addActor(startButton);
        startButton.setPosition((float) 300, (float) 200);

        ClickListener listener = new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                StateManager.getInstance().replaceTopState(new Play());
                Gdx.input.setInputProcessor(InputManager.getInstance());
            }
        };

        startButton.addListener(listener);
    }

    @Override
    public void onDraw(float dt) {
        stage.draw();
    }

    @Override
    public void onUpdate(float dt) {

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
}
