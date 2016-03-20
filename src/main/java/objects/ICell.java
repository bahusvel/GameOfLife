package objects;

/**
 * Created by Dale on 9/03/16.
 */
public interface ICell {
    boolean isAlive();

    void setIsAlive(boolean b);

    boolean getNextGenState();

    void setNextGenState(boolean b);

    void toggleIsAlive();

    boolean transform();
}
