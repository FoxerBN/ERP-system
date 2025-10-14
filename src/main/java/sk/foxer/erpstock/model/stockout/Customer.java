package sk.foxer.erpstock.model.stockout;

import javafx.beans.property.*;

public class Customer {

    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty address;
    private final StringProperty contact;

    public Customer(int id, String name, String address, String contact) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.contact = new SimpleStringProperty(contact);
    }

    // Getters
    public int getId() { return id.get(); }
    public String getName() { return name.get(); }
    public String getAddress() { return address.get(); }
    public String getContact() { return contact.get(); }

    // Property getters (for TableView binding)
    public IntegerProperty idProperty() { return id; }
    public StringProperty nameProperty() { return name; }
    public StringProperty addressProperty() { return address; }
    public StringProperty contactProperty() { return contact; }

    // Setters
    public void setId(int id) { this.id.set(id); }
    public void setName(String name) { this.name.set(name); }
    public void setAddress(String address) { this.address.set(address); }
    public void setContact(String contact) { this.contact.set(contact); }
}
