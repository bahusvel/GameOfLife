package objects.drawable;

import java.awt.*;

/**
 * Created by Dale on 8/03/16.
 */
public interface IDrawable<T extends Graphics> {
    void draw(T g, double xPos, double YPos);
}
