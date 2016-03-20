package test.game;

import game.GameOfLifeGrid;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import objects.Cell;
import objects.drawable.DrawableCell;
import org.junit.Assert;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Optional;

/**
 * GameOfLifeGrid Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/13/2016</pre>
 */
public class GameOfLifeGridTest extends TestCase {
    private final GameOfLifeGrid<Cell> gameOfLifeGrid = new GameOfLifeGrid(2000, 2000, 0, 0, 10, 10, () -> new Cell()) {
        @Override
        protected Dimension getViewPortSize() {
            return new Dimension(800, 800);
        }
    };

    /**
     * Instantiates a new Game of life grid test.
     *
     * @param name the name
     */
    public GameOfLifeGridTest(String name) {
        super(name);
    }

    /**
     * Suite test.
     *
     * @return the test
     */
    public static Test suite() {
        return new TestSuite(GameOfLifeGridTest.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        BufferedImage bufferedImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        gameOfLifeGrid.render(bufferedImage.getGraphics());
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCompliesWithRule1(){
        gameOfLifeGrid.addSeed(10, 10);
        gameOfLifeGrid.update();
        assertFalse(gameOfLifeGrid.cellIsAlive(10, 10));
        gameOfLifeGrid.addSeed(10, 10);
        gameOfLifeGrid.addSeed(10, 11);
        gameOfLifeGrid.update();
        assertFalse(gameOfLifeGrid.cellIsAlive(10,10));
    }

    public void testCompliesWithRule2(){
        gameOfLifeGrid.addSeed(10, 10);
        gameOfLifeGrid.addSeed(10, 11);
        gameOfLifeGrid.addSeed(11, 10);
        gameOfLifeGrid.update();
        assertTrue(gameOfLifeGrid.cellIsAlive(10, 10));

        gameOfLifeGrid.addSeed(20, 20);
        gameOfLifeGrid.addSeed(20, 21);
        gameOfLifeGrid.addSeed(21, 20);
        gameOfLifeGrid.addSeed(19, 20);
        gameOfLifeGrid.update();
        assertTrue(gameOfLifeGrid.cellIsAlive(20, 20));
    }

    public void testCompliesWithRule3(){
        gameOfLifeGrid.addSeed(20, 20);
        gameOfLifeGrid.addSeed(20, 21);
        gameOfLifeGrid.addSeed(21, 20);
        gameOfLifeGrid.addSeed(19, 20);
        gameOfLifeGrid.addSeed(20, 19);
        gameOfLifeGrid.update();
        assertFalse(gameOfLifeGrid.cellIsAlive(20, 20));
    }

    public void testCompliesWithRule4(){
        gameOfLifeGrid.addSeed(20, 21);
        gameOfLifeGrid.addSeed(21, 20);
        gameOfLifeGrid.addSeed(19, 20);
        gameOfLifeGrid.update();
        assertTrue(gameOfLifeGrid.cellIsAlive(20, 20));
    }

    /**
     * Test get alive neighbours.
     *
     * @throws Exception the exception
     */
    public void testGetAliveNeighbours() throws Exception {
        Assert.assertTrue(gameOfLifeGrid.setCellAlive(10, 10));
        Assert.assertTrue(gameOfLifeGrid.setCellAlive(10, 11));
        int neighbours = gameOfLifeGrid.getAliveNeighbours(gameOfLifeGrid.getDrawableCell(10, 10).get());
        Assert.assertEquals(neighbours, 1);
    }

    /**
     * Test get cells viable for next gen.
     *
     * @throws Exception the exception
     */
    public void testGetCellsViableForNextGen() throws Exception {
        gameOfLifeGrid.addSeed(10, 10);
        gameOfLifeGrid.addSeed(10, 11);
        gameOfLifeGrid.addSeed(10, 12);
        HashSet<DrawableCell<Cell>> nGenCells = gameOfLifeGrid.getCellsViableForNextGen(true);

        //S = Number of seeds
        //D = duplicate neighbours
        // Formulae = ((S*8) - D) + S
        Assert.assertEquals(nGenCells.size(), 15);
    }

    /**
     * Test calculate next gen seed state.
     *
     * @throws Exception the exception
     */
    public void testCalculateNextGenSeedState() throws Exception {
        int x1 = 10, x2 = 10, x3 = 10, y1 = 10, y2 = 11, y3 = 12;

        gameOfLifeGrid.addSeed(x1, y1);
        gameOfLifeGrid.addSeed(x2, y2);
        gameOfLifeGrid.addSeed(x3, y3);
        HashSet<DrawableCell<Cell>> nGenCells = gameOfLifeGrid.getCellsViableForNextGen(true);
        gameOfLifeGrid.calculateNextGenSeedState(nGenCells);
        for (DrawableCell<Cell> cells : nGenCells) {
            if (cells.getPosX() == x1 && cells.getPosY() == y1)
                Assert.assertFalse(cells.getCell().getNextGenState());
            if (cells.getPosX() == x2 && cells.getPosY() == y2)
                Assert.assertTrue(cells.getCell().getNextGenState());
            if (cells.getPosX() == x3 && cells.getPosY() == y3)
                Assert.assertFalse(cells.getCell().getNextGenState());
        }
    }

    /**
     * Test get neighbouring cells.
     *
     * @throws Exception the exception
     */
    public void testGetNeighbouringCells() throws Exception {
        /** Get the neighbouring cells of the following coords **/
        int x = 5;
        int y = 6;
        /*******************************************************/

        Optional<DrawableCell<Cell>[]> c = gameOfLifeGrid.getNeighbouringCells(x, y);
        //C will not be present if given x,y coords are out of bounds.
        assertTrue(c.isPresent());
        DrawableCell<Cell>[] neighbourCells = c.get();

        int count = 0;
        //Loop through neighbour x,y coords
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y)
                    continue;
                for (DrawableCell<Cell> ce : neighbourCells) {
                    if (ce.getPosX() == i && ce.getPosY() == j) {
                        count++;
                        break;
                    }
                }
            }
        }
        //Should always be 8 if the should the first assertion pass
        assertEquals(count, 8);
    }
}
