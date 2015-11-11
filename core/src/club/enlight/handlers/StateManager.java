package club.enlight.handlers;

import club.enlight.states.MainMenu;
import club.enlight.states.PauseMenu;
import club.enlight.states.Play;


import club.enlight.states.State;
import com.badlogic.gdx.Gdx;

import java.util.Stack;

/**
 * Created by sschwebach on 10/9/15.
 */
public class StateManager {
    private static StateManager ourInstance = new StateManager();
    private Stack<State> mStates;


    public static StateManager getInstance() {
        return ourInstance;
    }



    private StateManager() {
        this.mStates = new Stack<State>();
    }

    /**
     * Pushes a new state to the stack, pausing the old one if desired.
     *
     * @param newState      The new State to push to the stack
     * @param pausePrevious True if the previous state should be paused and no longer render. False otherwise
     */
    public void pushState(State newState, boolean pausePrevious) {
        if (pausePrevious) {
            this.mStates.peek().pause();
        }
        this.mStates.push(newState);
    }

    /**
     * Pauses a specified state in the state manager
     *
     * @param stateIndex the index of the state to pause
     */
    public void pauseState(int stateIndex) {
        if (mStates.size() > stateIndex) {
            if (!mStates.get(stateIndex).isPaused()) {
                mStates.get(stateIndex).pause();
            }
        } else {
            throw new IndexOutOfBoundsException("State index (" + stateIndex + ") is out of range of stack (size " + mStates.size() + ")");
        }
    }

    /**
     * Resumes a specified state in the state manager
     *
     * @param stateIndex the index of the state to resume
     */
    public void resumeState(int stateIndex) {
        if (mStates.size() > stateIndex) {
            if (mStates.get(stateIndex).isPaused()) {
                mStates.get(stateIndex).resume();
            }
        } else {
            throw new IndexOutOfBoundsException("State index (" + stateIndex + ") is out of range of stack (size " + mStates.size() + ")");
        }
    }

    /**
     * Pops and returns a state, but does not call the state's destroy code in case you want to use it later.
     * Resumes the next state if paused.
     * The popped state is also not paused unless specifieed
     *
     * @param pausePoppedState Pass in true if the popped state should be paused
     * @return Returns the state that was popped
     */
    public State popState(boolean pausePoppedState) {
        if (mStates.size() > 0) {
            State toReturn = mStates.pop();
            if (this.mStates.peek().isPaused()) {
                this.mStates.peek().resume();
            }
            if (pausePoppedState) {
                toReturn.pause();
            }
            return toReturn;
        }
        return null;
    }

    /**
     * Pops a state off the stack and destroys it (will be garbage collected).
     */
    public void popAndRemoveState() {
        if (mStates.size() > 0) {
            mStates.pop().onDestroy();
            mStates.peek().resume();
        }
    }

    /**
     * Replaces the top state with a new state, destroying the old state.
     *
     * @param newState The state with which to replace the top state.
     */
    public void replaceTopState(State newState) {
        if (mStates.size() > 0) {
            mStates.pop().onDestroy();
            mStates.push(newState);
        } else {
            mStates.push(newState);
        }
    }

    /**
     * Replaces a state in the stack with another, destroying the original
     * h
     *
     * @param newState   The state with which to replace the old state
     * @param stateIndex the index in the stack of the old state
     */
    public void replaceState(State newState, int stateIndex) {
        if (mStates.size() > stateIndex) {
            mStates.get(stateIndex).onDestroy();
            mStates.set(stateIndex, newState);
        } else {
            throw new IndexOutOfBoundsException("State index (" + stateIndex + ") is out of range of stack (size " + mStates.size() + ")");
        }

    }

    /**
     * Draws all the states in the stack, starting from the bottom.
     */
    public void updateAndDraw() {
        float dt = Gdx.graphics.getDeltaTime();
        for (State s : mStates) {
            s.update(dt);
        }
        for (State s : mStates) {
            s.draw(dt);
        }
    }
}
