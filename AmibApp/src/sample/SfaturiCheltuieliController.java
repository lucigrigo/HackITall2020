package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SfaturiCheltuieliController {
    private SecondScene prevScene;
    @FXML
    private GridPane cheltuieliGridPane;
    @FXML
    private Button messButton;
    @FXML
    private TextArea textArea;

    @FXML
    public void initialize() throws Exception {
        prevScene = new SecondScene();
        textArea.setVisible(false);
        textArea.setEditable(false);
    }

    @FXML
    public void goBack() {
        try {
            Stage stage = (Stage) cheltuieliGridPane.getScene().getWindow();
            prevScene.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void recommend() {
        StringBuilder sb = new StringBuilder();
        try {
            ProcessBuilder pb = new ProcessBuilder();
            pb.command("python", " /Users/grigorelucian/Documents/HackITall/App3/src/sample/shopping_recommender.py");
            Process p = pb.start();
            BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = bf.readLine()) != null) {
                sb.append(line);
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        textArea.setVisible(true);
        textArea.setText(sb.toString());
    }
}
