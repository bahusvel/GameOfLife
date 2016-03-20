package game.interfaces;

import java.awt.*;

/**
 * Created by Dale on 8/03/16.
 */
public interface IGameOfLife {
    void init();

    void stop();

    void update();

    <T extends Graphics> void render(T graphics);
}
