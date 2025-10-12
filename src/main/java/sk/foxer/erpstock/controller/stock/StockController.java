package sk.foxer.erpstock.controller.stock;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sk.foxer.erpstock.dao.stock.ProductDao;
import sk.foxer.erpstock.model.stock.Product;

public class StockController {

    @FXML private TableView<Product> tableProducts;
    @FXML private TableColumn<Product, Integer> colId;
    @FXML private TableColumn<Product, String> colCode;
    @FXML private TableColumn<Product, String> colName;
    @FXML private TableColumn<Product, String> colUnit;
    @FXML private TableColumn<Product, Double> colPurchase;
    @FXML private TableColumn<Product, Double> colSale;
    @FXML private TableColumn<Product, Integer> colStock;

    private final ProductDao productDao = new ProductDao();

    @FXML
    public void initialize() {
        // prepojenie stĺpcov s atribútmi modelu Product
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        colPurchase.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        colSale.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("currentStock"));

        // načítanie dát z DB
        ObservableList<Product> products = FXCollections.observableArrayList(productDao.getAllProducts());
        tableProducts.setItems(products);
    }
}
