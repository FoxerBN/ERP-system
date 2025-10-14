package sk.foxer.erpstock.controller.stockout;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sk.foxer.erpstock.dao.stockout.StockOutDao;
import sk.foxer.erpstock.model.stockout.StockOut;
import sk.foxer.erpstock.util.PdfExportUtil;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class CustomerOutcomesController {

    @FXML private TableView<StockOut> table;
    @FXML private TableColumn<StockOut, Integer> colId;
    @FXML private TableColumn<StockOut, String>  colDate;
    @FXML private TableColumn<StockOut, String>  colProduct;
    @FXML private TableColumn<StockOut, Double>  colQty;
    @FXML private TableColumn<StockOut, Double>  colUnitPrice;
    @FXML private TableColumn<StockOut, Double>  colTotal;

    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        colQty.setCellFactory(tc -> numericCell());
        colUnitPrice.setCellFactory(tc -> moneyCell());
        colTotal.setCellFactory(tc -> moneyCell());

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    private void handleExportPdf() {
        ObservableList<StockOut> items = table.getItems();
        if (items == null || items.isEmpty()) {
            showInfo("Export", "Tabuľka je prázdna – nie je čo exportovať.");
            return;
        }

        try {
            Path savedFile = PdfExportUtil.exportStockOutTable(items);
            showInfo("Export", "PDF bolo uložené do: " + savedFile);
        } catch (IOException e) {
            e.printStackTrace();
            showError("Export zlyhal", "PDF sa nepodarilo vytvoriť.");
        }
    }

    public void loadForCustomer(int customerId) {
        List<StockOut> list = StockOutDao.getByCustomerId(customerId);
        table.getItems().setAll(list);
    }

    private TableCell<StockOut, Double> moneyCell() {
        return new TableCell<>() {
            @Override protected void updateItem(Double v, boolean empty) {
                super.updateItem(v, empty);
                setText(empty || v == null ? null : String.format("%.2f €", v));
            }
        };
    }

    private TableCell<StockOut, Double> numericCell() {
        return new TableCell<>() {
            @Override protected void updateItem(Double v, boolean empty) {
                super.updateItem(v, empty);
                setText(empty || v == null ? null : String.format("%.2f", v));
            }
        };
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.show();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.show();
    }
}
