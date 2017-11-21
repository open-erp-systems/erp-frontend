module erp.gui {

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires vertx.core;
    requires ini4j;
    requires erp.client.network;

    exports com.jukusoft.erp.gui.app;
    exports com.jukusoft.erp.gui.menu;
    opens com.jukusoft.erp.gui.controller;

}