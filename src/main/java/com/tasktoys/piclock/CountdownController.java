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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
public class CountdownController implements Initializable {

    private static final @NonNull Logger log = Logger.getLogger(CountdownController.class.getName());
    private static @NonNull LocalDate targetDate = LocalDate.now();
    @FXML
    public VBox wrapper;
    @FXML
    public Label countdownLabel;
    private @NonNull Timer updater = new Timer(true); // Create timer as daemon thread.

    static void setTargetDate(@NonNull LocalDate targetDate) {
        CountdownController.targetDate = targetDate;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Logs.applyLogLevel(log);
        countdownLabel.setFont(PiClock.getClockFont());
        updateRemain();
        updater.schedule(new UpdateRemainTask(), 500);
        log.info(CountdownController.class.getSimpleName() + " initialized.");
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

    private void updateRemain() {
        log.finer("now:\t" + LocalDate.now());
        log.finer("tgt:\t" + targetDate);
        long remain = ChronoUnit.DAYS.between(LocalDate.now(), targetDate);
        if (remain < 0) {
            remain = 0;
        }
        if (countdownLabel != null) {
            countdownLabel.setText(Long.toString(remain));
        } else {
            log.severe("countdownLabel is null!");
        }
    }

    private class UpdateRemainTask extends TimerTask {

        @Override
        public void run() {
            Platform.runLater(CountdownController.this::updateRemain);
        }
    }
}
