package sk.foxer.erpstock.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ToolbarController {

    private MainController mainController; // 🔗 referencia na hlavný controller

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML private Button btnStock;
    @FXML private Button btnIncome;
    @FXML private Button btnOutcome;

    @FXML
    private void onStockClicked() {
        System.out.println("Klikol si na: Stav skladu");
        if (mainController != null) mainController.showStockView();
    }

    @FXML
    private void onIncomeClicked() {
        System.out.println("Klikol si na: Príjem");
        if (mainController != null) mainController.showStockInView();
    }

    @FXML
    private void onOutcomeClicked() {
        System.out.println("Klikol si na: Výdaj");
        // tu neskôr pridáš výdaj view
    }
}
