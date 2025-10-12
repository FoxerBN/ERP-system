package sk.foxer.erpstock.model.stockin;

import java.time.LocalDate;

public class StockIn {
    private int id;
    private LocalDate date;
    private String supplierName;
    private String productName;
    private double quantity;
    private double unitPrice;
    private double total;

    public StockIn(int id, LocalDate date, String supplierName, String productName,
                   double quantity, double unitPrice, double total) {
        this.id = id;
        this.date = date;
        this.supplierName = supplierName;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public int getId() { return id; }
    public LocalDate getDate() { return date; }
    public String getSupplierName() { return supplierName; }
    public String getProductName() { return productName; }
    public double getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    public double getTotal() { return total; }
}
