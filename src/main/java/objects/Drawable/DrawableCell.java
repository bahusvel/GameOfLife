package objects.drawable;

import objects.ICell;
import utils.IRGBColor;

import java.awt.*;
import java.util.Objects;

/**
 * Created by Dale on 8/03/16.
 */
public class DrawableCell<T extends ICell> extends Rectangle implements IDrawable<Graphics> {
    private final T cell;
    private Color cellColor;
    private int x;
    private int y;

    public DrawableCell(T cell, int width, int height, int x, int y, IRGBColor color) {
        super(width, height);
        this.cell = cell;
        this.x = x;
        this.y = y;
        float[] HSB = Color.RGBtoHSB(color.getR(), color.getG(), color.getB(), null);
        cellColor = Color.getHSBColor(HSB[0], HSB[1], HSB[2]);
    }

    public T getCell() {
        return cell;
    }

    public int getPosX() {
        return x;
    }

    public void setPosX(int x) {
        this.x = x;
    }

    public int getPosY() {
        return y;
    }

    public void setPosY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DrawableCell))
            return false;

        return ((DrawableCell<?>) obj).getPosX() == this.getPosX() &&
                ((DrawableCell<?>) obj).getPosY() == this.getPosY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public void draw(Graphics g, double xPos, double yPos) {
        Color orig = g.getColor();

        if (getCell().isAlive()) {
            g.setColor(cellColor);
            g.fillRect((int) xPos, (int) yPos, width, height);
        } else {
            g.setColor(Color.gray);
            g.clearRect((int) xPos, (int) yPos, width, height);
            g.drawRect((int) xPos, (int) yPos, width, height);
        }
        g.setColor(orig);
    }
}
