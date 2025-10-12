package sk.foxer.erpstock.controller.stockin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sk.foxer.erpstock.model.stockin.Supplier;

public class SupplierDetailController {

    @FXML private Label valName;
    @FXML private Label valAddress;
    @FXML private Label valContact;

    private Supplier supplier;

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        if (supplier != null) {
            valName.setText(supplier.getName());
            valAddress.setText(supplier.getAddress());
            valContact.setText(supplier.getContact());
        }
    }

    @FXML
    private void onCloseClicked() {
        valName.getScene().lookup("#detailPane").setVisible(false);
    }
}
