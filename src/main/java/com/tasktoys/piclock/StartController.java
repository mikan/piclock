/*
 * Cpoyright (c) 2016 mikan. All rights reserved.
 */
package com.tasktoys.piclock;

import com.tasktoys.piclock.util.DateFormatConverter;
import com.tasktoys.piclock.util.Logs;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * JavaFX controller class for {@code start.fxml}.
 *
 * @author mikan
 * @since 1.0
 */
public class StartController implements Initializable {

    @NonNull
    private static final Logger log = Logger.getLogger(StartController.class.getName());
    private static final String PATTERN = "yyyy/MM/dd";
    @FXML
    public VBox wrapper;
    @FXML
    public Button clockButton;
    @FXML
    public DatePicker countdownDatePicker;
    @FXML
    public Button countdownButton;
    @FXML
    public Label title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Logs.applyLogLevel(log);

        // Setup DatePicker format.
        countdownDatePicker.setConverter(new DateFormatConverter(PATTERN));
        countdownDatePicker.setPromptText("2016/11/30");
        countdownButton.requestFocus();

        log.info(StartController.class.getSimpleName() + " initialized.");
    }

    /**
     * Click handler for screen background.
     *
     * @param event mouse event
     */
    @FXML
    public void onWrapperClicked(MouseEvent event) {
        switch (event.getButton()) {
            case PRIMARY:
                // Double-clicked
                if (event.getClickCount() == 2) {
                    log.finer("Wrapper double-clicked.");
                    Stage primaryStage = PiClock.getPrimaryStage();
                    primaryStage.setFullScreen(!primaryStage.isFullScreen()); // Change full-screen or window.
                }
                break;
            default:
                log.finer("Wrapper clicked: ev=" + event.getEventType() + ",count=" + event.getClickCount());
                break;
        }
    }

    /**
     * Click handler for "clock" button.
     *
     * @param event mouse event
     */
    @FXML
    public void onClockClicked(MouseEvent event) {
        log.info("Clock button clicked.");
        PiClock.setScreen(getClass(), "clock", PiClock.getPrimaryStage().isFullScreen());
    }

    /**
     * Click handler for "countdown" button.
     *
     * @param event mouse event
     */
    @FXML
    public void onCountdownClicked(MouseEvent event) {
        log.info("Countdown button clicked. prompt=" + countdownDatePicker.getPromptText());
        CountdownController.setTargetDate(countdownDatePicker.getConverter().fromString(countdownDatePicker.getPromptText()));
        PiClock.setScreen(getClass(), "countdown", PiClock.getPrimaryStage().isFullScreen());
    }
}
