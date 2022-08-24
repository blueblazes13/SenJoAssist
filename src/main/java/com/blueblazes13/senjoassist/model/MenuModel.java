package com.blueblazes13.senjoassist.model;

import java.util.Timer;
import java.util.TimerTask;

import com.blueblazes13.senjoassist.interfaces.MenuItem;
import com.blueblazes13.senjoassist.view.SquareView;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class MenuModel {
    
    SquareView[][] squares;
    AnchorPane pane;
    Timer timer;

    public MenuModel() {
        this.squares = new SquareView[3][4];
        createMenu();
    }

    /**
     * Returns an AnchorPane that contains the menu
     * 
     * @return AnchorPane, containing the menu
     */
    public AnchorPane getNode() {
        return this.pane;
    }


    /**
     * Creates the menu and puts it into the returnable AnchorPane
     */
    private void createMenu() {
        this.pane = new AnchorPane();
        this.pane.setPrefSize(1024, 600);

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 4; x++) {
                SquareView square = new SquareView((getMenuItem(x, y) != null)?getMenuItem(x, y).getView():null);
                square.setLayoutX(((x < 2)? 10: 260) + 10*x + SquareView.WIDTH*x);
                square.setLayoutY(10 + 10*y + SquareView.HEIGHT*y);
                square.setVisible(false);

                square.setOnMouseClicked((MouseEvent me) -> {
                    System.out.println("Clicked!");
                });

                this.squares[y][x] = square;
                this.pane.getChildren().add(square);
            }
        }
    }


    /**
     * Fills the menu with a predefined set of items
     * 
     * @param x The x coordinate
     * @param y The y coordinate
     * @return  The item that provides the functionality
     */
    private MenuItem getMenuItem(int x, int y) {
        final MenuItem[][] items = {
            {new HueModel() , null, null, null},
            {null           , null, null, null},
            {null           , null, null, null}
        };

        return items[y][x];
    }


    /**
     * Allows the menu to be opened and closed smoothly
     */
    private void setMenuVisible(boolean visible) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 4; x++) {
                if (visible) this.squares[y][x].setVisible(true);

                FadeTransition transition = new FadeTransition();
                transition.setDuration(Duration.millis(100));
                transition.setFromValue(visible? 0.0: 1.0);
                transition.setToValue(visible? 1.0: 0.0);
                transition.setNode(this.squares[y][x]);
                transition.play();

                final int i = x;
                final int j = y;
                transition.setOnFinished((ActionEvent ae) -> {if (!visible) this.squares[j][i].setVisible(false);});
            }
        }
    }


    /**
     * Checks whether the menu is visible or not
     * 
     * @return true if visible, false if not visible
     */
    public boolean menuIsVisible() {
        return this.squares[1][1].isVisible();

    }


    /**
     * Triggers the menu:
     * <br></br>
     * <br>
     * - If the menu is <i>not visible</i>, it will become visible and wait 7 seconds before
     *   it disappears.
     * </br>
     * <br>
     * - If the menu is <i>already visible</i>, the timer will be reset and the menu will be
     *   visible for another 7 seconds.
     * </br>
     */
    public void triggerMenu() {
        if (this.timer != null)  this.timer.cancel();
        if (!this.menuIsVisible()) setMenuVisible(true);
        this.timer = new Timer(true);
        this.timer.schedule(new TimerTask() {
            private int secondsToWait = 7;

            @Override
            public void run() {
                if (secondsToWait <= 0) {
                    Platform.runLater(() -> {
                        setMenuVisible(false);
                    });
                }

                secondsToWait--;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            }
        }, 0, 1);
    }


}
