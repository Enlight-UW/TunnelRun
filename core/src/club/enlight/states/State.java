package club.enlight.states;

import com.badlogic.gdx.Gdx;

/**
 * Created by sschwebach on 10/9/15.
 */
public abstract class State {
    // Private boolean indicating if the state is "paused". Use the public method to access
    private boolean mPaused;
    // If we want to have tags for our states, such as "menu state". Not currently used.
    private String mTag;

    /**
     * Creates a base state with tag ""
     */
    public State() {
        this.mTag = "";
        this.onCreate();
    }

    /**
     * Creates a state with the specified tag
     * @param tag The tag for the state, such as "main game" or "menu"
     */
    public State(String tag) {
        this.mTag = tag;
        this.onCreate();
    }

    /**
     * Pauses the state. A paused state will no longer update, but will still render.
     */
    public void pause() {
        this.mPaused = true;
        this.onPause();
    }

    /**
     * Resumes the current state, causing it to begin updating again
     */
    public void resume() {
        this.mPaused = false;
        this.onResume();
    }

    /**
     * Method that updates the state called by the state manager. Calls the state's specific onUpdate method
     */
    public void update() {
        if (!this.mPaused) {
            float dt = Gdx.graphics.getDeltaTime();
            this.onUpdate(dt);
        }
    }

    /**
     * Gets the tag of the state, which is likely a description of the state
     * @return
     */
    public String getTag() {
        return this.mTag;
    }

    /**
     * Method that draws the state called by the state manager. Calls the state's specific onDraw method
     */
    public void draw() {
        float dt = Gdx.graphics.getDeltaTime();
        this.onDraw(dt);
    }

    /**
     * Sees if the current state is paused
     * @return True if paused, false otherwise
     */
    public boolean isPaused() {
        return this.mPaused;
    }

    /**
     * Called when a state is first created in the constructor.
     * INSTEAD OF INITIALIZING THINGS IN THE CONSTRUCTOR, DO IT HERE.
     */
    public abstract void onCreate();

    /**
     * Called whenever a state is to be drawn, which is on every tick
     * @param dt The time since the last draw call (probably not used)
     */
    public abstract void onDraw(float dt);

    /**
     * Called whenever a state is to be updated, which is on every tick when the state isn't paused
     * @param dt The time since the last update call
     */
    public abstract void onUpdate(float dt);

    /**
     * Called when a state is paused.
     */
    public abstract void onPause();

    /**
     * Called when a state is resumed (un-paused)
     */
    public abstract void onResume();

    /**
     * Called right before a state is destroyed to allow for cleanup code such as removal of listeners
     */
    public abstract void onDestroy();

}
