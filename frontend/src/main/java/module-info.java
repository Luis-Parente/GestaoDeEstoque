module com.espacolcosmeticos.gestaodeestoque_frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.net.http;
    requires javafx.graphics;

    opens com.cosmeticosespacol.GestaoDeEstoque_Frontend to javafx.fxml;
    opens com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.Login to javafx.fxml;
    exports com.cosmeticosespacol.GestaoDeEstoque_Frontend;
}
