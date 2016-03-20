package objects;

/**
 * Created by Dale on 8/03/16.
 */
public class Cell implements ICell {
    private boolean isAlive = false;
    private boolean nextGenState = false;

    public Cell() {
    }

    @Override
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public boolean getNextGenState() {
        return nextGenState;
    }

    @Override
    public void setNextGenState(boolean b) {
        this.nextGenState = b;
    }

    @Override
    public boolean transform() {
        setIsAlive(getNextGenState());
        return isAlive();
    }

    @Override
    public void toggleIsAlive() {
        isAlive = isAlive ? false : true;
    }
}
