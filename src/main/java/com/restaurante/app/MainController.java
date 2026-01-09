package com.restaurante.app;

import com.restaurante.app.model.Empleado;
import com.restaurante.app.model.Plato;
import com.restaurante.app.model.Restaurante;
import com.restaurante.app.repository.DataRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert; // Mostrar mensajes emergentes
import java.time.LocalDate;        // Manejar las fechas


public class MainController {

    // ELEMENTOS DE RESTAURANTE
    // Para decirle a la tabla el tipo de objetos tiene
    @FXML
    public TableView<Restaurante> tvRestaurantes;
    @FXML
    public TableColumn<Restaurante, String> colNombre;
    @FXML
    public TableColumn<Restaurante, String> colCiudad;
    @FXML
    private Button btnEliminar;

    // Formulario
    @FXML
    public TextField txtNombre;
    @FXML
    public TextField txtCiudad;
    @FXML
    public TextField txtAforo;
    @FXML
    public CheckBox chkAbierto;
    @FXML
    public DatePicker dpFecha;

    // Hace que la tabla se actualice sola cuando añadimos datos
    private ObservableList<Restaurante> listaRestaurantes;
    // Guarda el restaurante seleccionado actualmente
    private Restaurante restauranteSeleccionado;

    //ELEMENTOS DE PLATO
    @FXML
    private TableView<Plato> tvPlatos;
    @FXML
    private TableColumn<Plato, String> colNombrePlato;
    @FXML
    private TableColumn<Plato, Double> colPrecio;

    @FXML
    private TextField txtNombrePlato;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtCalorias;
    @FXML
    private TextField txtDificultad;
    @FXML
    private CheckBox chkVegetariano;
    @FXML
    private Button btnEliminarPlato;

    // Lista para que la tabla se actualice automáticamente
    private ObservableList<Plato> listaPlatos = FXCollections.observableArrayList();
    //guarda el plato que el usuario ha seleccionado
    private Plato platoSeleccionado;

    //Elementos EMPLEADOS
    @FXML
    private TableView<Empleado> tvEmpleados;
    @FXML
    private TableColumn<Empleado, String> colNombreEmp;
    @FXML
    private TableColumn<Empleado, String> colApellidosEmp;
    @FXML
    private TableColumn<Empleado, Float> colSueldoEmp; // Ojo: Float
    @FXML
    private TableColumn<Empleado, String> colHorarioEmp;

    @FXML
    private TextField txtNombreEmp;
    @FXML
    private TextField txtApellidosEmp;
    @FXML
    private TextField txtSueldoEmp;
    @FXML
    private TextField txtHorarioEmp;
    @FXML
    private DatePicker dpFechaContrato;
    @FXML
    private Button btnEliminarEmp;

    private ObservableList<Empleado> listaEmpleados = FXCollections.observableArrayList();
    // Guarda el empleado seleccionado actualmente
    private Empleado empleadoSeleccionado;

    //filtros
    @FXML private TextField txtBusquedaNombre;
    @FXML private TextField txtBusquedaLocalidad;
    private FilteredList<Restaurante> listaFiltradaRest;



    // METODO INITIALIZE (Se ejecuta al arrancar la ventana)
    @FXML
    public void initialize() {
        //Configuracion RESTAURANTES
        //Configurar columnas
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));

        // Cargar datos del repositorio
        listaRestaurantes = FXCollections.observableArrayList(DataRepository.getRestaurantes());
        tvRestaurantes.setItems(listaRestaurantes);

        // Detectar clic en la tabla
        tvRestaurantes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            restauranteSeleccionado = newSelection;
            mostrarDetalles(restauranteSeleccionado);
        });

        //Configuracion PLATOS
        //vinculo columnas
        colNombrePlato.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        //cargo datos iniciales
        listaPlatos.addAll(DataRepository.getPlatos());
        tvPlatos.setItems(listaPlatos);

        //asegurar nada seleccionado
        tvRestaurantes.getSelectionModel().clearSelection(); // Limpiar al inicio

        //detectar clic
        tvPlatos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            platoSeleccionado = newSelection;
            if (platoSeleccionado != null) {
                txtNombrePlato.setText(platoSeleccionado.getNombre());
                txtPrecio.setText(String.valueOf(platoSeleccionado.getPrecio()));
                txtCalorias.setText(String.valueOf(platoSeleccionado.getCalorias()));
                txtDificultad.setText(platoSeleccionado.getDificultad());
                chkVegetariano.setSelected(platoSeleccionado.isVegetariano());
            }
        });

        //Configuracion EMPLEADOS
        colNombreEmp.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidosEmp.setCellValueFactory(new PropertyValueFactory<>("apellidos")); // Coincide con el modelo
        colSueldoEmp.setCellValueFactory(new PropertyValueFactory<>("sueldo"));
        colHorarioEmp.setCellValueFactory(new PropertyValueFactory<>("horario"));

        listaEmpleados.addAll(DataRepository.getEmpleados());
        tvEmpleados.setItems(listaEmpleados);

        // Listener para rellenar al hacer clic
        tvEmpleados.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            empleadoSeleccionado = newSelection;
            if (empleadoSeleccionado != null) {
                txtNombreEmp.setText(empleadoSeleccionado.getNombre());
                txtApellidosEmp.setText(empleadoSeleccionado.getApellidos());
                txtSueldoEmp.setText(String.valueOf(empleadoSeleccionado.getSueldo()));
                txtHorarioEmp.setText(empleadoSeleccionado.getHorario());
                dpFechaContrato.setValue(empleadoSeleccionado.getFechaContrato());
            }
        });
        //Activar/desactivar botones
        btnEliminarEmp.disableProperty().bind(
                tvEmpleados.getSelectionModel().selectedItemProperty().isNull()
        );
        btnEliminar.disableProperty().bind(
                tvRestaurantes.getSelectionModel().selectedItemProperty().isNull()
        );
        btnEliminarPlato.disableProperty().bind(
                tvPlatos.getSelectionModel().selectedItemProperty().isNull()
        );


        //filtro restaurantes
        listaFiltradaRest = new FilteredList<>(listaRestaurantes, p -> true);
        tvRestaurantes.setItems(listaFiltradaRest);

        txtBusquedaNombre.textProperty().addListener((obs, viejo, nuevo) -> filtrarRestaurantes());
        txtBusquedaLocalidad.textProperty().addListener((obs, viejo, nuevo) -> filtrarRestaurantes());


    }

    // CRUD RESTAURANTES
    private void mostrarDetalles(Restaurante r) {
        if (r != null) {
            txtNombre.setText(r.getNombre());
            txtCiudad.setText(r.getCiudad());
            txtAforo.setText(String.valueOf(r.getAforo()));
            chkAbierto.setSelected(r.isAbierto());
            dpFecha.setValue(r.getFechaApertura());
        } else {
            // Si no hay nada seleccionado, limpiamos
            txtNombre.setText("");
            txtCiudad.setText("");
            txtAforo.setText("");
            chkAbierto.setSelected(false);
            dpFecha.setValue(null);
        }
    }

    // Acciones de los botones

    @FXML
    public void onNuevoClick(ActionEvent actionEvent) {
        // Limpiamos la selección y el formulario para crear uno nuevo
        tvRestaurantes.getSelectionModel().clearSelection();
        restauranteSeleccionado = null;
        mostrarDetalles(null);

        txtNombre.requestFocus();
    }

    @FXML
    public void onGuardarClick(ActionEvent actionEvent) {
        try {
            // 1. Recoger los datos que ha escrito el usuario
            String nombre = txtNombre.getText();
            String ciudad = txtCiudad.getText();

            // Validación
            if (nombre.isEmpty() || ciudad.isEmpty()) {
                mostrarAlerta("Error", "Por favor, escribe al menos Nombre y Ciudad.");
                return; // Detenemos aquí si faltan datos
            }

            // Convertir texto a número
            int aforo = 0;
            if (!txtAforo.getText().isEmpty()) {
                aforo = Integer.parseInt(txtAforo.getText());
            }

            boolean abierto = chkAbierto.isSelected();
            LocalDate fecha = dpFecha.getValue();

            // Si la fecha está vacía, ponemos la de hoy por defecto
            if (fecha == null) fecha = LocalDate.now();

            // 2. Crear el objeto Restaurante
            if (restauranteSeleccionado == null) {
                Restaurante nuevoRestaurante = new Restaurante(nombre, ciudad, aforo, abierto, fecha);
                DataRepository.addRestaurante(nuevoRestaurante);
                listaRestaurantes.add(nuevoRestaurante);

                mostrarAlerta("Guardado", "Restaurante creado con exito");

            } else {

                // Forzamos a la tabla para ver los cambios
                tvRestaurantes.refresh();
                // Guardamos los cambios en el archivo
                DataRepository.saveData();

                mostrarAlerta("Actualizado", "Restaurante editado correctamente.");
            }
            // Limpiamos el formulario
            onNuevoClick(null);

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El Aforo debe ser un número.");
        }
    }


    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
        System.out.println("Botón Guardar pulsado");
    }


    @FXML
    public void onEliminarClick(ActionEvent actionEvent) {
        // 1. Comprobar si hay algo seleccionado
        if (restauranteSeleccionado == null) {
            mostrarAlerta("Error", "¡Selecciona un restaurante de la tabla primero!");
            return;
        }

        // Preguntar confirmación
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar eliminación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres eliminar: " + restauranteSeleccionado.getNombre() + "?");

        // Solo borramos si dice "Aceptar"
        if (alert.showAndWait().get() == ButtonType.OK) {

            DataRepository.removeRestaurante(restauranteSeleccionado);      // Borrar del disco (Repositorio)
            listaRestaurantes.remove(restauranteSeleccionado);      //Borrar de la pantalla
            onNuevoClick(null);     //Limpiar el formulario
            mostrarAlerta("Eliminado", "Restaurante eliminado correctamente.");
        }
    }

    //CRUD PLATOS
    @FXML
    public void onNuevoPlatoClick(ActionEvent event) {
        tvPlatos.getSelectionModel().clearSelection();
        platoSeleccionado = null;

        txtNombrePlato.clear();
        txtPrecio.clear();
        txtCalorias.clear();
        txtDificultad.clear();
        chkVegetariano.setSelected(false);
        txtNombrePlato.requestFocus();
    }

    @FXML
    public void onGuardarPlatoClick(ActionEvent event) {
        try {
            String nombre = txtNombrePlato.getText();
            // Validamos campos obligatorios
            if (nombre.isEmpty() || txtPrecio.getText().isEmpty()) {
                mostrarAlerta("Error", "Nombre y Precio son obligatorios");
                return;
            }

            // Convertimos Strings a números
            double precio = Double.parseDouble(txtPrecio.getText());
            int calorias = 0;
            if (!txtCalorias.getText().isEmpty()) calorias = Integer.parseInt(txtCalorias.getText());

            String dificultad = txtDificultad.getText();
            boolean vegetariano = chkVegetariano.isSelected();

            if (platoSeleccionado == null) {
                //nuevo plato
                Plato nuevo = new Plato(nombre, precio, calorias, vegetariano, dificultad);
                DataRepository.addPlato(nuevo);
                listaPlatos.add(nuevo);
                mostrarAlerta("Guardado", "Plato creado correctamente.");
            } else {
                // editar plato
                DataRepository.saveData();

                tvPlatos.refresh();         // Refrescar vista
                DataRepository.saveData();      // Guardar cambios
                mostrarAlerta("Actualizado", "Plato modificado correctamente.");
            }
            onNuevoPlatoClick(null);        // Resetear

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Revisa que Precio y Calorías sean números (usa punto para decimales).");
        }
    }

    @FXML
    public void onEliminarPlatoClick(ActionEvent event) {
        if (platoSeleccionado == null) {
            mostrarAlerta("Error", "Selecciona un plato para eliminar.");
            return;
        }

        DataRepository.removePlato(platoSeleccionado);
        listaPlatos.remove(platoSeleccionado);
        onNuevoPlatoClick(null);
        mostrarAlerta("Eliminado", "Plato eliminado.");
    }

    //CRUD EMPLEADOS
    @FXML
    public void onNuevoEmpleadoClick(ActionEvent event) {
        tvEmpleados.getSelectionModel().clearSelection();
        empleadoSeleccionado = null;

        // Limpiar campos
        txtNombreEmp.clear();
        txtApellidosEmp.clear();
        txtSueldoEmp.clear();
        txtHorarioEmp.clear();
        dpFechaContrato.setValue(null);

        txtNombreEmp.requestFocus();
    }

    @FXML
    public void onEliminarEmpleadoClick(ActionEvent event) {
        if (empleadoSeleccionado == null) {
            mostrarAlerta("Selección requerida", "Selecciona un empleado para eliminar.");
            return;
        }

        // Confirmación opcional
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Despedir a " + empleadoSeleccionado.getApellidos() + "?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            DataRepository.removeEmpleado(empleadoSeleccionado);
            listaEmpleados.remove(empleadoSeleccionado);
            onNuevoEmpleadoClick(null);
        }
    }

    @FXML
    public void onGuardarEmpleadoClick(ActionEvent event) {
        try {
            // Recoger datos
            String nombre = txtNombreEmp.getText();
            String apellidos = txtApellidosEmp.getText();
            String horario = txtHorarioEmp.getText();
            LocalDate fecha = dpFechaContrato.getValue();

            // VALIDACIÓN: Apellidos y Sueldo son obligatorios según tu modelo
            if (apellidos.isEmpty() || txtSueldoEmp.getText().isEmpty()) {
                mostrarAlerta("Datos incompletos", "Apellidos y Sueldo son obligatorios.");
                return;
            }

            // Si no pone fecha, ponemos la de hoy
            if (fecha == null) fecha = LocalDate.now();

            // Conversión a float
            float sueldo = Float.parseFloat(txtSueldoEmp.getText());

            if (empleadoSeleccionado == null) {
                // CREAR NUEVO
                Empleado nuevo = new Empleado(nombre, apellidos, sueldo, fecha, horario);
                DataRepository.addEmpleado(nuevo);
                listaEmpleados.add(nuevo);
                mostrarAlerta("Éxito", "Empleado contratado correctamente.");
            } else {
                // EDITAR EXISTENTE
                DataRepository.saveData();
                mostrarAlerta("Éxito", "Datos del empleado actualizados.");
            }
            onNuevoEmpleadoClick(null); // Limpiar

        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "El sueldo debe ser un número (usa punto para decimales).");
        }


    }
    //filtro de restaurantes
    private void filtrarRestaurantes() {
        listaFiltradaRest.setPredicate(restaurante -> {
            String nombreFiltro = txtBusquedaNombre.getText().toLowerCase();
            String localidadFiltro = txtBusquedaLocalidad.getText().toLowerCase();

            // Si el filtro está vacío, pasa la validación (se muestra)
            boolean coincideNombre = nombreFiltro.isEmpty() ||
                    restaurante.getNombre().toLowerCase().contains(nombreFiltro);

            boolean coincideLocalidad = localidadFiltro.isEmpty() ||
                    restaurante.getCiudad().toLowerCase().contains(localidadFiltro);

            // Se muestra solo si cumple AMBAS condiciones
            return coincideNombre && coincideLocalidad;
        });

    }

}
