import controller.DBUtil;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RacketController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="userTextField"
    private TextField userTextField; // Value injected by FXMLLoader

    @FXML // fx:id="passwordTextField"
    private PasswordField passwordTextField; // Value injected by FXMLLoader

    @FXML // fx:id="connectButton"
    private Button connectButton; // Value injected by FXMLLoader

    @FXML // fx:id="disconnectButton"
    private Button disconnectButton; // Value injected by FXMLLoader

    @FXML // fx:id="consoleTextArea"
    private TextArea consoleTextArea; // Value injected by FXMLLoader

    @FXML // fx:id="racketNameToAddTextField"
    private TextField racketNameToAddTextField; // Value injected by FXMLLoader

    @FXML // fx:id="addRacketButton"
    private Button addRacketButton; // Value injected by FXMLLoader

    @FXML // fx:id="selectRacketNameTextField"
    private TextField selectRacketNameTextField; // Value injected by FXMLLoader

    @FXML // fx:id="selectRacketButton"
    private Button selectRacketButton; // Value injected by FXMLLoader

    @FXML // fx:id="showRacketsButton"
    private Button showRacketsButton; // Value injected by FXMLLoader

    @FXML // fx:id="racketTable"
    private TableView clientsTable; // Value injected by FXMLLoader
/*
    @FXML
    private TableColumn<Racket, String> nameCol;

    @FXML
    private TableColumn<Racket, String> manufacturerCol;

    @FXML
    private TableColumn<Racket, String> massCol;

    @FXML
    private TableColumn<Racket, String> headSizeCol;

    @FXML
    private TableColumn<Racket, String> dominantColorCol;

    @FXML
    private TableColumn<Racket, String> priceUSDCol;
*/
    private DBUtil dbUtil;
    private RacketDAO racketDAO;


    @FXML
    void addRacketButtonPressed(ActionEvent event) throws SQLException, ClassNotFoundException {
/*
        try {

            if (!racketNameToAddTextField.getText().equals(null)) {

                racketDAO.insertRacket(racketNameToAddTextField.getText());
                consoleTextArea.appendText("New Racket " + racketNameToAddTextField.getText() + " inserted." + "\n");

            }
        } catch (SQLException e) {
            consoleTextArea.appendText("Error occurred while inserting racket.\n");
            throw e;
        }

 */

    }

    @FXML
    void connectButtonPressed(ActionEvent event) throws SQLException, ClassNotFoundException {

        dbUtil = new DBUtil(userTextField.getText(), passwordTextField.getText(), consoleTextArea);
        racketDAO = new RacketDAO(dbUtil, consoleTextArea);

        dbUtil.dbConnect();

        consoleTextArea.appendText("Access granted for user \"" + userTextField.getText() + "\"." + "\n");
        connectButton.setDisable(true);
        disconnectButton.setDisable(false);
        addRacketButton.setDisable(false);
        selectRacketButton.setDisable(false);
        showRacketsButton.setDisable(false);

        selectRacketNameTextField.setDisable(false);
        racketNameToAddTextField.setDisable(false);

    }

    @FXML
    void disconnectButtonPressed(ActionEvent event) throws SQLException {

        dbUtil.dbDisconnect();
        connectButton.setDisable(false);
        disconnectButton.setDisable(true);
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

    }

    @FXML
    void selectRacketButtonPressed(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {

            clientsTable.getItems().clear();
            ObservableList<Statystyka> statystykaData = racketDAO.showAllZysk();
            populateZysk(statystykaData);

        } catch (SQLException e) {
            consoleTextArea.appendText("Error occurred while getting rackets from DB.\n");
            throw e;
        }

    }





    @FXML
    void showRacketsButtonPressed(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {

            clientsTable.getItems().clear();
            ObservableList<Clients> racketData = racketDAO.showAllClients();
            populateRackets(racketData);

        } catch (SQLException e) {
            consoleTextArea.appendText("Error occurred while getting rackets from DB.\n");
            throw e;
        }

    }

    private void populateRackets(ObservableList<Clients> racketData) {
        clientsTable.setItems(racketData);
    }

    private void populateZysk(ObservableList<Statystyka> statystykaData) {
        clientsTable.setItems(statystykaData);
    }



    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert userTextField != null : "fx:id=\"userTextField\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert connectButton != null : "fx:id=\"connectButton\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert disconnectButton != null : "fx:id=\"disconnectButton\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert consoleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert racketNameToAddTextField != null : "fx:id=\"racketNameToAddTextField\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert addRacketButton != null : "fx:id=\"addRacketButton\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert selectRacketNameTextField != null : "fx:id=\"selectRacketNameTextField\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert selectRacketButton != null : "fx:id=\"selectRacketButton\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert showRacketsButton != null : "fx:id=\"showRacketsButton\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert clientsTable != null : "fx:id=\"racketTable\" was not injected: check your FXML file 'dbFX.fxml'.";
/*
        assert nameCol != null : "fx:id=\"nameCol\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert manufacturerCol != null : "fx:id=\"manufacturerCol\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert massCol != null : "fx:id=\"massCol\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert headSizeCol != null : "fx:id=\"headSizeCol\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert dominantColorCol != null : "fx:id=\"dominantColorCol\" was not injected: check your FXML file 'dbFX.fxml'.";
        assert priceUSDCol != null : "fx:id=\"priceUSDCol\" was not injected: check your FXML file 'dbFX.fxml'.";
*/

    }


}
