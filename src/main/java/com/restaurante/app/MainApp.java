package com.restaurante.app;

import com.restaurante.app.repository.DataRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // CORRECCIÓN AQUÍ: Solo el nombre del archivo, sin rutas largas
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("main-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Gestión Restaurante");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // CORRECCIÓN 2: Recuperamos la carga de datos que habíamos hecho antes
        DataRepository.loadData();

        launch();
    }
}