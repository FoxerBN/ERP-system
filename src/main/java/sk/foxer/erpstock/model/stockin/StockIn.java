package sk.foxer.erpstock.model.stockin;

import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StockIn {
    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>(this, "date");
    private final StringProperty supplierName = new SimpleStringProperty(this, "supplierName");
    private final StringProperty productName = new SimpleStringProperty(this, "productName");
    private final DoubleProperty quantity = new SimpleDoubleProperty(this, "quantity");
    private final DoubleProperty unitPrice = new SimpleDoubleProperty(this, "unitPrice");
    private final DoubleProperty total = new SimpleDoubleProperty(this, "total");

    public StockIn(
        int id,
        LocalDate date,
        String supplierName,
        String productName,
        double quantity,
        double unitPrice,
        double total
    ) {
        setId(id);
        setDate(date);
        setSupplierName(supplierName);
        setProductName(productName);
        setQuantity(quantity);
        setUnitPrice(unitPrice);
        setTotal(total);
    }

    public int getId() { return id.get(); }
    public void setId(int value) { id.set(value); }
    public IntegerProperty idProperty() { return id; }

    public LocalDate getDate() { return date.get(); }
    public void setDate(LocalDate value) { date.set(value); }
    public ObjectProperty<LocalDate> dateProperty() { return date; }

    public String getSupplierName() { return supplierName.get(); }
    public void setSupplierName(String value) { supplierName.set(value); }
    public StringProperty supplierNameProperty() { return supplierName; }

    public String getProductName() { return productName.get(); }
    public void setProductName(String value) { productName.set(value); }
    public StringProperty productNameProperty() { return productName; }

    public double getQuantity() { return quantity.get(); }
    public void setQuantity(double value) { quantity.set(value); }
    public DoubleProperty quantityProperty() { return quantity; }

    public double getUnitPrice() { return unitPrice.get(); }
    public void setUnitPrice(double value) { unitPrice.set(value); }
    public DoubleProperty unitPriceProperty() { return unitPrice; }

    public double getTotal() { return total.get(); }
    public void setTotal(double value) { total.set(value); }
    public DoubleProperty totalProperty() { return total; }
}
