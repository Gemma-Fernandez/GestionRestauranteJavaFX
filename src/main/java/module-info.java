module com.restaurante.app {
    requires javafx.controls;
    requires javafx.fxml;

    // Permisos para la vista principal
    opens com.restaurante.app to javafx.fxml;

    // PERMISO Para que la tabla pueda leer el modelo
    opens com.restaurante.app.model to javafx.base;

    exports com.restaurante.app;
}