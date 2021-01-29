import javafx.beans.property.*;


public class Slots {

    private IntegerProperty idSlot;
    private StringProperty size;
    private StringProperty status;
    private IntegerProperty idAutomat;

    public Slots() {
        idSlot = new SimpleIntegerProperty();
        size = new SimpleStringProperty();
        status = new SimpleStringProperty();
        idAutomat = new SimpleIntegerProperty();
    }

    public int getIdSlot() {
        return idSlot.get();
    }

    public IntegerProperty idSlotProperty() {
        return idSlot;
    }

    public void setIdSlot(int idSlot) {
        this.idSlot.set(idSlot);
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

    public int getIdAutomat() {
        return idAutomat.get();
    }

    public IntegerProperty idAutomatProperty() {
        return idAutomat;
    }

    public void setIdAutomat(int idAutomat) {
        this.idAutomat.set(idAutomat);
    }
}

