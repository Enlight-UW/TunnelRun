package club.enlight;

/**
 * Created by sschwebach on 10/13/15.
 */
public interface ExternalInputListener {
    public void onButtonDown(InputEnum buttonType);

    public void onButtonUp(InputEnum buttonType);

    public void onTouchDown(int screenX, int screenY, int pointer, int button);

    public void onTouchUp(int screenX, int screenY, int pointer, int button);

    public void onTouchDragged(int screenX, int screenY, int pointer);
}
