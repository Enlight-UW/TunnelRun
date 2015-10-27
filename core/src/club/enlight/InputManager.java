package club.enlight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

/**
 * Created by sschwebach on 4/18/15.
 * This class interprets all inputs based on which mode we're doing and what input devices we have.
 * For example, when there are two controllers connected it routes each to one player.
 * When there is one controller, it uses the keyboard for player 1 and the controller for player 2
 * Online mode is a bit more complicated. First, we need to know if we've been assigned player 1 or 2
 * Once we know, that player uses local input and the other player uses input from a web caller (if we get to it)
 * Lol we totally didn't get to it
 */
public class InputManager implements InputProcessor {
    public static InputManager instance = new InputManager();

    private ArrayList<ExternalInputListener> mListeners;

    boolean player1Keyboard = false; // true if player 1 is using a keyboard
    Controller player1;
    // Current button states and stuff
    boolean player1Start, player1DLeft, player1DRight, player1DUp, player1DDown, player1A, player1B, player1X;
    public Vector2 player1LeftStick, player1RightStick;

    private InputManager() {
        Array<Controller> controllers = Controllers.getControllers();
        mListeners = new ArrayList<ExternalInputListener>();
        System.out.println("Found " + controllers.size + " controllers");
        // See how many controllers we have
        if (controllers.size == 0) {
            // no controller. bind player to keyboard
            player1Keyboard = true;
            Gdx.input.setInputProcessor(this);
            // Online mode only
        } else if (controllers.size >= 1) {
            // 1 controller or more. Bind player to first controller
            player1Keyboard = false;
            // Local or online, player 1 is on keyboard for local
            player1 = controllers.get(0);
            if (player1.getName().equals(ControllerButtons.XboxName)) {
                System.out.println("Controller name or something " + controllers.get(0).getName());
            }
            Gdx.input.setInputProcessor(this);
        }
        // initialize shit
        player1LeftStick = new Vector2();
        player1RightStick = new Vector2();
    }

    public static InputManager getInstance() {
        return instance;
    }

    public void addExternalListener(ExternalInputListener listener){
        this.mListeners.add(listener);
    }

    public void removeAllExternalListeners(){
        this.mListeners.clear();
    }

    public void removeExternalListener(ExternalInputListener listener){
        this.mListeners.remove(listener);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (player1Keyboard) {
            switch (keycode) {
                case Input.Keys.ESCAPE:
                    // Escape sets start to true
                    player1Start = true;
                    for (ExternalInputListener l : mListeners){
                        l.onButtonDown(InputEnum.PAUSE);
                    }
                    break;
                case Input.Keys.W:
                    // W sets Y to -1.0
                    player1LeftStick.y += 1.0f;
                    for (ExternalInputListener l : mListeners){
                        l.onButtonDown(InputEnum.UP);
                    }
                    break;
                case Input.Keys.S:
                    // S sets Y to 1.0
                    player1LeftStick.y += -1.0f;
                    for (ExternalInputListener l : mListeners){
                        l.onButtonDown(InputEnum.DOWN);
                    }
                    break;
                case Input.Keys.A:
                    // A sets X to -1.0
                    player1LeftStick.x += -1.0f;
                    for (ExternalInputListener l : mListeners){
                        l.onButtonDown(InputEnum.LEFT);
                    }
                    break;
                case Input.Keys.D:
                    // D sets X to 1.0
                    player1LeftStick.x += 1.0f;
                    for (ExternalInputListener l : mListeners){
                        l.onButtonDown(InputEnum.RIGHT);
                    }
                    break;
                case Input.Keys.UP:
                    // Arrow up sets Y to -1.0
                    player1RightStick.y += -1.0f;
                    player1DUp = true;
                    for (ExternalInputListener l : mListeners){
                        l.onButtonDown(InputEnum.UP);
                    }
                    break;
                case Input.Keys.DOWN:
                    // Arrow down sets Y to 1.0
                    player1RightStick.y += 1.0f;
                    player1DDown = true;
                    for (ExternalInputListener l : mListeners){
                        l.onButtonDown(InputEnum.DOWN);
                    }
                    break;
                case Input.Keys.LEFT:
                    // Arrow left sets X to -1.0, left to true
                    player1RightStick.x += -1.0f;
                    player1DLeft = true;
                    for (ExternalInputListener l : mListeners){
                        l.onButtonDown(InputEnum.LEFT);
                    }
                    break;
                case Input.Keys.RIGHT:
                    // Arrow right sets X to 1.0, right to true
                    player1RightStick.x += 1.0f;
                    player1DRight = true;
                    for (ExternalInputListener l : mListeners){
                        l.onButtonDown(InputEnum.RIGHT);
                    }
                    break;
                case Input.Keys.SPACE:
                    // Enter sets a to true
                    player1A = true;
                    for (ExternalInputListener l : mListeners){
                        l.onButtonDown(InputEnum.ACTION);
                    }
                    break;
            }

        }

        checkForFloatingPointError(player1LeftStick);
        checkForFloatingPointError(player1RightStick);

        return false;
    }

    public void checkForFloatingPointError(Vector2 stick) {
        float error = 0.15f;

        if(Math.abs(stick.x) < error) {
            stick.x = 0.0f;
        }

        if(Math.abs(stick.y) < error) {
            stick.y = 0.0f;
        }
    }

    @Override
    public boolean keyUp(int keycode) {
        if (player1Keyboard) {
            switch (keycode) {
                case Input.Keys.ESCAPE:
                    // Escape sets start to true
                    player1Start = false;
                    for (ExternalInputListener l : mListeners){
                        l.onButtonUp(InputEnum.PAUSE);
                    }
                    break;
                case Input.Keys.W:
                    // W sets Y to -1.0
                    player1LeftStick.y -= 1.0f;
                    for (ExternalInputListener l : mListeners){
                        l.onButtonUp(InputEnum.UP);
                    }
                    break;
                case Input.Keys.S:
                    // S sets Y to 1.0
                    player1LeftStick.y -= -1.0f;
                    for (ExternalInputListener l : mListeners){
                        l.onButtonUp(InputEnum.DOWN);
                    }
                    break;
                case Input.Keys.A:
                    // A sets X to -1.0
                    player1LeftStick.x -= -1.0f;
                    for (ExternalInputListener l : mListeners){
                        l.onButtonUp(InputEnum.LEFT);
                    }
                    break;
                case Input.Keys.D:
                    // D sets X to 1.0
                    player1LeftStick.x -= 1.0f;
                    for (ExternalInputListener l : mListeners){
                        l.onButtonUp(InputEnum.RIGHT);
                    }
                    break;
                case Input.Keys.UP:
                    // Arrow up sets Y to -1.0
                    player1RightStick.y -= -1.0f;
                    player1DUp = false;
                    for (ExternalInputListener l : mListeners){
                        l.onButtonUp(InputEnum.UP);
                    }
                    break;
                case Input.Keys.DOWN:
                    // Arrow down sets Y to 1.0
                    player1RightStick.y -= 1.0f;
                    player1DDown = false;
                    for (ExternalInputListener l : mListeners){
                        l.onButtonUp(InputEnum.DOWN);
                    }
                    break;
                case Input.Keys.LEFT:
                    // Arrow left sets X to -1.0, left to true
                    player1RightStick.x -= -1.0f;
                    player1DLeft = false;
                    for (ExternalInputListener l : mListeners){
                        l.onButtonUp(InputEnum.LEFT);
                    }
                    break;
                case Input.Keys.RIGHT:
                    // Arrow right sets X to 1.0, right to true
                    player1RightStick.x -= 1.0f;
                    player1DRight = false;
                    for (ExternalInputListener l : mListeners){
                        l.onButtonUp(InputEnum.RIGHT);
                    }
                    break;
                case Input.Keys.SPACE:
                    // Enter sets a to true
                    player1A = false;
                    for (ExternalInputListener l : mListeners){
                        l.onButtonUp(InputEnum.ACTION);
                    }
                    break;
            }

        }

        checkForFloatingPointError(player1LeftStick);
        checkForFloatingPointError(player1RightStick);

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (ExternalInputListener l : mListeners){
            l.onTouchDown(screenX, Gdx.graphics.getHeight() - screenY, pointer, button);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for (ExternalInputListener l : mListeners){
            l.onTouchUp(screenX, Gdx.graphics.getHeight() - screenY, pointer, button);
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        for (ExternalInputListener l : mListeners){
            l.onTouchDragged(screenX, Gdx.graphics.getHeight() - screenY, pointer);
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }



}