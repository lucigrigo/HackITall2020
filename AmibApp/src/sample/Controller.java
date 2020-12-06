package sample;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.bytedeco.javacv.FrameFilter;

import java.awt.*;
import java.io.*;

public class Controller {
    private SecondScene secondScene;

    @FXML
    private GridPane loginGridPane;

    public void initialize() throws Exception {
        secondScene = new SecondScene();
    }

    @FXML
    public void quitApp() {
        System.exit(0);
    }

    @FXML
    public void checkIdentity() {
        // fotografierea fetei folosind camera device-ului
        MutableBoolean takePhoto = new MutableBoolean(false);
        Thread th = new Thread(new WebCamThread(takePhoto));
        th.start();
        takePhoto.setValue(true);
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // trimiterea catre server a 2 fotografii pentru verificare
        sendRequests();
    }

    public void sendRequests() {
        String link_image1 = "https://scontent.fias1-1.fna.fbcdn.net/v/t1.0-9/46485382_1902016696575801_2255347456671744_n.jpg?_nc_cat=106&ccb=2&_nc_sid=174925&_nc_ohc=L-hGb2Ydn3YAX-Sf4ZA&_nc_ht=scontent.fias1-1.fna&oh=66821bf794b0d199aa13399f5b4d3c8e&oe=5FF2BE60";
        String link_image2 = "https://scontent.fias1-1.fna.fbcdn.net/v/t1.0-9/38260497_1739119572865515_1036985923377561600_o.jpg?_nc_cat=101&ccb=2&_nc_sid=174925&_nc_ohc=2rjlVzxZEXwAX-LI8vI&_nc_ht=scontent.fias1-1.fna&oh=6faed55981572b9c0674fd65168524b9&oe=5FF07E22";
        String faceId1, faceId2;
        boolean result = false;

        try {
            faceId1 = Requests.postDetectRequest(link_image1);
            faceId2 = Requests.postDetectRequest(link_image2);
            result = Requests.postVerifyRequest(faceId1, faceId2);
            //Requests.postImageReferenceRequest("C:\\Users\\ediol\\Desktop\\asd.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result && verificare_SMS()) {
            try {
                Stage stage = (Stage) loginGridPane.getScene().getWindow();
                secondScene.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private boolean verificare_SMS() {
        // Functie care ar trimite un SMS catre telefonul utilizatorului
        // cu un cod de securitate, pe care ar putea singura sa il citeasca
        // double-factor authentification
        return true;
    }
}

