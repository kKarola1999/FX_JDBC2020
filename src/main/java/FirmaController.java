import controller.DBUtil;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Kontroler sceny FiramView
 */
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
    private Button sendButton;
    @FXML
    private TextField idSend;

    @FXML
    private TextField idTextPickup;

    @FXML
    private TextField podaiIdAutomatu;

    @FXML
    private TextField podajDate;


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

    @FXML
    private Button idPickup;





    private DBUtil dbUtil;
    private PackagesDAO packagesDAO;

    /**
     * Metoda obsłuhująca guizk logowania, sprawdza czy isnieje użytkownik o podanycm loginie i haśle.
     * Odblokowuje inne guziki jeśli logowanie się powiodło.
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void connectButtonPressed(ActionEvent event) throws SQLException, ClassNotFoundException {

        dbUtil = new DBUtil(userTextField.getText(), passwordTextField.getText(), consoleTextArea);
        packagesDAO = new PackagesDAO(dbUtil, consoleTextArea);

        dbUtil.dbConnect();

        consoleTextArea.appendText("Access granted for user \"" + userTextField.getText() + "\"." + "\n");
        connectButton.setDisable(true);
        disconnectButton.setDisable(false);
        btnStatystyka.setDisable(false);
        idPackages.setDisable(false);
        idPickup.setDisable(false);
        sendButton.setDisable(false);



    }

    /**
     * Metoda obługująca guzik wylogowywujący. Zrywa połączenie aplikacji z bazą danych. Blokuje inne guziki od zaloguj się.
     * @param event
     * @throws SQLException
     */
    @FXML
    void disconnectButtonPressed(ActionEvent event) throws SQLException {
        dbUtil.dbDisconnect();
        connectButton.setDisable(false);
        disconnectButton.setDisable(true);
        disconnectButton.setDisable(true);
        btnStatystyka.setDisable(true);
        idPackages.setDisable(true);
        idPickup.setDisable(true);
        sendButton.setDisable(true);
    }

    /**
     * Metoda obsługująca guzik statystyki, wyświeta dane z zysków dla podanego dnia oraz automatu.
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void onStatystykaClick(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {

            statystykaTable.getItems().clear();
            ObservableList<Statystyka> statystykaData = packagesDAO.showAllZysk(podajDate.getText(),podaiIdAutomatu.getText());
            populateZysk(statystykaData);

        } catch (SQLException | ClassNotFoundException e) {
            consoleTextArea.appendText("Error occurred while getting statystyka from DB.\n");
            throw e;
        }

    }

    /**
     * Metoda obsługująca guzik pokazywania paczek. Wyświetla dabne dotyczące paczek w widoku paczek.
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void onPackagesClick(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {

            packagesTable.getItems().clear();
            ObservableList<PackagesView> packagesData = packagesDAO.showAllPackagesView();
            populatePackages(packagesData);

        } catch (SQLException | ClassNotFoundException e) {
            consoleTextArea.appendText("Error occurred while getting paczki from DB.\n");
            throw e;
        }


    }

    /**
     *
     * @param event
     * Metoda obsługująca guzik zmiany statusu paczki na wysłaną.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void onSendButtonClick(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {

            if (!idSend.getText().equals(null)) {
                packagesDAO.updatePackages(idSend.getText());
                consoleTextArea.appendText("Update for " + idSend.getText() + "\n");

            }
        } catch (SQLException | ClassNotFoundException e) {
            consoleTextArea.appendText("Error occurred while updating packages.\n");
            throw e;
        }

    }

    /**
     * Metoda obsługującą guzik zmiany statusu paczki na gotową do odbioru.
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void onPickupButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {

            if (!idSend.getText().equals(null)) {
                packagesDAO.updatePackagesPickup(idTextPickup.getText());
                consoleTextArea.appendText("Update for " + idSend.getText() + "\n");

            }
        } catch (SQLException | ClassNotFoundException e) {
            consoleTextArea.appendText("Error occurred while updating packages.\n");
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
        assert sendButton != null : "fx:id=\"sendButton\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert idPickup != null : "fx:id=\"idPickup\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert idTextPickup != null : "fx:id=\"idTextPickup\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert podaiIdAutomatu != null : "fx:id=\"podaiIdAutomatu\" was not injected: check your FXML file 'FirmaView.fxml'.";
        assert podajDate != null : "fx:id=\"podajDate\" was not injected: check your FXML file 'FirmaView.fxml'.";
        disconnectButton.setDisable(true);
        btnStatystyka.setDisable(true);
        idPackages.setDisable(true);
        idPickup.setDisable(true);
        sendButton.setDisable(true);
    }
}
