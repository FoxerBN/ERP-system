package sk.foxer.erpstock;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sk.foxer.erpstock.config.DatabaseConfig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ERPApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/sk/foxer/erpstock/view/main.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load(), 1900, 800);
        stage.setTitle("ERP-Foxer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = DatabaseConfig.getConnection();
        System.out.println("Connected: " + !conn.isClosed());
        conn.close();

        launch();
    }
}
