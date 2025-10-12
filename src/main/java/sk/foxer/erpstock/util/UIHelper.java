package sk.foxer.erpstock.util;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class UIHelper {

    public static void clearDetailPane(AnchorPane pane) {
        if (pane != null) {
            pane.getChildren().clear();

            Label placeholder = new Label("Details");
            placeholder.setStyle("-fx-font-size: 16px; -fx-text-fill: #888;");
            placeholder.setLayoutX(14);
            placeholder.setLayoutY(14);
            pane.getChildren().add(placeholder);
        }
    }
}
