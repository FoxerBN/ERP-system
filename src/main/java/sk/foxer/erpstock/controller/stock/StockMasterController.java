package sk.foxer.erpstock.controller.stock;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sk.foxer.erpstock.controller.MainController;

public class StockMasterController {

    private MainController mainController;
    private StockController stockController;

    @FXML private Button btnAddProduct;
    @FXML private Button btnRemoveProduct;
    @FXML private Button btnEditProduct;

    public void setControllers(MainController mainController, StockController stockController) {
        this.mainController = mainController;
        this.stockController = stockController;
    }

    @FXML
    private void handleAddProduct() {
        if (stockController != null) {
            stockController.startAddProduct();
        }
    }

    @FXML
    private void handleRemoveProduct() {
        if (stockController != null) {
            stockController.removeSelectedProduct();
        }
    }

    @FXML
    private void handleEditProduct() {
        if (stockController != null) {
            stockController.startEditSelectedProduct();
        }
    }
}
