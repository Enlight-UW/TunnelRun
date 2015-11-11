package club.enlight.states;

import club.enlight.assets.MenuButtons;
import club.enlight.handlers.StateManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PauseMenu extends State {
    Stage stage;
    TextButton resumeButton;
    TextButton.TextButtonStyle textButtonStyle;

    public PauseMenu() {

    }

    @Override
    public void onCreate() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        resumeButton = new MenuButtons().MenuTextButton("Resume");
        stage.addActor(resumeButton);
        resumeButton.setPosition((float) 200, (float) 150);


        resumeButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                PauseMenu.this.resumeButton.removeListener(this);
                StateManager.getInstance().popAndRemoveState();
            }
        });
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
