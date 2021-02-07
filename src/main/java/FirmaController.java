import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.DBUtil;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FirmaController {

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
    private TableView statystykaTable;

    @FXML
    private TableColumn<Statystyka, String> idAutNad;

    @FXML
    private TableColumn<Statystyka, String> inSendDate;

    @FXML
    private TableColumn<Statystyka, String> idZysk;

    @FXML
    private TableView packagesTable;

    @FXML
    private TableColumn<PackagesView, String> idPaczki;

    @FXML
    private TableColumn<PackagesView, String> status;

    @FXML
    private TableColumn<PackagesView, String> size;

    @FXML
    private TableColumn<PackagesView, String> sendDate;

    @FXML
    private TableColumn<PackagesView, String> dataArrive;

    @FXML
    private TableColumn<PackagesView, String> dataEnd;

    @FXML
    private TableColumn<PackagesView, String> automatOdb;

    @FXML
    private TableColumn<PackagesView, String> automatNad;

    @FXML
    private TableColumn<PackagesView, String> pickupDate;


    @FXML
    private Button btnStatystyka;

    @FXML
    private Button idPackages;






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



    }

    @FXML
    void disconnectButtonPressed(ActionEvent event) throws SQLException {
        dbUtil.dbDisconnect();
        connectButton.setDisable(false);
        disconnectButton.setDisable(true);

    }

    @FXML
    void onStatystykaClick(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {

            statystykaTable.getItems().clear();
            ObservableList<Statystyka> statystykaData = racketDAO.showAllZysk();
            populateZysk(statystykaData);

        } catch (SQLException | ClassNotFoundException e) {
            consoleTextArea.appendText("Error occurred while getting rackets from DB.\n");
            throw e;
        }

    }

    @FXML
    void onPackagesClick(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {

            packagesTable.getItems().clear();
            ObservableList<PackagesView> packagesData = racketDAO.showAllPackagesView();
            populatePackages(packagesData);

        } catch (SQLException | ClassNotFoundException e) {
            consoleTextArea.appendText("Error occurred while getting rackets from DB.\n");
            throw e;
        }


    }


    private void populateZysk(ObservableList<Statystyka> statystykaData) {
        statystykaTable.setItems(statystykaData);
    }
    private void populatePackages(ObservableList<PackagesView> packagesData) {
        packagesTable.setItems(packagesData);
    }


    @FXML
    void initialize() {
        assert userTextField != null : "fx:id=\"userTextField\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert connectButton != null : "fx:id=\"connectButton\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert disconnectButton != null : "fx:id=\"disconnectButton\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert consoleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert statystykaTable != null : "fx:id=\"statystykaTable\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert idAutNad != null : "fx:id=\"idAutNad\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert inSendDate != null : "fx:id=\"inSendDate\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert idZysk != null : "fx:id=\"idZysk\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert packagesTable != null : "fx:id=\"packagesTable\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert idPaczki != null : "fx:id=\"idPaczki\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert size != null : "fx:id=\"size\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert sendDate != null : "fx:id=\"sendDate\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert dataArrive != null : "fx:id=\"dataArrive\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert dataEnd != null : "fx:id=\"dataEnd\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert automatOdb != null : "fx:id=\"automatOdb\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert automatNad != null : "fx:id=\"automatNad\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert pickupDate != null : "fx:id=\"pickupDate\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert btnStatystyka != null : "fx:id=\"btnStatystyka\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert idPackages != null : "fx:id=\"idPackages\" was not injected: check your FXML file 'FirmaView.fxml'.";


    }
}
