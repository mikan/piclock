/*
 * Cpoyright (c) 2016 mikan. All rights reserved.
 */
package com.tasktoys.piclock;

import com.tasktoys.piclock.util.Logs;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * JavaFX controller class for {@code countdown.fxml}.
 *
 * @author mikan
 * @since 1.0
 */
public class ClockController implements Initializable {

    private static final @NonNull Logger log = Logger.getLogger(ClockController.class.getName());
    @FXML
    public VBox wrapper;
    @FXML
    public Label clockLabel;
    private @NonNull Timer updater = new Timer(true); // Create timer as daemon thread.

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Logs.applyLogLevel(log);
        clockLabel.setFont(PiClock.getClockFont());
        updateClock();
        updater.schedule(new ClockTask(), 500);
        log.info(ClockController.class.getSimpleName() + " initialized.");
    }

    /**
     * Click handler for screen background.
     *
     * @param event mouse event
     */
    @FXML
    public void onWrapperClicked(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            log.finer("Wrapper clicked.");
            updater.cancel();
            PiClock.setScreen(getClass(), "start", PiClock.getPrimaryStage().isFullScreen()); // Go back
        }
    }

    private void updateClock() {
        LocalDateTime time = LocalDateTime.now();
        int hour = time.getHour();
        int minute = time.getMinute();
        clockLabel.setText(time.getSecond() % 2 == 0 ? hour + ":" + minute : hour + " " + minute);
    }

    private class ClockTask extends TimerTask {

        @Override
        public void run() {
            Platform.runLater(ClockController.this::updateClock);
        }
    }
}
