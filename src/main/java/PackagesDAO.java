import controller.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Klasa przechowująca sentencje SQL, tworzaąca obiekty klasy: SenderView, ReciverView, Statystyki, PackagesView
 */
public class PackagesDAO {

    private DBUtil dbUtil;
    private TextArea consoleTextArea;

    public PackagesDAO(DBUtil dbUtil, TextArea consoleTextArea) {
        this.dbUtil = dbUtil;
        this.consoleTextArea = consoleTextArea;
    }

    public PackagesDAO() {
    }

    /**
     * Metoda tworząca liste odbiorców
     * @param rs
     * @return lista dodanych odbiorców
     * @throws SQLException
     */
    private ObservableList<ClientBase> getClientBaseList(ResultSet rs) throws SQLException {

        ObservableList<ClientBase> clientBaseList = FXCollections.observableArrayList();

        while (rs.next()) {

            ClientBase cb = new ClientBase();
            cb.setIdClient(rs.getInt("idClient"));
            cb.setImieNazwisko(rs.getString("ImieNazwisko"));

            clientBaseList.add(cb);
            //clientList.add(a);
        }

        return clientBaseList;
    }

    /**
     * Metoda tworząca listę obiektów typu SenderView
     * @param rs
     * @return listę obiektów klasy SenderView
     * @throws SQLException
     */
    private  ObservableList<SenderView> getSenderList(ResultSet rs) throws SQLException{
        ObservableList<SenderView> senderList = FXCollections.observableArrayList();
        while (rs.next()){
            SenderView s =  new SenderView();
            s.setIdPaczki(rs.getInt("id_paczki"));
            s.setDataNadania(rs.getString("data_nadania"));
            s.setDataOdebrania(rs.getString("data_odebrania"));
            s.setIdAutomatuNadawczego(rs.getInt("id_a_nadawcy"));
            s.setAdresNadania(rs.getString("adres_nadania"));
            s.setOdbiorca(rs.getInt("obiorca"));
            s.setAdresOdbiorcy(rs.getString("adres_odbiorcy"));
            s.setIdAutomatuOdbiorczego(rs.getInt("id_a_odbiorczego"));
            s.setAdresOdbiorcy(rs.getString("adres_odbioru"));
            senderList.add(s);
        }
        return  senderList;
    }

    /**
     * Metoda tworząca listę obiektów klasy Statystyka
     * @param rs
     * @return listę obiektów statystykaList
     * @throws SQLException
     */
    private ObservableList<Statystyka> getStatystykaList(ResultSet rs) throws SQLException {

        ObservableList<Statystyka> statystykaList = FXCollections.observableArrayList();

        while (rs.next()) {

            Statystyka s = new Statystyka();
            s.setIdAutomatNad(rs.getInt("idAutomatNad"));
            s.setSend_date(rs.getString("count(idPackages)"));
            s.setZysk_paczki(rs.getDouble("sum(zysk_paczki)"));



            statystykaList.add(s);
            //clientList.add(a);
        }

        return statystykaList;
    }

    /**
     * Metoda tworząca listę obiektów klasy PackagesView
     * @param rs
     * @return listę obiektów packahesList
     * @throws SQLException
     */
    private ObservableList<PackagesView> getPackagesList(ResultSet rs) throws SQLException {

        ObservableList<PackagesView> packageList = FXCollections.observableArrayList();

        while (rs.next()) {

            PackagesView p = new PackagesView();
            p.setIdPackages(rs.getInt("id_paczki"));
            p.setStatus(rs.getString("status_paczki"));
            p.setSize(rs.getString("rozmiarPaczki"));
            p.setSend_date(rs.getString("dataWysłania"));
            p.setData_arrive(rs.getString("dataDostawy"));
            p.setData_end(rs.getString("ostatecznaDataOdebraniaPaczki"));
            p.setIdAutomatNad(rs.getInt("id_automatu_nadawczego"));
            p.setIdAutomatOdb(rs.getInt("id_automatu_odbioreczego"));
            p.setPickup_date(rs.getString("dataOdebrania"));



            packageList.add(p);

        }

        return packageList;
    }
    /**
     * Metoda tworząca listę obiektów klasy ReciverView
     * @param rs
     * @return listę obiektów reciverViewList
     * @throws SQLException
     */
    private ObservableList<ReceiverView> getReceiverViewList(ResultSet rs) throws SQLException {

        ObservableList<ReceiverView> receiverViewList = FXCollections.observableArrayList();

        while (rs.next()) {

            ReceiverView r = new ReceiverView();
            r.setIdPackages(rs.getInt("id_paczki"));
            r.setSend_date(rs.getString("data_nadania"));
            r.setData_end(rs.getString("ostatni_termin"));
            r.setIdClientNad(rs.getInt("nadawca"));
            r.setAdresNadawcy(rs.getString("adres_nadawcy"));
            r.setIdAutomatOdb(rs.getInt("id_a_odbiorczego"));
            r.setAdresAutomatuOdbiorczego(rs.getString("adres_odbioru"));




            receiverViewList.add(r);

        }

        return receiverViewList;
    }


    /**
     * Metoda zawierająca zapytanie do bazy danych pobierająca i przekazująca je do metody worzącej listę obiektów.
     * @param idName
     * @return listę obiektów stworzonych przez metode get..
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ObservableList<SenderView> showAllSender (String idName) throws SQLException, ClassNotFoundException {


        StringBuilder sb = new StringBuilder("SELECT * FROM widok_nadawcy where nadawca='");
        sb.append(idName);
        sb.append("';");
        String insertStmt = sb.toString();
        try{
            ResultSet resultSet = dbUtil.dbExecuteQuery(insertStmt);
            ObservableList<SenderView>senderList =  getSenderList(resultSet);
            consoleTextArea.appendText("");

            return  senderList;
        } catch (SQLException | ClassNotFoundException e) {
            consoleTextArea.appendText("While searching sender, an error occurred. \n");
            throw e;
        }
    }

    /**
     * Metoda zawierająca zapytanie do bazy danych pobierająca i przekazująca je do metody worzącej listę obiektów.
     * @param date
     * @param idAutomatu
     * @return listę obiektów stworzonych przez metode get..
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public ObservableList<Statystyka> showAllZysk(String date, String idAutomatu) throws SQLException, ClassNotFoundException {

        StringBuilder sb = new StringBuilder("select distinct(idAutomatNad), count(idPackages),sum(zysk_paczki) from statystyka where idAutomatNad='");
        sb.append(idAutomatu);
        sb.append("'and send_date='");
        sb.append(date);
        sb.append("';");
        String insertStmt = sb.toString();
        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(insertStmt);

            ObservableList<Statystyka> clientList = getStatystykaList(resultSet);
            consoleTextArea.appendText(insertStmt);

            return clientList;


        } catch (SQLException e) {
            consoleTextArea.appendText("While searching statystyka, an error occurred. \n");
            throw e;
        }

    }

    /**
     * Metoda zawierająca zapytanie do bazy danych pobierająca i przekazująca je do metody worzącej listę obiektów.
     * @return listę obiektów stworzonych przez metode get..
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ObservableList<PackagesView> showAllPackagesView() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM widok_paczek;";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<PackagesView> packagesList = getPackagesList(resultSet);
            consoleTextArea.appendText(selectStmt);

            return packagesList;


        } catch (SQLException e) {
            consoleTextArea.appendText("While searching widok paczek, an error occurred. \n");
            throw e;
        }

    }

    /**
     * Metoda zawierająca sentencję SQL, zmienia status paczki wysłąnej na paczke gotową do odbioru.
     * @param name
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public void updatePackages(String name) throws SQLException, ClassNotFoundException {


        StringBuilder sb = new StringBuilder("update packages set status='send' where idPackages='");
        sb.append(name);
        sb.append("';");
        String insertStmt = sb.toString();
        try {

            dbUtil.dbExecuteUpdate(insertStmt);
            consoleTextArea.appendText(insertStmt + "\n");

        } catch (SQLException e) {
            consoleTextArea.appendText("Error occurred while update Operation.");
            throw e;
        }
    }

    /**
     * Metoda zwierajaća sentencję SQL, zmieniająca date odbioru paczki na datę aktualną.
     * @param name
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void updatePickUp(String name) throws SQLException, ClassNotFoundException {


        StringBuilder sb = new StringBuilder("update packages set pickup_date=curdate() where idPackages='");
        sb.append(name);
        sb.append("';");
        String insertStmt = sb.toString();
        try {

            dbUtil.dbExecuteUpdate(insertStmt);
            consoleTextArea.appendText(insertStmt + "\n");

        } catch (SQLException e) {
            consoleTextArea.appendText("Error occurred while update Operation.");
            throw e;
        }
    }

    /**
     * Metoda aktualizująca rekordy tabeli paczek.
     * @param name
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void updatePackagesPickup(String name) throws SQLException, ClassNotFoundException {


        StringBuilder sb = new StringBuilder("update packages set status='pickup',data_arrive=curdate(), data_end=date_add(curdate(),INTERVAL 10 DAY) where idPackages='");
        sb.append(name);
        sb.append("';");
        String insertStmt = sb.toString();
        try {

            dbUtil.dbExecuteUpdate(insertStmt);
            consoleTextArea.appendText(insertStmt + "\n");

        } catch (SQLException e) {
            consoleTextArea.appendText("Error occurred while update Operation.");
            throw e;
        }
    }

    /**
     * Metoda zawierająca zapytanie do bazy danych pobierająca i przekazująca je do metody worzącej listę obiektów.
     * @param idName
     * @return listę obietków stworzonych przez metodę get.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ObservableList<ReceiverView> showAllReceiverView(String idName) throws SQLException, ClassNotFoundException {



        StringBuilder sb = new StringBuilder("SELECT * FROM widok_odbiorcy where odbiorca='");
        sb.append(idName);
        sb.append("';");
        String insertStmt = sb.toString();

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(insertStmt);

            ObservableList<ReceiverView> receiverViewList = getReceiverViewList(resultSet);
            consoleTextArea.appendText(insertStmt);

            return receiverViewList;


        } catch (SQLException e) {
            consoleTextArea.appendText("While searching widok paczek, an error occurred. \n");
            throw e;
        }
    }

    /**
     * Metoda zawierająca zapytanie do bazy danych pobierająca i przekazująca je do metody worzącej listę obiektów.
     * @return listę obietków stworzonych przez metodę get.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ObservableList<ClientBase> showClientBase() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM clientBase;";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<ClientBase> ClientBaseList = getClientBaseList(resultSet);
            consoleTextArea.appendText(selectStmt);

            return ClientBaseList;


        } catch (SQLException e) {
            consoleTextArea.appendText("While searching widok bazy klientów, an error occurred. \n");
            throw e;
        }
    }


    /**
     * Metoda dodająca nowe rekordy do tabeli klientów
     * @param id
     * @param imieNazwisko
     * @param adres
     * @param email
     * @param number
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void insertClient(String id, String imieNazwisko, String adres, String email,String number ) throws SQLException, ClassNotFoundException {

        StringBuilder sb = new StringBuilder("INSERT INTO clients VALUES('");
        sb.append(id);
        sb.append("','");
        sb.append(imieNazwisko);
        sb.append("','");
        sb.append(adres);
        sb.append("','");
        sb.append(email);
        sb.append("','");
        sb.append(number);
        sb.append("');");
        String insertStmt = sb.toString();

        try {

            dbUtil.dbExecuteUpdate(insertStmt);
            consoleTextArea.appendText(insertStmt + "\n");

        } catch (SQLException e) {
            consoleTextArea.appendText("Error occurred while INSERT Operation.");
            throw e;
        }

    }

    /**
     * Metoda dodająca nowe rekordy do tabeli paczek.
     * @param size
     * @param idOdb
     * @param idAOd
     * @param idCliNad
     * @param idANad
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void insertNewPack(String size, String idOdb, String idAOd, String idCliNad,String idANad ) throws SQLException, ClassNotFoundException {

            StringBuilder sb = new StringBuilder("INSERT INTO packages VALUES((select count(id_paczki) from widok_paczek)+9,'");
            sb.append(size);
            sb.append("',null, curdate(), null, null, null,'");
            sb.append(idOdb);
            sb.append("','");
            sb.append(idAOd);
            sb.append("','");
            sb.append(idCliNad);
            sb.append("','");
            sb.append(idANad);
            sb.append("');");
            String insertStmt = sb.toString();

            try {

                dbUtil.dbExecuteUpdate(insertStmt);
                consoleTextArea.appendText(insertStmt + "\n");

            } catch (SQLException e) {
                consoleTextArea.appendText("Error occurred while INSERT Operation.");
                throw e;
            }

        }


    }
