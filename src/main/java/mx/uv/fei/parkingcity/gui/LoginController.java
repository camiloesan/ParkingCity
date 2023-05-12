package mx.uv.fei.parkingcity.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mx.uv.fei.parkingcity.dao.UserDAO;
import mx.uv.fei.parkingcity.logic.SessionDetails;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private TextField textFieldUser;

    public static final String USER_ADMIN = "Administrador";
    public static final String USER_STUDENT = "Estudiante";
    public static final String USER_PROFESSOR = "Profesor";
    public static final String USER_REPRESENTATIVE = "RepresentanteCA";
    static SessionDetails sessionDetails;

    @FXML
    private void onActionButtonContinue() throws IOException {
        UserDAO accessAccountDAO = new UserDAO();
        try {
            continueLogin(accessAccountDAO.areCredentialsValid(textFieldUser.getText(), textFieldPassword.getText()));
        } catch (SQLException sqlException) {
            System.out.println("hola");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No se pudo conectar a la base de datos");
            alert.show();
        }
    }

    private void continueLogin(boolean isLoginValid) throws SQLException, IOException {
        if (isLoginValid) {
            redirectToWindow();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("El usuario o contraseña son incorrectos");
            alert.show();
        }
    }

    private void redirectToWindow() throws SQLException, IOException {
        UserDAO accessAccountDAO = new UserDAO();
        String userType = accessAccountDAO.getAccessAccountTypeByUsername(textFieldUser.getText());
        sessionDetails = SessionDetails.getInstance();
        sessionDetails.setUsername(textFieldUser.getText());
        sessionDetails.setUserType(userType);
        switch (userType) {
            default -> System.out.println("Hola");
        }
    }
}
