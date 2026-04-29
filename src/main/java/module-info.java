module co.edu.uniquindio.clinica {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.simplejavamail.core;
    requires org.simplejavamail;

    opens co.edu.uniquindio.clinica to javafx.fxml;
    exports co.edu.uniquindio.clinica;
    exports co.edu.uniquindio.clinica.controladores;
    exports co.edu.uniquindio.clinica.modelo;
    exports co.edu.uniquindio.clinica.modelo.factory;
    exports co.edu.uniquindio.clinica.utils;
    opens co.edu.uniquindio.clinica.controladores to javafx.fxml;
    opens co.edu.uniquindio.clinica.modelo to javafx.fxml;
    opens co.edu.uniquindio.clinica.enums to javafx.fxml;
    opens co.edu.uniquindio.clinica.utils to javafx.fxml;

}