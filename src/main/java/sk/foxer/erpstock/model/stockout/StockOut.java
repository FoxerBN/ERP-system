package sk.foxer.erpstock.model.stockout;

import javafx.beans.property.*;
import java.time.LocalDate;

public class StockOut {

    private final IntegerProperty id;
    private final ObjectProperty<LocalDate> date;
    private final StringProperty customerName;
    private final StringProperty productName;
    private final DoubleProperty quantity;
    private final DoubleProperty unitPrice;
    private final DoubleProperty totalPrice;

    public StockOut(int id, LocalDate date, String customerName, String productName,
                    double quantity, double unitPrice, double totalPrice) {
        this.id = new SimpleIntegerProperty(id);
        this.date = new SimpleObjectProperty<>(date);
        this.customerName = new SimpleStringProperty(customerName);
        this.productName = new SimpleStringProperty(productName);
        this.quantity = new SimpleDoubleProperty(quantity);
        this.unitPrice = new SimpleDoubleProperty(unitPrice);
        this.totalPrice = new SimpleDoubleProperty(totalPrice);
    }

    // Getters
    public int getId() { return id.get(); }
    public LocalDate getDate() { return date.get(); }
    public String getCustomerName() { return customerName.get(); }
    public String getProductName() { return productName.get(); }
    public double getQuantity() { return quantity.get(); }
    public double getUnitPrice() { return unitPrice.get(); }
    public double getTotalPrice() { return totalPrice.get(); }

    // Property getters for JavaFX bindings
    public IntegerProperty idProperty() { return id; }
    public ObjectProperty<LocalDate> dateProperty() { return date; }
    public StringProperty customerNameProperty() { return customerName; }
    public StringProperty productNameProperty() { return productName; }
    public DoubleProperty quantityProperty() { return quantity; }
    public DoubleProperty unitPriceProperty() { return unitPrice; }
    public DoubleProperty totalPriceProperty() { return totalPrice; }
}
