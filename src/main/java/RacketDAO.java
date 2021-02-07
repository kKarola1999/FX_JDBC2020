import controller.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RacketDAO {

    private DBUtil dbUtil;
    private TextArea consoleTextArea;

    public RacketDAO(DBUtil dbUtil, TextArea consoleTextArea) {
        this.dbUtil = dbUtil;
        this.consoleTextArea = consoleTextArea;
    }

    private ObservableList<Clients> getClientsList(ResultSet rs) throws SQLException {

        ObservableList<Clients> clientList = FXCollections.observableArrayList();

        while (rs.next()) {

            Clients c = new Clients();
            c.setIdClient(rs.getInt("idClient"));
            c.setImieNazwisko(rs.getString("ImieNazwisko"));
            c.setAdres(rs.getString("adres"));
            c.setMobile_number(rs.getInt("mobile_number"));
            c.setEmail(rs.getString("email"));

            clientList.add(c);
            //clientList.add(a);
        }

        return clientList;
    }
    private ObservableList<Statystyka> getStatystykaList(ResultSet rs) throws SQLException {

        ObservableList<Statystyka> statystykaList = FXCollections.observableArrayList();

        while (rs.next()) {

            Statystyka s = new Statystyka();
            s.setIdAutomatNad(rs.getInt("idAutomatNad"));
            s.setSend_date(rs.getString("send_date"));
            s.setZysk_paczki(rs.getDouble("zysk_paczki"));



            statystykaList.add(s);
            //clientList.add(a);
        }

        return statystykaList;
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
            consoleTextArea.appendText("While searching rackets, an error occurred. \n");
            throw e;
        }

    }

    public ObservableList<Statystyka> showAllZysk() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM statystyka;";

        try {

            ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);

            ObservableList<Statystyka> clientList = getStatystykaList(resultSet);
            consoleTextArea.appendText(selectStmt);

            return clientList;


        } catch (SQLException e) {
            consoleTextArea.appendText("While searching rackets, an error occurred. \n");
            throw e;
        }

    }
/*
    public void insertRacket(String name) throws SQLException, ClassNotFoundException {

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


}
