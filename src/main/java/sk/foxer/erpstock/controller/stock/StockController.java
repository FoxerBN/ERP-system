package sk.foxer.erpstock.controller.stock;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sk.foxer.erpstock.controller.MainController;
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
    private final ObservableList<Product> products = FXCollections.observableArrayList();
    private MainController mainController;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        colPurchase.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        colSale.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("currentStock"));

        tableProducts.setItems(products);
        refreshProducts();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void refreshProducts() {
        products.setAll(productDao.getAllProducts());
    }

    public void startAddProduct() {
        Product newProduct = new Product(0, "", "", "", 0.0, 0.0, 0);
        openProductEditor(newProduct, true);
    }

    public void startEditSelectedProduct() {
        Product selected = tableProducts.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("⚠️ Nie je vybraný žiadny produkt na úpravu.");
            return;
        }
        Product editableCopy = copyOf(selected);
        openProductEditor(editableCopy, false);
    }

    public void removeSelectedProduct() {
        Product selected = tableProducts.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("⚠️ Nie je vybraný žiadny produkt na odstránenie.");
            return;
        }
        boolean removed = productDao.deleteProduct(selected.getId());
        if (!removed) {
            System.out.println("⚠️ Produkt sa nepodarilo odstrániť.");
            return;
        }

        refreshProducts();
        tableProducts.getSelectionModel().clearSelection();
        if (mainController != null) {
            mainController.closeDetailPane();
        }
    }

    public void persistProduct(Product product, boolean isNew) {
        boolean success = isNew
                ? productDao.addProduct(product)
                : productDao.updateProduct(product);
        if (!success) {
            System.out.println("⚠️ Produkt sa nepodarilo uložiť.");
            return;
        }

        refreshProducts();
        selectProductById(product.getId());

        if (mainController != null) {
            mainController.closeDetailPane();
        }
    }

    private void openProductEditor(Product product, boolean isNew) {
        if (mainController != null) {
            mainController.showProductEdit(product, isNew, this);
        }
    }

    private Product copyOf(Product source) {
        return new Product(
                source.getId(),
                source.getCode(),
                source.getName(),
                source.getUnit(),
                source.getPurchasePrice(),
                source.getSalePrice(),
                source.getCurrentStock()
        );
    }

    private void selectProductById(int id) {
        if (id <= 0) {
            return;
        }
        for (Product product : products) {
            if (product.getId() == id) {
                tableProducts.getSelectionModel().select(product);
                tableProducts.scrollTo(product);
                break;
            }
        }
    }
}
