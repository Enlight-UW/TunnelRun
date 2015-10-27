package club.enlight.handlers;

import club.enlight.states.MainMenu;
import club.enlight.states.PauseMenu;
import club.enlight.states.State;
import com.badlogic.gdx.Gdx;

import java.util.Stack;

public class StateManager {

    public final int MAIN_MENU = 0;
    public final int PLAY = 1;
    public final int PAUSE = 2;

    private Stack<State> stack;

    public static StateManager SM = new StateManager();

    private StateManager()
    {
        stack = new Stack<State>();
        this.put(MAIN_MENU);
    }

    public void put(int state)
    {
        stack.push(this.getState(state));
    }

    public void replace(int state)
    {
        this.remove();
        this.put(state);
    }

    public void remove()
    {
        stack.pop();
    }

    public void update()
    {
        float dt = Gdx.graphics.getDeltaTime();

        stack.peek().update(dt);

        for(int i = 0; i < stack.size(); i++)
        {
            stack.get(i).render();
        }
    }

    private State getState(int state)
    {
        if(state == MAIN_MENU)
        {
            return new MainMenu();
        }
        else if(state == PLAY)
        {

        }
        else if(state == PAUSE)
        {
            return new PauseMenu();
        }

        return null;
    }
}
