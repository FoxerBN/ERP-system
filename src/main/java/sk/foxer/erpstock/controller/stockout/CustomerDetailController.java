package sk.foxer.erpstock.controller.stockout;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import sk.foxer.erpstock.model.stockout.Customer;

public class CustomerDetailController {

    @FXML private Label valName;
    @FXML private Label valAddress;
    @FXML private Label valContact;
    @FXML private Button btnCopyContact;
    @FXML private Button btnToggleOutcomes;
    @FXML private VBox outcomesContainer;

    private Customer customer;
    private boolean outcomesLoaded = false;

    public void setCustomer(Customer customer) {
        this.customer = customer;
        if (customer != null) {
            valName.setText(customer.getName());
            valAddress.setText(customer.getAddress());
            valContact.setText(customer.getContact());
            btnCopyContact.setDisable(customer.getContact() == null || customer.getContact().isBlank());
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
    private void handleToggleOutcomes() {
        if (!outcomesLoaded) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/sk/foxer/erpstock/view/layout/detail/customer_outcomes.fxml"));
                Node view = loader.load();

                CustomerOutcomesController controller = loader.getController();
                controller.loadForCustomer(customer.getId());

                outcomesContainer.getChildren().setAll(view);
                outcomesLoaded = true;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        boolean show = !outcomesContainer.isVisible();
        outcomesContainer.setVisible(show);
        outcomesContainer.setManaged(show);
        btnToggleOutcomes.setText(show ? "Skryť výdaje" : "Zobraziť výdaje");
    }
}
