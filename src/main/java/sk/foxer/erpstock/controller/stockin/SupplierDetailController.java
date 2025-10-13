package sk.foxer.erpstock.controller.stockin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import sk.foxer.erpstock.model.stockin.Supplier;

public class SupplierDetailController {
    @FXML private Label valName;
    @FXML private Label valAddress;
    @FXML private Label valContact;
    @FXML private Button btnCopyContact;
    @FXML private Button btnToggleIncomes;
    @FXML private VBox incomesContainer;

    private Supplier supplier;
    private boolean incomesLoaded = false;

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        if (supplier != null) {
            valName.setText(supplier.getName());
            valAddress.setText(supplier.getAddress());
            valContact.setText(supplier.getContact());
            btnCopyContact.setDisable(supplier.getContact() == null || supplier.getContact().isBlank());
        }
    }

    @FXML
    private void handleCopyContact() {
        if (valContact.getText() != null && !valContact.getText().isBlank()) {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(valContact.getText());
            clipboard.setContent(content);
        }
    }

    @FXML
    private void handleToggleIncomes() {
        if (!incomesLoaded) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/sk/foxer/erpstock/view/layout/detail/supplier_incomes.fxml"));
                Node view = loader.load();

                SupplierIncomesController controller = loader.getController();
                controller.loadForSupplier(supplier.getId());

                incomesContainer.getChildren().setAll(view);
                incomesLoaded = true;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        boolean show = !incomesContainer.isVisible();
        incomesContainer.setVisible(show);
        incomesContainer.setManaged(show);
        btnToggleIncomes.setText(show ? "Skryť príjmy" : "Zobraziť príjmy");
    }
}
