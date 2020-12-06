package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CerereCreditController {
    private final static float MIN_CREDIT_SCORE = 2.64f;

    private SecondScene prevScene;
    @FXML
    private GridPane creditGridPane;
    @FXML
    private Label message;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField valoareCr;
    @FXML
    private TextField nrLuni;
    @FXML
    private Button srcButton;

    @FXML
    public void initialize() throws Exception {
        prevScene = new SecondScene();
        //runAnalysis();
    }

    @FXML
    public void srcButtonAction() {
        if (valoareCr.getText().equals("") || nrLuni.getText().equals("")) {
            textArea.setText("Introduceti date valide!");
            return;
        }
        float valoare_credit = Float.parseFloat(valoareCr.getText());
        int numar_luni = Integer.parseInt(nrLuni.getText());
        runAnalysis(valoare_credit, numar_luni);
    }

    private int verificare_ANAF() {
        return 0;
    }

    private int verificare_datorii_credite() {
        return 0;
    }

    private int alte_verificari() {
        return 0;
    }

    private float procentaj_acceptat(float valoare_credit,
                                     int numar_luni,
                                     float sold_cont_curent,
                                     float cheltuieli_pe_luna,
                                     float venituri_pe_luna) {
        // o formula de calcul
        // care sa calculeze un procent acceptat din valoarea initiala
        // a creditului
        return .8f;
    }

    private void runAnalysis(float valoare_credit,
                             int numar_luni) {
//        float valoare_credit = 1000.f;
//        int numar_luni = 24;
        float sold_cont_curent = 5500.f;
        float cheltuieli_pe_luna = 1200.f;
        float venituri_pe_luna = 3000.f;
        boolean acorda_credit = false;

        // calcule mai complicate legate de oferirea unui credit
        // mai multe verificari: ANAF, etc.
        //        if (((venituri_pe_luna - cheltuieli_pe_luna) * numar_luni <= 1.3 * valoare_credit) ||
//                (sold_cont_curent >= 1.2 * valoare_credit && venituri_pe_luna >= cheltuieli_pe_luna)) {
//            acorda_credit = true;
//        }
        float credit_score = 0;
        credit_score += verificare_ANAF();
        credit_score += verificare_datorii_credite();
        credit_score += alte_verificari();

        if (credit_score >= MIN_CREDIT_SCORE) {
            acorda_credit = true;
        }

        if (acorda_credit) {
            message.setText("Creditul a fost aprobat!");
            textArea.setVisible(false);
            // de trimis la serverul propriu
        } else {
            message.setText("Creditul nu a fost aprobat...\nSe incearca alte variante");
            searchAlternatives(valoare_credit, numar_luni, sold_cont_curent, cheltuieli_pe_luna, venituri_pe_luna);
        }
    }

    private void searchAlternatives(float valoare_credit,
                                    int numar_luni,
                                    float sold_cont_curent,
                                    float cheltuieli_pe_luna,
                                    float venituri_pe_luna) {
        int initial_numar_luni = numar_luni;
        float initial_valoare_credit = valoare_credit;
        boolean found = false;

        // cautare prima varianta de credit
        // cu o suma mai mica
        StringBuilder sb = new StringBuilder();

        sb.append("\n--- ALTERNATIVE DE CREDIT ---\n\n");

        sb.append("Alternativa 1:");
        sb.append("\nValoare credit: " + procentaj_acceptat(valoare_credit,
                numar_luni, sold_cont_curent, cheltuieli_pe_luna,
                venituri_pe_luna) * valoare_credit);
        sb.append("\nNumar de luni: " + numar_luni);
        sb.append("\n-------\n");

        sb.append("Alternativa 2:");
        sb.append("\nValoare credit: " + valoare_credit);
        sb.append("\nNumar de luni: " + initial_numar_luni * 2); // factor de inmultire
        sb.append("\n-------\n");

        textArea.setVisible(true);
        textArea.setEditable(false);
//        if (!found) {
//            textArea.setText("Nu au fost gasite alte variante acceptabile");
//        } else {
            textArea.setText(sb.toString());
//        }
    }

    @FXML
    public void goBack() {
        try {
            Stage stage = (Stage) creditGridPane.getScene().getWindow();
            prevScene.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
