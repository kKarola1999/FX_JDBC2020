import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Handler;

import controller.DBUtil;

import controller.DBUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class KlientController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button connectButton;

    @FXML
    private Button disconnectButton;

    @FXML
    private TextArea consoleTextArea;

    @FXML
    private TextField IDText;

    @FXML
    private TextField IdName;

    @FXML
    private TextField idEmail;

    @FXML
    private TextField idAdres;

    @FXML
    private TextField idTelNum;

    @FXML
    private Button idLogIn;

    private DBUtil dbUtil;
    private RacketDAO racketDAO;

    @FXML
    void connectButtonPressed(ActionEvent event) throws SQLException, ClassNotFoundException {

        dbUtil = new DBUtil(userTextField.getText(), passwordTextField.getText(), consoleTextArea);
        racketDAO = new RacketDAO(dbUtil, consoleTextArea);

        dbUtil.dbConnect();

        consoleTextArea.appendText("Access granted for user \"" + userTextField.getText() + "\"." + "\n");
        connectButton.setDisable(true);
        disconnectButton.setDisable(false);
        /*
        addRacketButton.setDisable(false);
        selectRacketButton.setDisable(false);
        showRacketsButton.setDisable(false);

        selectRacketNameTextField.setDisable(false);
        racketNameToAddTextField.setDisable(false);

         */

    }



    @FXML
    void disconnectButtonPressed(ActionEvent event) throws SQLException {

        dbUtil.dbDisconnect();
        connectButton.setDisable(false);
        disconnectButton.setDisable(true);
        /*
        addRacketButton.setDisable(true);

        selectRacketButton.setDisable(true);
        showRacketsButton.setDisable(true);
        selectRacketNameTextField.setDisable(true);
        selectRacketNameTextField.setText("");
        selectRacketNameTextField.setPromptText("Podaj nazwę");
        racketNameToAddTextField.setDisable(true);
        racketNameToAddTextField.setText("");
        racketNameToAddTextField.setPromptText("Podaj nazwę");
        clientsTable.getItems().clear();
*/
    }

    @FXML
    void onLogIn(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {

            if (!IdName.getText().equals(null)) {
                racketDAO.insertClient(IDText.getText(), IdName.getText(), idAdres.getText(), idEmail.getText(), idTelNum.getText());
                consoleTextArea.appendText("New Client " + IdName.getText() + " inserted." + "\n");

            }
        } catch (SQLException | ClassNotFoundException e) {
            consoleTextArea.appendText("Error occurred while inserting racket.\n");
            throw e;
        }
    }



    @FXML
    void initialize() {
        assert userTextField != null : "fx:id=\"userTextField\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert connectButton != null : "fx:id=\"connectButton\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert disconnectButton != null : "fx:id=\"disconnectButton\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert consoleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert IDText != null : "fx:id=\"IDText\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert IdName != null : "fx:id=\"IdName\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert idEmail != null : "fx:id=\"idEmail\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert idAdres != null : "fx:id=\"idAdres\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert idTelNum != null : "fx:id=\"idTelNum\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert idLogIn != null : "fx:id=\"idLogIn\" was not injected: check your FXML file 'dbFX.fxml'.";

    }
}