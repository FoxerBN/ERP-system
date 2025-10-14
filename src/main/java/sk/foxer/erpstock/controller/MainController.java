package sk.foxer.erpstock.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import sk.foxer.erpstock.controller.stock.ProductEditController;
import sk.foxer.erpstock.controller.stock.StockController;
import sk.foxer.erpstock.controller.stock.StockMasterController;
import sk.foxer.erpstock.controller.stockin.StockInController;
import sk.foxer.erpstock.controller.stockin.SupplierDetailController;
import sk.foxer.erpstock.controller.stockout.CustomerDetailController;
import sk.foxer.erpstock.controller.stockout.StockOutController;
import sk.foxer.erpstock.model.stock.Product;
import sk.foxer.erpstock.model.stockin.Supplier;
import sk.foxer.erpstock.model.stockout.Customer;
import sk.foxer.erpstock.util.UIHelper;

import java.io.IOException;

public class MainController {

    @FXML
    private AnchorPane masterPane;
    @FXML
    private AnchorPane centerPane;
    @FXML
    private AnchorPane detailPane;
    @FXML
    private ToolbarController toolbarController;


    @FXML
    public void initialize() {
        if (toolbarController != null) {
            toolbarController.setMainController(this);
        } else {
            System.out.println("⚠️ ToolbarController je null!");
        }
        masterPane.getProperties().put("mainController", this);

        showStockView();
    }


    // ====== VIEW PREPINANIE ======
    public void showStockView() {
        closeDetail(); // zavri detail, ak je zobrazený
        try {
            FXMLLoader centerLoader = new FXMLLoader(getClass().getResource(
                    "/sk/foxer/erpstock/view/layout/stock.fxml"
            ));
            AnchorPane centerView = centerLoader.load();
            StockController stockController = centerLoader.getController();
            stockController.setMainController(this);

            centerPane.getChildren().setAll(centerView);
            AnchorPane.setTopAnchor(centerView, 0.0);
            AnchorPane.setBottomAnchor(centerView, 0.0);
            AnchorPane.setLeftAnchor(centerView, 0.0);
            AnchorPane.setRightAnchor(centerView, 0.0);

            FXMLLoader masterLoader = new FXMLLoader(getClass().getResource(
                    "/sk/foxer/erpstock/view/layout/master/stock.fxml"
            ));
            AnchorPane masterView = masterLoader.load();
            StockMasterController masterController = masterLoader.getController();
            masterController.setControllers(this, stockController);

            var masterCssUrl = getClass().getResource("/sk/foxer/erpstock/view/style/master-pane.css");
            if (masterCssUrl != null) {
                masterView.getStylesheets().add(masterCssUrl.toExternalForm());
            }

            masterPane.getChildren().setAll(masterView);
            AnchorPane.setTopAnchor(masterView, 0.0);
            AnchorPane.setBottomAnchor(masterView, 0.0);
            AnchorPane.setLeftAnchor(masterView, 0.0);
            AnchorPane.setRightAnchor(masterView, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStockInView() {
        closeDetail();
        masterPane.getChildren().clear();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/sk/foxer/erpstock/view/layout/stock_in.fxml"
            ));
            AnchorPane view = loader.load();

            StockInController controller = loader.getController();
            controller.setMainController(this);

            centerPane.getChildren().setAll(view);
            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loadCenter(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane view = loader.load();
            centerPane.getChildren().setAll(view);
            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSupplierDetail(Supplier supplier) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/sk/foxer/erpstock/view/layout/detail/supplier_detail.fxml"
            ));
            AnchorPane detailView = loader.load();

            String css = getClass().getResource("/sk/foxer/erpstock/view/style/supplier-detail.css").toExternalForm();
            if (css != null) {
                detailView.getStylesheets().add(css);
            }

            SupplierDetailController controller = loader.getController();
            controller.setSupplier(supplier);


            detailPane.getChildren().setAll(detailView);
            AnchorPane.setTopAnchor(detailView, 0.0);
            AnchorPane.setBottomAnchor(detailView, 0.0);
            AnchorPane.setLeftAnchor(detailView, 0.0);
            AnchorPane.setRightAnchor(detailView, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStockOutView() {
        closeDetail();
        masterPane.getChildren().clear();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/sk/foxer/erpstock/view/layout/stock_out.fxml"
            ));
            AnchorPane view = loader.load();

            StockOutController controller = loader.getController();
            controller.setMainController(this);

            centerPane.getChildren().setAll(view);
            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCustomerDetail(Customer customer) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/sk/foxer/erpstock/view/layout/detail/customer_detail.fxml"
            ));
            AnchorPane detailView = loader.load();

            String css = getClass().getResource("/sk/foxer/erpstock/view/style/supplier-detail.css").toExternalForm();
            if (css != null) {
                detailView.getStylesheets().add(css);
            }

            CustomerDetailController controller = loader.getController();
            controller.setCustomer(customer);

            detailPane.getChildren().setAll(detailView);
            AnchorPane.setTopAnchor(detailView, 0.0);
            AnchorPane.setBottomAnchor(detailView, 0.0);
            AnchorPane.setLeftAnchor(detailView, 0.0);
            AnchorPane.setRightAnchor(detailView, 0.0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void showProductEdit(Product product, boolean isNew, StockController stockController) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/sk/foxer/erpstock/view/layout/detail/product_edit.fxml"
            ));
            AnchorPane detailView = loader.load();

            ProductEditController controller = loader.getController();
            controller.setContext(stockController, product, isNew);

            detailPane.getChildren().setAll(detailView);
            AnchorPane.setTopAnchor(detailView, 0.0);
            AnchorPane.setBottomAnchor(detailView, 0.0);
            AnchorPane.setLeftAnchor(detailView, 0.0);
            AnchorPane.setRightAnchor(detailView, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeDetailPane() {
        closeDetail();
    }

    //----- helper -----//
    private void closeDetail() {
        UIHelper.clearDetailPane(detailPane);
    }

}
