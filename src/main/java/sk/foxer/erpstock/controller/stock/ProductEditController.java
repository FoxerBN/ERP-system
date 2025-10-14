package sk.foxer.erpstock.controller.stock;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sk.foxer.erpstock.model.stock.Product;

public class ProductEditController {

    @FXML private Label lblTitle;
    @FXML private TextField txtCode;
    @FXML private TextField txtName;
    @FXML private TextField txtUnit;
    @FXML private TextField txtPurchasePrice;
    @FXML private TextField txtSalePrice;
    @FXML private TextField txtCurrentStock;

    private StockController stockController;
    private Product workingCopy;
    private boolean isNew;

    public void setContext(StockController stockController, Product product, boolean isNew) {
        this.stockController = stockController;
        this.isNew = isNew;
        this.workingCopy = product != null ? product : new Product(0, "", "", "", 0.0, 0.0, 0);
        populateFields();
    }

    private void populateFields() {
        if (lblTitle != null) {
            lblTitle.setText(isNew ? "Nový produkt" : "Úprava produktu");
        }
        txtCode.setText(workingCopy.getCode());
        txtName.setText(workingCopy.getName());
        txtUnit.setText(workingCopy.getUnit());
        txtPurchasePrice.setText(Double.toString(workingCopy.getPurchasePrice()));
        txtSalePrice.setText(Double.toString(workingCopy.getSalePrice()));
        txtCurrentStock.setText(Integer.toString(workingCopy.getCurrentStock()));
    }

    @FXML
    private void handleSave() {
        if (stockController == null) {
            return;
        }

        if (!applyFormValues()) {
            return;
        }

        stockController.persistProduct(workingCopy, isNew);
    }

    private boolean applyFormValues() {
        String code = txtCode.getText() != null ? txtCode.getText().trim() : "";
        String name = txtName.getText() != null ? txtName.getText().trim() : "";
        String unit = txtUnit.getText() != null ? txtUnit.getText().trim() : "";

        if (code.isEmpty() || name.isEmpty() || unit.isEmpty()) {
            System.out.println("⚠️ Kód, názov aj jednotka musia byť vyplnené.");
            return false;
        }

        double purchasePrice;
        double salePrice;
        int currentStock;

        try {
            purchasePrice = Double.parseDouble(txtPurchasePrice.getText().trim());
            salePrice = Double.parseDouble(txtSalePrice.getText().trim());
            currentStock = Integer.parseInt(txtCurrentStock.getText().trim());
        } catch (NumberFormatException ex) {
            System.out.println("⚠️ Skontroluj číselné polia (ceny a množstvo).");
            return false;
        }

        workingCopy.setCode(code);
        workingCopy.setName(name);
        workingCopy.setUnit(unit);
        workingCopy.setPurchasePrice(purchasePrice);
        workingCopy.setSalePrice(salePrice);
        workingCopy.setCurrentStock(currentStock);

        return true;
    }
}
