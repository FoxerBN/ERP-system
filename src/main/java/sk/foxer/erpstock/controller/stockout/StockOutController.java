package sk.foxer.erpstock.controller.stockout;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sk.foxer.erpstock.controller.MainController;
import sk.foxer.erpstock.dao.stockout.CustomerDao;
import sk.foxer.erpstock.dao.stockout.StockOutDao;
import sk.foxer.erpstock.model.stockout.StockOut;
import sk.foxer.erpstock.model.stockout.Customer;

import java.util.List;

public class StockOutController {

    private MainController mainController;

    @FXML private TableView<StockOut> stockOutTable;
    @FXML private TableColumn<StockOut, Integer> colId;
    @FXML private TableColumn<StockOut, String> colDate;
    @FXML private TableColumn<StockOut, String> colCustomer;
    @FXML private TableColumn<StockOut, String> colProduct;
    @FXML private TableColumn<StockOut, Double> colQuantity;
    @FXML private TableColumn<StockOut, Double> colUnitPrice;
    @FXML private TableColumn<StockOut, Double> colTotalPrice;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        // Vyplň tabuľku dátami
        List<StockOut> list = StockOutDao.getAll();
        stockOutTable.getItems().setAll(list);

        // Nastavenie resize policy ako v stock/stock_in
        stockOutTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Kliknutie na riadok → otvorí detail zákazníka
        stockOutTable.setOnMouseClicked(event -> {
            if (event.getClickCount() != 1) return;

            StockOut selected = stockOutTable.getSelectionModel().getSelectedItem();
            if (selected == null) return;

            Customer customer = CustomerDao.getByName(selected.getCustomerName());
            if (customer == null) return;

            if (mainController != null) {
                mainController.showCustomerDetail(customer);
            }
        });
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
