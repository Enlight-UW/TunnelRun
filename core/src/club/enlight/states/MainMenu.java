package club.enlight.states;

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
    TextButton.TextButtonStyle textButtonStyle;
    /*
    TextButton.TextButtonStyle textButtonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;
    //*/
    public MainMenu()
    {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        startButton  = new MenuButtons().MenuTextButton("Play");
        stage.addActor(startButton);


        startButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                StateManager.SM.remove();
            }
        });

    }

    @Override
    public void update(float dt)
    {
        if(false){
            StateManager.SM.replace(2);
        }
    }

    @Override
    public void render()
    {

        stage.draw();
    }
}
