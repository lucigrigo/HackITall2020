package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RemindersScene {
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("reminders.fxml"));
        stage.setTitle("Reminders");
        stage.setScene(new Scene(root, 600, 400));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
}
