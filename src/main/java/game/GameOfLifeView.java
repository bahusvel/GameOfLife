package game;

import game.interfaces.IGameOfLife;
import game.interfaces.IUpdateable;
import game.interfaces.Renderable;
import objects.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created by Dale on 8/03/16.
 */
public class GameOfLifeView implements IGameOfLife, Runnable, ComponentListener {

    /**
     * The constant Time between updates.
     */
    public static final long UPDATE_FPS = 600L;

    /**
     * The view
     */
    private static final GameOfLifeView gol = new GameOfLifeView("Game of life",
            new Dimension(800, 700));

    /**
     * J components
     */
    private final JFrame gameFrame;
    private final Button startButton = new Button("Start");
    private final GameOfLifeGrid<Cell> gameOfLifePanel;

    /**
     * Dimensions
     */
    private final Dimension gameDimension;
    private final double frameWidth;
    private final double frameHeight;

    /**
     * Render items
     */
    private final Renderable<Graphics>[] renderableItems;

    /**
     * Updateable items
     */
    private final IUpdateable[] updatebleItems;

    /**
     * State
     */
    private boolean upd = false;
    private boolean pause = false;


    /**
     * Constructs a new game of life view
     */
    private GameOfLifeView(String gameTitle, Dimension d) {
        gameFrame = new JFrame(gameTitle);
        gameFrame.setResizable(false);
        frameWidth = d.getWidth();
        frameHeight = d.getHeight();
        gameDimension = d;
        gameFrame.setSize(d);
        DisplayMode dm = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
        gameOfLifePanel = new GameOfLifeGrid(dm.getWidth(), dm.getHeight(), 0, 0, 10, 10, () -> new Cell()) {
            @Override
            protected Dimension getViewPortSize() {
                return new Dimension(gameFrame.getWidth(), gameFrame.getHeight());
            }
        };
        renderableItems = new Renderable[]{gameOfLifePanel};
        updatebleItems = new IUpdateable[]{gameOfLifePanel};
        gameFrame.addComponentListener(this);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Get the singleton instance game of life view.
     *
     * @return the game of life view
     */
    public static GameOfLifeView _getInstance() {
        return gol;
    }

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String[] args) {
        GameOfLifeView._getInstance().init();
    }

    @Override
    public void init() {
        GridLayout gridLayout = new GridLayout(2, 1);
        gameOfLifePanel.setSize(gameDimension);
        gridLayout.addLayoutComponent("gamePanel", gameOfLifePanel);
        gridLayout.preferredLayoutSize(gameFrame);
        startButton.setBounds(gameFrame.getWidth() / 2 - 70, gameFrame.getHeight() - 100, 80, 30);

        startButton.addActionListener(e -> upd = true);
        gameFrame.add(startButton);
        gameFrame.add(gameOfLifePanel);
        gameFrame.setVisible(true);
        gameFrame.addKeyListener(gameOfLifePanel);
        new Thread(GameOfLifeView._getInstance()).start();
    }

    @Override
    public void stop() {
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    @Override
    public void update() {
        for (IUpdateable items : updatebleItems) {
            items.update();
        }
    }

    /**
     * Render game.
     *
     * @param <T>      the type parameter
     * @param graphics the graphics
     */
    public <T extends Graphics> void render(T graphics) {
        for (Renderable<Graphics> obj : renderableItems) {
            obj.render(graphics);
        }
    }

    /**
     * Gets frame width.
     *
     * @return the frame width
     */
    public double getFrameWidth() {
        return frameWidth;
    }

    /**
     * Gets frame height.
     *
     * @return the frame height
     */
    public double getFrameHeight() {
        return frameHeight;
    }

    /**
     * Gets game dimension.
     *
     * @return the game dimension
     */
    public Dimension getGameDimension() {
        return gameDimension;
    }


    @Override
    public void run() {
        long upStartTime = System.currentTimeMillis();

        while (true) {
            if (pause)
                return;


            render(gameOfLifePanel.getGraphics());
            if ((System.currentTimeMillis() - upStartTime) >= UPDATE_FPS) {
                upStartTime = System.currentTimeMillis();
                if (upd) {
                    update();
                }
            }
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        startButton.setBounds(e.getComponent().getWidth() / 2 - 80, gameFrame.getHeight() - 100, 80, 30);
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
