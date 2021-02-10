import javafx.beans.property.*;

public class PackagesView {
    /**
     * Klasa przechowująca dane z bazy danych, wykorzystywana do tworzenia widoków dla firmy w aplikacji.
     */

    private IntegerProperty idPackages;
    private StringProperty size;
    private StringProperty status;
    private DoubleProperty price;
    private StringProperty send_date;
    private StringProperty data_arrive;
    private StringProperty data_end;
    private StringProperty pickup_date;
    private IntegerProperty idAutomatOdb;
    private IntegerProperty idAutomatNad;

    public PackagesView() {
        idPackages = new SimpleIntegerProperty();
        size = new SimpleStringProperty();
        status = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
        send_date = new SimpleStringProperty();
        data_arrive = new SimpleStringProperty();
        data_end = new SimpleStringProperty();
        pickup_date = new SimpleStringProperty();

        idAutomatOdb = new SimpleIntegerProperty();

        idAutomatNad = new SimpleIntegerProperty();
    }

    public int getIdPackages() {
        return idPackages.get();
    }

    public IntegerProperty idPackagesProperty() {
        return idPackages;
    }

    public void setIdPackages(int idPackages) {
        this.idPackages.set(idPackages);
    }

    public String getSize() {
        return size.get();
    }

    public StringProperty sizeProperty() {
        return size;
    }

    public void setSize(String size) {
        this.size.set(size);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getSend_date() {
        return send_date.get();
    }

    public StringProperty send_dateProperty() {
        return send_date;
    }

    public void setSend_date(String send_date) {
        this.send_date.set(send_date);
    }

    public String getData_arrive() {
        return data_arrive.get();
    }

    public StringProperty data_arriveProperty() {
        return data_arrive;
    }

    public void setData_arrive(String data_arrive) {
        this.data_arrive.set(data_arrive);
    }

    public String getData_end() {
        return data_end.get();
    }

    public StringProperty data_endProperty() {
        return data_end;
    }

    public void setData_end(String data_end) {
        this.data_end.set(data_end);
    }

    public String getPickup_date() {
        return pickup_date.get();
    }

    public StringProperty pickup_dateProperty() {
        return pickup_date;
    }

    public void setPickup_date(String pickup_date) {
        this.pickup_date.set(pickup_date);
    }

    public int getIdAutomatOdb() {
        return idAutomatOdb.get();
    }

    public IntegerProperty idAutomatOdbProperty() {
        return idAutomatOdb;
    }

    public void setIdAutomatOdb(int idAutomatOdb) {
        this.idAutomatOdb.set(idAutomatOdb);
    }

    public int getIdAutomatNad() {
        return idAutomatNad.get();
    }

    public IntegerProperty idAutomatNadProperty() {
        return idAutomatNad;
    }

    public void setIdAutomatNad(int idAutomatNad) {
        this.idAutomatNad.set(idAutomatNad);
    }
}

