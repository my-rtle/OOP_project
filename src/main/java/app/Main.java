package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main entry point for testing the CalendarPanel component.
 * This class launches a standalone JavaFX application to preview the calendar UI.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // Create an instance of the custom CalendarPanel component
        CalendarPanel panel = new CalendarPanel();

        // Create a scene containing the panel and set its dimensions
        Scene scene = new Scene(panel, 1000, 600);

        // Set up and display the main application window
        stage.setTitle("📅 Calendar Component Preview");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
