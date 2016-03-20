package game.interfaces;

import java.awt.*;

/**
 * Created by Dale on 8/03/16.
 */
public interface Renderable<T extends Graphics> {
    void render(T g);
}
