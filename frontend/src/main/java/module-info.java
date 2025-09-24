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

    opens com.cosmeticosespacol.GestaoDeEstoque_Frontend to javafx.fxml;
    exports com.cosmeticosespacol.GestaoDeEstoque_Frontend;
}
