package game.listeners;

import game.GameOfLifeGrid;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Dale on 9/03/16.
 */
public class GridMouseListener<T extends GameOfLifeGrid> implements MouseListener, MouseMotionListener {
    private T gameOfLifeGrid;
    private boolean mouseIsDown = false;

    public GridMouseListener(T grid) {
        this.gameOfLifeGrid = grid;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getX() > gameOfLifeGrid.getWidth() || e.getY() > gameOfLifeGrid.getHeight())
            return;

        mouseIsDown = true;
        if (gameOfLifeGrid.cellIsAliveAtMouseCoords(e.getX(), e.getY())) {
            gameOfLifeGrid.removeSeedAtMouseCoords(e.getX(), e.getY());
        } else {
            gameOfLifeGrid.addSeedFromMouseCoords((e.getX()), (e.getY()));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseIsDown = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!mouseIsDown)
            return;

        gameOfLifeGrid.addSeedFromMouseCoords((e.getX()), (e.getY()));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
