/*
 * Cpoyright (c) 2016 mikan. All rights reserved.
 */
package com.tasktoys.piclock;

import com.tasktoys.piclock.util.Logs;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * A simple clock & countdown application for Raspberry Pi screen.
 *
 * @author mikan
 * @since 1.0
 */
public class PiClock extends Application {

    @NonNull
    private static final Logger log = Logger.getLogger(PiClock.class.getName());
    @Nullable
    private static final Font clockFont = Font.loadFont(
            PiClock.class.getResource("/fonts/DSEG7ClassicMini-Bold.ttf").toExternalForm(), 10);
    @Nullable
    private static Stage primaryStage;

    /**
     * Bootstrap the JavaFX application.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    static Stage getPrimaryStage() {
        if (primaryStage == null) {
            throw new IllegalStateException("Application not started.");
        }
        return primaryStage;
    }

    private static void setPrimaryStage(Stage primaryStage) {
        PiClock.primaryStage = primaryStage;
    }

    @Nullable
    static Font getClockFont() {
        return clockFont;
    }

    /**
     * Set screen.
     *
     * @param loader     loader class
     * @param fxml       name of fxml
     * @param fullScreen set {@code true} for full-screen, {@code false} otherwise.
     */
    static void setScreen(Class<?> loader, String fxml, boolean fullScreen) {
        Parent root = null;
        try {
            root = FXMLLoader.load(loader.getResource("/fxml/" + fxml + ".fxml"));
        } catch (IOException e) {
            log.throwing(StartController.class.getName(), "onCountdownClicked", e);
            Platform.exit();
            return;
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/qvga.css");
        primaryStage.setTitle("PiClock");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(fullScreen);
        primaryStage.show();
    }

    /**
     * Displays start.fxml.
     *
     * @param primaryStage primary stage
     * @throws Exception when the runtime exception thrown
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Logs.applyLogLevel(log);

        setPrimaryStage(primaryStage);
        setScreen(getClass(), "start", true);
        log.info("Application started.");
    }

    @Override
    public void stop() throws Exception {
        log.info("Application stopped.");
    }
}
