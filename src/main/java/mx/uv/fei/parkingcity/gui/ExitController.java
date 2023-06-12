package mx.uv.fei.parkingcity.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import mx.uv.fei.parkingcity.dao.PaymentDAO;
import mx.uv.fei.parkingcity.dao.TicketDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;

public class ExitController {

    @FXML
    Text text;
    @FXML
    Rectangle rectangle;
    @FXML
    Rectangle horizontal;
    @FXML
    Polygon left;
    @FXML
    Polygon right;
    @FXML
    Polygon straight;

    @FXML
    private void initialize() {
        LocalDateTime MAX_TIME_FOR_PAY = getPaymentDatetime().plusMinutes(30);
        Duration duration = Duration.between(getPaymentDatetime(), MAX_TIME_FOR_PAY);
        long minutes = duration.toMinutes();

        if (minutes <= 30) {

            text.setText("¡Vuelva pronto!");
            rectangle.setVisible(false);
            straight.setVisible(false);
            horizontal.setVisible(true);
            right.setVisible(true);

            TicketDAO ticketDAO = new TicketDAO();

            try {
                ticketDAO.registerExit(1);
            } catch (SQLException sqlException) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setContentText("Error al registrar salida");
                error.showAndWait();
                sqlException.printStackTrace();
            }

        } else {

            text.setText("Excedio su tiempo de pago, pague de nuevo");
            rectangle.setVisible(false);
            straight.setVisible(false);
            horizontal.setVisible(true);
            left.setVisible(true);

        }
    }

    private LocalDateTime getPaymentDatetime() {
        PaymentDAO paymentDAO = new PaymentDAO();
        LocalDateTime chdateTime = null;
        try {
            chdateTime = paymentDAO.getDateTimeByPaymentID(1);
        } catch (SQLException sqlException) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Error al recuperar el ticket");
            error.showAndWait();
            sqlException.printStackTrace();
        }
        return chdateTime;
    }

    @FXML
    private void redirectToMainMenu() throws IOException {
        MainStage.changeView("MainMenu.fxml", 1000, 700);
    }
}
