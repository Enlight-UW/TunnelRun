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

    public PauseMenu(){
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        resumeButton = new MenuButtons().MenuTextButton("Resume");
        stage.addActor(resumeButton);
        resumeButton.setPosition((float) 200, (float) 150);


        resumeButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                PauseMenu.this.resumeButton.removeListener(this);
                StateManager.SM.remove();
            }
        });

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
        stage.draw();
    }
}
