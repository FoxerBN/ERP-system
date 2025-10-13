package sk.foxer.erpstock.controller.stockin;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sk.foxer.erpstock.dao.stockin.StockInDao;
import sk.foxer.erpstock.model.stockin.StockIn;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class SupplierIncomesController {
    @FXML private TableView<StockIn> table;
    @FXML private TableColumn<StockIn, Integer> colId;
    @FXML private TableColumn<StockIn, String>  colDate;
    @FXML private TableColumn<StockIn, String>  colProduct;
    @FXML private TableColumn<StockIn, Double>  colQty;
    @FXML private TableColumn<StockIn, Double>  colUnitPrice;
    @FXML private TableColumn<StockIn, Double>  colTotal;

    private final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));          // ak je String
        colProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        // formát peňazí (voliteľne)
        colUnitPrice.setCellFactory(tc -> moneyCell());
        colTotal.setCellFactory(tc -> moneyCell());
        colQty.setCellFactory(tc -> numericCell());
    }

    /** zavolá SupplierDetailController po načítaní FXML */
    public void loadForSupplier(int supplierId) {
        List<StockIn> list = StockInDao.getBySupplierId(supplierId);
        table.getItems().setAll(list);
    }

    private TableCell<StockIn, Double> moneyCell() {
        return new TableCell<>() {
            @Override protected void updateItem(Double v, boolean empty) {
                super.updateItem(v, empty);
                setText(empty || v == null ? null : String.format("%.2f", v));
            }
        };
    }
    private TableCell<StockIn, Double> numericCell() {
        return new TableCell<>() {
            @Override protected void updateItem(Double v, boolean empty) {
                super.updateItem(v, empty);
                setText(empty || v == null ? null : String.format("%.2f", v));
            }
        };
    }
}
