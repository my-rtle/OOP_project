package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        CalendarPanel calendarPanel = new CalendarPanel(); // 自定义组件

        Scene scene = new Scene(calendarPanel, 1000, 800); // 创建场景

        primaryStage.setTitle("📅 My JavaFX Calendar App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

