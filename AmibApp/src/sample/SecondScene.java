package sample;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SecondScene {
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("second_scene.fxml"));
        stage.setTitle("Home");
        stage.setScene(new Scene(root, 400, 400));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
}
