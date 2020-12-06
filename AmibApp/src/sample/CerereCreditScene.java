package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CerereCreditScene {
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("cererecredit.fxml"));
        stage.setTitle("Cerere credit");
        stage.setScene(new Scene(root, 600, 400));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
}
