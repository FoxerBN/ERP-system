package sk.foxer.erpstock.util;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

public final class HandleExportUtil {

    private HandleExportUtil() {
    }

    @FunctionalInterface
    public interface PdfExporter<T> {
        Path export(ObservableList<T> items) throws IOException;
    }

    public static <T> void handleExportPdf(TableView<T> table, PdfExporter<T> exporter) {
        ObservableList<T> items = table == null ? null : table.getItems();
        if (items == null || items.isEmpty()) {
            showInfo("Export", "Tabuľka je prázdna – nie je čo exportovať.");
            return;
        }

        try {
            Path savedFile = Objects.requireNonNull(exporter, "exporter").export(items);
            showInfo("Export", "PDF bolo uložené do: " + savedFile);
        } catch (IOException e) {
            e.printStackTrace();
            showError("Export zlyhal", "PDF sa nepodarilo vytvoriť.");
        }
    }

    public static void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.show();
    }

    public static void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.show();
    }
}
