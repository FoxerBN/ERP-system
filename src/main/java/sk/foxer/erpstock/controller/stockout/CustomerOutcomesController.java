package sk.foxer.erpstock.controller.stockout;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sk.foxer.erpstock.dao.stockout.StockOutDao;
import sk.foxer.erpstock.model.stockout.StockOut;
import sk.foxer.erpstock.util.HandleExportUtil;
import sk.foxer.erpstock.util.PdfExportUtil;

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

    }

    @FXML
    private void handleExportPdf() {
        HandleExportUtil.handleExportPdf(table, PdfExportUtil::exportStockOutTable);
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
}
