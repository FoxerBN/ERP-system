package sk.foxer.erpstock.model.stock;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Product {
    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final StringProperty code = new SimpleStringProperty(this, "code");
    private final StringProperty name = new SimpleStringProperty(this, "name");
    private final StringProperty unit = new SimpleStringProperty(this, "unit");
    private final DoubleProperty purchasePrice = new SimpleDoubleProperty(this, "purchasePrice");
    private final DoubleProperty salePrice = new SimpleDoubleProperty(this, "salePrice");
    private final IntegerProperty currentStock = new SimpleIntegerProperty(this, "currentStock");

    public Product(
        int id,
        String code,
        String name,
        String unit,
        double purchasePrice,
        double salePrice,
        int currentStock
    ) {
        setId(id);
        setCode(code);
        setName(name);
        setUnit(unit);
        setPurchasePrice(purchasePrice);
        setSalePrice(salePrice);
        setCurrentStock(currentStock);
    }

    public int getId() { return id.get(); }
    public void setId(int value) { id.set(value); }
    public IntegerProperty idProperty() { return id; }

    public String getCode() { return code.get(); }
    public void setCode(String value) { code.set(value); }
    public StringProperty codeProperty() { return code; }

    public String getName() { return name.get(); }
    public void setName(String value) { name.set(value); }
    public StringProperty nameProperty() { return name; }

    public String getUnit() { return unit.get(); }
    public void setUnit(String value) { unit.set(value); }
    public StringProperty unitProperty() { return unit; }

    public double getPurchasePrice() { return purchasePrice.get(); }
    public void setPurchasePrice(double value) { purchasePrice.set(value); }
    public DoubleProperty purchasePriceProperty() { return purchasePrice; }

    public double getSalePrice() { return salePrice.get(); }
    public void setSalePrice(double value) { salePrice.set(value); }
    public DoubleProperty salePriceProperty() { return salePrice; }

    public int getCurrentStock() { return currentStock.get(); }
    public void setCurrentStock(int value) { currentStock.set(value); }
    public IntegerProperty currentStockProperty() { return currentStock; }
}
