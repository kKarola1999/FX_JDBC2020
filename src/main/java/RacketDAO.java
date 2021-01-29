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
