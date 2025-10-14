module sk.foxer.erpstock {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires org.apache.pdfbox;

    opens sk.foxer.erpstock to javafx.fxml;
    opens sk.foxer.erpstock.controller to javafx.fxml;
    opens sk.foxer.erpstock.controller.stock to javafx.fxml;
    opens sk.foxer.erpstock.model.stock to javafx.base;
    opens sk.foxer.erpstock.dao.stockout to javafx.fxml;
    opens sk.foxer.erpstock.model.stockout to javafx.base;
    opens sk.foxer.erpstock.controller.stockin to javafx.fxml;
    opens sk.foxer.erpstock.model.stockin to javafx.base;



    exports sk.foxer.erpstock;
    opens sk.foxer.erpstock.controller.stockout to javafx.fxml;
}
