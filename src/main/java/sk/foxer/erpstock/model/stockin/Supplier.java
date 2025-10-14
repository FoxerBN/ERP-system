package sk.foxer.erpstock.model.stockin;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Supplier {
    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final StringProperty name = new SimpleStringProperty(this, "name");
    private final StringProperty address = new SimpleStringProperty(this, "address");
    private final StringProperty contact = new SimpleStringProperty(this, "contact");

    public Supplier(int id, String name, String address, String contact) {
        setId(id);
        setName(name);
        setAddress(address);
        setContact(contact);
    }

    public int getId() { return id.get(); }
    public void setId(int value) { id.set(value); }
    public IntegerProperty idProperty() { return id; }

    public String getName() { return name.get(); }
    public void setName(String value) { name.set(value); }
    public StringProperty nameProperty() { return name; }

    public String getAddress() { return address.get(); }
    public void setAddress(String value) { address.set(value); }
    public StringProperty addressProperty() { return address; }

    public String getContact() { return contact.get(); }
    public void setContact(String value) { contact.set(value); }
    public StringProperty contactProperty() { return contact; }
}
