package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class RemindersController {
    private SecondScene prevScene;
    @FXML
    private GridPane remindersGridPane;
    @FXML
    private TextArea textArea;

    @FXML
    public void initialize() throws Exception {
        prevScene = new SecondScene();
    }

    @FXML
    public void goBack() {
        try {
            Stage stage = (Stage) remindersGridPane.getScene().getWindow();
            prevScene.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void getReminders() {
        String csvFile = "/Users/grigorelucian/Downloads/spendings.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplit = ",";
        ArrayList<String[]> transactions = new ArrayList<>();
        int n = 0;
        try {
            br = new BufferedReader(new FileReader(csvFile));

            while ((line = br.readLine()) != null) {
                if (n == 0) {
                    n++;
                    continue;
                }
                transactions.add(line.split(cvsSplit));
                n++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //int date = Calendar.DAY_OF_MONTH - 1;
        int date = 15;
        ArrayList<Integer> previousDayTransactionsIndices = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            if (Integer.parseInt(transactions.get(i)[0]) == date) {
                previousDayTransactionsIndices.add(i);
            }
        }
        ArrayList<String[]> previousDayTransactions = new ArrayList<>();
        for (int i = 0; i < previousDayTransactionsIndices.size(); i++) {
            if ((Integer.parseInt(transactions.get(previousDayTransactionsIndices.get(i))[1]) == Calendar.MONTH - 1 + 10) ||
                    (Integer.parseInt(transactions.get(previousDayTransactionsIndices.get(i))[1]) == Calendar.MONTH - 2 + 10) ||
                    (Integer.parseInt(transactions.get(previousDayTransactionsIndices.get(i))[1]) == Calendar.MONTH + 10) ||
                    (Integer.parseInt(transactions.get(previousDayTransactionsIndices.get(i))[1]) == Calendar.MONTH - 3 + 10)) {
                previousDayTransactions.add(transactions.get(previousDayTransactionsIndices.get(i)));
            }
        }
        ArrayList<String> makePayment = new ArrayList<>();
        ArrayList<String> madePayments = new ArrayList<>();
        for (int i = 0; i < previousDayTransactions.size(); i++) {
            if (Integer.parseInt(previousDayTransactions.get(i)[1]) != Calendar.MONTH + 10) {
                if (!makePayment.contains(previousDayTransactions.get(i)[3])) {
                    makePayment.add(previousDayTransactions.get(i)[3]);
                }
            } else {
                madePayments.add(previousDayTransactions.get(i)[3]);
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for (int i = 0; i < makePayment.size(); i++) {
            if (!madePayments.contains(makePayment.get(i)))
//                System.out.println("Nu uitati sa faceti plata lunara catre " + makePayment.get(i));
                sb.append("Nu uitati sa faceti plata lunara catre " + makePayment.get(i) + "\n");
            found = true;
        }

        if(found) {
            textArea.setVisible(true);
            textArea.setEditable(false);
            textArea.setText(sb.toString());
        }
    }
}
