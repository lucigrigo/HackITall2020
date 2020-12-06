package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SecondController {
    private CerereCreditScene cerereCreditScene;
    private RemindersScene remindersScene;
    private SfaturiCheltuieliScene sfaturiCheltuieliScene;

    @FXML
    public Label welcomeMessage2;
    @FXML
    public Label welcomeMessage3;
    @FXML
    public GridPane mainGridPane;
    @FXML
    public Button button1;
    @FXML
    public Button button2;
    @FXML
    public Button button3;
    @FXML
    public Button button4;
    @FXML
    public Button button5;

    @FXML
    public void initialize() {
        cerereCreditScene= new CerereCreditScene();
        remindersScene = new RemindersScene();
        sfaturiCheltuieliScene = new SfaturiCheltuieliScene();

        Timeline t = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(welcomeMessage2.visibleProperty(), true)),
                new KeyFrame(Duration.seconds(0), new KeyValue(welcomeMessage3.visibleProperty(), false)),
                new KeyFrame(Duration.seconds(0), new KeyValue(button1.visibleProperty(), false)),
                new KeyFrame(Duration.seconds(0), new KeyValue(button2.visibleProperty(), false)),
                new KeyFrame(Duration.seconds(0), new KeyValue(button3.visibleProperty(), false)),
                new KeyFrame(Duration.seconds(0), new KeyValue(button4.visibleProperty(), false)),
                new KeyFrame(Duration.seconds(0), new KeyValue(button5.visibleProperty(), false)),
                new KeyFrame(Duration.seconds(3), new KeyValue(welcomeMessage2.visibleProperty(), false)),
                new KeyFrame(Duration.seconds(3), new KeyValue(welcomeMessage3.visibleProperty(), true)),
                new KeyFrame(Duration.seconds(3), new KeyValue(button1.visibleProperty(), true)),
                new KeyFrame(Duration.seconds(3), new KeyValue(button2.visibleProperty(), true)),
                new KeyFrame(Duration.seconds(3), new KeyValue(button3.visibleProperty(), true)),
                new KeyFrame(Duration.seconds(3), new KeyValue(button4.visibleProperty(), true)),
                new KeyFrame(Duration.seconds(5), new KeyValue(button5.visibleProperty(), true))
        );
        t.play();
    }

    @FXML
    public void quitApp() {
        System.exit(0);
    }

    @FXML
    public void cerereCredit() {
        try {
            Stage stage = (Stage) mainGridPane.getScene().getWindow();
            cerereCreditScene.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void reminders() {
        try {
            Stage stage = (Stage) mainGridPane.getScene().getWindow();
            remindersScene.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sfaturiCheltuieli() {
        try {
            Stage stage = (Stage) mainGridPane.getScene().getWindow();
            sfaturiCheltuieliScene.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void doctorMsg() {
        System.out.println("To be added in future versions. Stau tuned!");
    }
}
