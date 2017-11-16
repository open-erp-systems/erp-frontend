module erp.gui {

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires vertx.core;
    requires ini4j;

    exports com.jukusoft.erp.gui.app;
    opens com.jukusoft.erp.gui.controller;

}