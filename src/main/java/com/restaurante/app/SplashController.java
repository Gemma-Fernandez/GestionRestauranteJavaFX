package com.restaurante.app;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SplashController {

    @FXML private ImageView imgLogo;

    @FXML
    public void initialize() {

        try {
            // ruta de la imagen
            String rutaImagen = "/images/oroma-gourmet.png";

            // Cargamos la imagen
            Image image = new Image(getClass().getResourceAsStream(rutaImagen));
            imgLogo.setImage(image);
        } catch (Exception e) {
            System.out.println("No se encontró la imagen del logo. Revisa la ruta.");
            e.printStackTrace();
        }

        //pausa de 3 segundos
        PauseTransition delay = new PauseTransition(Duration.seconds(3));

        // Qué hacer cuando termine la pausa:
        delay.setOnFinished(event -> cargarVentanaPrincipal());

        // Iniciar el contador
        delay.play();
    }

    private void cargarVentanaPrincipal() {
        try {
            // Cargar el FXML principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
            Parent root = loader.load();

            // Crear la nueva escena
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Gestión de Restaurantes v1.0");
            stage.setScene(scene);
            stage.show();

            // CERRAR la ventana del Splash
            Stage splashStage = (Stage) imgLogo.getScene().getWindow();
            splashStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
