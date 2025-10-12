package sk.foxer.erpstock.model.stockin;

public class Supplier {
    private final int id;
    private final String name;
    private final String address;
    private final String contact;

    public Supplier(int id, String name, String address, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getContact() { return contact; }
}
