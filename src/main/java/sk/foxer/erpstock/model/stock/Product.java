package sk.foxer.erpstock.model.stock;

public class Product {
    private final int id;
    private final String code;
    private final String name;
    private final String unit;
    private final double purchasePrice;
    private final double salePrice;
    private final int currentStock;

    public Product(int id, String code, String name, String unit, double purchasePrice, double salePrice, int currentStock) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.unit = unit;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.currentStock = currentStock;
    }

    // Gettre a settre
    public int getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getUnit() { return unit; }
    public double getPurchasePrice() { return purchasePrice; }
    public double getSalePrice() { return salePrice; }
    public int getCurrentStock() { return currentStock; }
}
