package mx.uv.fei.parkingcity.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import mx.uv.fei.parkingcity.dao.ParkingSlotsDAO;
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
    ComboBox<Integer> comboBoxTicket;

    @FXML
    private void initialize() {
        fillComboBoxTickets();
    }

    private void fillComboBoxTickets() {
        TicketDAO ticketDAO = new TicketDAO();
        try {
            comboBoxTicket.setItems(
                    FXCollections.observableList(ticketDAO.getTicketsPay()));
        } catch (SQLException sqlException) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Error al recuperar el lugares de estacionamiento por pagar");
            error.showAndWait();
            sqlException.printStackTrace();
        }
    }

    private LocalDateTime getPaymentDatetime() {
        PaymentDAO paymentDAO = new PaymentDAO();
        LocalDateTime chdateTime = null;
        try {
            chdateTime = paymentDAO.getDateTimeByPaymentID(comboBoxTicket.getValue());
        } catch (SQLException sqlException) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Error al recuperar el ticket");
            error.showAndWait();
            sqlException.printStackTrace();
        }
        return chdateTime;
    }

    @FXML
    private void exit() {
        Duration duration = Duration.between(getPaymentDatetime(), LocalDateTime.now());
        long minutes = duration.toMinutes();

        if (minutes <= 10) {

            text.setText("¡Vuelva pronto!");
            rectangle.setVisible(false);
            straight.setVisible(false);
            left.setVisible(false);
            horizontal.setVisible(true);
            right.setVisible(true);

            TicketDAO ticketDAO = new TicketDAO();
            ParkingSlotsDAO parkingSlotsDAO = new ParkingSlotsDAO();
            String level = "nivel0";

            if (getSlotID() >= 101 && getSlotID() <= 150) {
                level = "nivel1";
            } else if (getSlotID() >= 201 && getSlotID() <= 250) {
                level = "nivel2";
            } else if (getSlotID() >= 301 && getSlotID() <= 350) {
                level = "nivel3";
            }

            try {
                ticketDAO.registerExit(comboBoxTicket.getValue());
                parkingSlotsDAO.openSlot(level, getSlotID());
            } catch (SQLException sqlException) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setContentText("Error al registrar salida");
                error.showAndWait();
                sqlException.printStackTrace();
            }

        } else {

            text.setText("Tiempo vencido, pague de nuevo");
            rectangle.setVisible(false);
            straight.setVisible(false);
            right.setVisible(false);
            horizontal.setVisible(true);
            left.setVisible(true);

        }
    }

    private int getSlotID() {
        TicketDAO ticketDAO = new TicketDAO();
        int result = 0;
        try {
            result = ticketDAO.getSlotIDByTicketID(comboBoxTicket.getValue());
        } catch (SQLException sqlException) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Error al recuperar el lugar de estacionamiento");
            error.showAndWait();
            sqlException.printStackTrace();
        }
        return result;
    }

    @FXML
    private void redirectToMainMenu() throws IOException {
        MainStage.changeView("MainMenu.fxml", 1000, 700);
    }
}
