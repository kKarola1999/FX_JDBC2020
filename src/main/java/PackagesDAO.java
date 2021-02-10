import controller.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PackagesDAO {

    private DBUtil dbUtil;
    private TextArea consoleTextArea;

    public PackagesDAO(DBUtil dbUtil, TextArea consoleTextArea) {
        this.dbUtil = dbUtil;
        this.consoleTextArea = consoleTextArea;
    }

    public PackagesDAO() {
    }

    private ObservableList<Clients> getClientsList(ResultSet rs) throws SQLException {

        ObservableList<Clients> clientList = FXCollections.observableArrayList();

        while (rs.next()) {

            Clients c = new Clients();
            c.setIdClient(rs.getInt("idClient"));
            c.setImieNazwisko(rs.getString("ImieNazwisko"));
            c.setAdres(rs.getString("adres"));
            c.setEmail(rs.getString("email"));
            c.setMobile_number(rs.getInt("mobile_number"));

            clientList.add(c);
            //clientList.add(a);
        }

        return clientList;
    }

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
        //clientList.add(a);
        }

        return packageList;
    }

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



/*
    public ObservableList<Racket> searchRackets(String manuf) throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM rackets WHERE manufacturer LIKE '%" + manuf + "%';";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<Racket> rackets = getRacketList(resultSet);

            consoleTextArea.appendText(selectStmt + "\n");

            return rackets;

        } catch (SQLException e) {
            consoleTextArea.appendText("While searching a racket from '" + manuf + "' name, an error occurred. \n");
            throw e;
        }

    }

 */

    public ObservableList<Clients> showAllClients() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM clients;";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<Clients> clientList = getClientsList(resultSet);
            consoleTextArea.appendText(selectStmt);

            return clientList;


        } catch (SQLException e) {
            consoleTextArea.appendText("While searching clients, an error occurred. \n");
            throw e;
        }

    }

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


/*
    public void insertClient(String name) throws SQLException, ClassNotFoundException {

        StringBuilder sb = new StringBuilder("INSERT INTO rackets(model) VALUES('");
        sb.append(name);
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
