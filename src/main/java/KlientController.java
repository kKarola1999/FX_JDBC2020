import controller.DBUtil;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Kontroler sceny KlientView
 */
public class KlientController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField userTextField;
    @FXML
    private TextField inputUserID;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button connectButton;

    @FXML
    private Button btnShowPack;

    @FXML
    private Button btnSignIn;

    @FXML
    private Button disconnectButton;

    @FXML
    private TextArea consoleTextArea;

    @FXML
    private ImageView imageCilentView;

    @FXML
    private TableView nadwcaTabel;

    @FXML
    private TableColumn<SenderView, String> colNad1;

    @FXML
    private TableColumn<SenderView, String> colNad2;

    @FXML
    private TableColumn<SenderView, String> colNad3;

    @FXML
    private TableColumn<SenderView, String> colNad4;

    @FXML
    private TableColumn<SenderView, String> colNad5;

    @FXML
    private TableColumn<SenderView, String> colNad6;

    @FXML
    private TableColumn<SenderView, String> colNad7;

    @FXML
    private TableColumn<SenderView, String> colNad8;

    @FXML
    private TableView paczkiOdebraneTable;

    @FXML
    private TableColumn<ReceiverView, String> colOdb1;

    @FXML
    private TableColumn<ReceiverView, String> colOdb2;

    @FXML
    private TableColumn<ReceiverView, String> colOdb3;

    @FXML
    private TableColumn<ReceiverView, String> colOdb4;

    @FXML
    private TableColumn<ReceiverView, String> colOdb5;

    @FXML
    private TableColumn<ReceiverView, String> colOdb6;

    @FXML
    private TableColumn<ReceiverView, String> colOdb7;

    @FXML
    private TextField inputAdrOdb;

    @FXML
    private TextField inputOdbiorca;

    @FXML
    private Spinner<String> spinner;

    @FXML
    private TextField inputPaczNad;

    @FXML
    private Button btnNadajPaczke;
    @FXML
    private TextField inputSize;

    @FXML
    private Button btnPickUp;
    @FXML
    private TextField idPickUp;

    @FXML
    private TextField idNadawcy;

    @FXML
    private Button idClientBase;

    public static DBUtil dbUtil;
    public static PackagesDAO packagesDAO;

    public DBUtil getDbUtil() {
        return dbUtil;
    }

    public PackagesDAO getRacketDAO() {
        return packagesDAO;

    }

    /**
     * Metoda służaca za obsługe guzika zaloguj się. Sprawdza czy podane dane logowania są prawidłowe
     * jeśli tak odblokowuje przyciski innych funkcji i łączy sie z bazą.
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
        btnShowPack.setDisable(false);
        btnNadajPaczke.setDisable(false);
        btnPickUp.setDisable(false);
        idClientBase.setDisable(false);
        btnSignIn.setDisable(false);

    }


    /**
     * Metoda rozłąćzająca aplikację z bazą danych oraz blokująca inne guziki oprócz zaloguj się.
     * @param event
     * @throws SQLException
     */
    @FXML
    void disconnectButtonPressed(ActionEvent event) throws SQLException {

        dbUtil.dbDisconnect();
        connectButton.setDisable(false);
        disconnectButton.setDisable(true);
        btnShowPack.setDisable(true);
        btnNadajPaczke.setDisable(true);
        btnPickUp.setDisable(true);
        idClientBase.setDisable(true);


    }

    /**
     * Metoda obsługująca guzik dodawania nowego odbiorcy. Otwiera osobne okno.
     * @param event
     */
    @FXML
    void onBtnSignIn(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("sign_in.fxml"), resources);
            Stage stage = new Stage();
            stage.setTitle("Rejestracja");
            stage.setScene(new Scene(root, 238, 200));
            stage.show();
            // Hide this current window (if this is what you want)
//            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda obsługująca guzik pokazywania paczek. Po naciśnięciu guzika są piokazywane paczki dla konkretengo numeru
     * ID klienta.
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
     @FXML
    void onBtnShowPack(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {

            paczkiOdebraneTable.getItems().clear();
            nadwcaTabel.getItems().clear();
            ObservableList<ReceiverView> receiverViewData = packagesDAO.showAllReceiverView(inputUserID.getText());
            populateReceiverView(receiverViewData);
            ObservableList<SenderView> senderData = packagesDAO.showAllSender(inputUserID.getText());
            populateSenderView(senderData);


        } catch (SQLException | ClassNotFoundException e) {
            consoleTextArea.appendText("Error occurred while getting paczki from DB.\n");
            throw e;
        }

    }

    /**
     * Metoda obśługująca guzik spisu odbiorców, otwiera osbne okno ze spisem dodanych odbiorców.
     * @param event
     */
    @FXML
    void onClientBaseClick(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("ClientBase.fxml"), resources);
            Stage stage = new Stage();
            stage.setTitle("Rejestracja");
            stage.setScene(new Scene(root, 400, 400));
            stage.show();
            // Hide this current window (if this is what you want)
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void populateReceiverView(ObservableList<ReceiverView> receiverViewData) {
        paczkiOdebraneTable.setItems(receiverViewData);

    } private void populateSenderView(ObservableList<SenderView> senderViews) {
        nadwcaTabel.setItems(senderViews);
    }


    @FXML
    void onBtnNadajPaczke(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            packagesDAO.insertNewPack(inputSize.getText(),inputOdbiorca.getText(),inputAdrOdb.getText(),inputUserID.getText(),inputPaczNad.getText());

        }catch (SQLException e){
            consoleTextArea.appendText("Error occurred while INSERT Operation.");
            throw  e;
        }

    }
    @FXML
    void onBtnPickUp(ActionEvent event) throws SQLException, ClassNotFoundException {

        try{
            KlientController.packagesDAO.updatePickUp(idPickUp.getText());
        }catch (SQLException e){
            consoleTextArea.appendText("Error occurred while update Operation.");
            throw e;
        }
    }



    @FXML
    void initialize() {
        assert userTextField != null : "fx:id=\"userTextField\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert btnShowPack != null : "fx:id=\"btnShowPack\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert connectButton != null : "fx:id=\"connectButton\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert btnSignIn != null : "fx:id=\"btnSignIn\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert nadwcaTabel != null : "fx:id=\"nadwcaTabel\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert disconnectButton != null : "fx:id=\"disconnectButton\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert consoleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert imageCilentView != null : "fx:id=\"imageCilentView\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colNad1 != null : "fx:id=\"colNad1\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colNad2 != null : "fx:id=\"colNad2\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colNad3 != null : "fx:id=\"colNad3\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colNad4 != null : "fx:id=\"colNad4\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colNad5 != null : "fx:id=\"colNad5\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colNad6 != null : "fx:id=\"colNad6\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colNad7 != null : "fx:id=\"colNad7\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colNad8 != null : "fx:id=\"colNad8\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colOdb1 != null : "fx:id=\"colOdb1\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colOdb2 != null : "fx:id=\"colOdb2\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colOdb3 != null : "fx:id=\"colOdb3\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colOdb4 != null : "fx:id=\"colOdb4\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colOdb5 != null : "fx:id=\"colOdb5\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colOdb6 != null : "fx:id=\"colOdb6\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colOdb7 != null : "fx:id=\"colOdb7\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert inputAdrOdb != null : "fx:id=\"inputAdrOdb\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert inputOdbiorca != null : "fx:id=\"inputOdbiorca\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert spinner != null : "fx:id=\"spinner\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert inputPaczNad != null : "fx:id=\"inputPaczNad\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert btnNadajPaczke != null : "fx:id=\"btnNadajPaczke\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert paczkiOdebraneTable != null : "fx:id=\"paczkiOdebraneTable\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert idPickUp != null : "fx:id=\"idPickUp\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert btnPickUp != null : "fx:id=\"btnPickUp\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert idNadawcy != null : "fx:id=\"idNadawcy\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert inputSize != null : "fx:id=\"inputSize\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert idClientBase != null : "fx:id=\"idClientBase\" was not injected: check your FXML file 'KlientView.fxml'.";

        disconnectButton.setDisable(true);
        btnShowPack.setDisable(true);
        btnNadajPaczke.setDisable(true);
        btnPickUp.setDisable(true);
        idClientBase.setDisable(true);
        btnSignIn.setDisable(true);

    }
}