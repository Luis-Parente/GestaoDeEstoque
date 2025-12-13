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
    requires com.fasterxml.jackson.databind;

    opens com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.Login to javafx.fxml;
    opens com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.MainMenu to javafx.fxml;
    opens com.cosmeticosespacol.GestaoDeEstoque_Frontend.gui.Produto to javafx.fxml;

    opens com.cosmeticosespacol.GestaoDeEstoque_Frontend.dto to javafx.base, com.fasterxml.jackson.databind;

    exports com.cosmeticosespacol.GestaoDeEstoque_Frontend;
}

