package utils;

/**
 * Created by Dale on 10/03/16.
 */
public final class RGBColor implements IRGBColor {
    private final int r;
    private final int g;
    private final int b;

    public RGBColor() {
        this.r = (int) (Math.random() * 250);
        this.g = (int) (Math.random() * 250);
        this.b = (int) (Math.random() * 250);
    }

    public RGBColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }
}
