package sk.foxer.erpstock.controller.stockin;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sk.foxer.erpstock.controller.MainController;
import sk.foxer.erpstock.dao.stockin.StockInDao;
import sk.foxer.erpstock.dao.stockin.SupplierDao;
import sk.foxer.erpstock.model.stockin.StockIn;


import java.util.List;

public class StockInController {
    private MainController mainController;
    @FXML private TableView<StockIn> stockInTable;
    @FXML private TableColumn<StockIn, Integer> colId;
    @FXML private TableColumn<StockIn, String> colDate;
    @FXML private TableColumn<StockIn, String> colSupplier;
    @FXML private TableColumn<StockIn, String> colProduct;
    @FXML private TableColumn<StockIn, Double> colQuantity;
    @FXML private TableColumn<StockIn, Double> colUnitPrice;
    @FXML private TableColumn<StockIn, Double> colTotal;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        StockInDao dao = new StockInDao();
        List<StockIn> list = dao.getAll();
        stockInTable.getItems().setAll(list);

        stockInTable.setOnMouseClicked(event -> {
            if (event.getClickCount() != 1) return;

            StockIn selected = stockInTable.getSelectionModel().getSelectedItem();
            if (selected == null) return;

            var supplier = SupplierDao.getByName(selected.getSupplierName());
            if (supplier == null) return;

            if (mainController != null) {
                mainController.showSupplierDetail(supplier);
            }


        });

    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
