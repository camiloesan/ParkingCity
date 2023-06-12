package mx.uv.fei.parkingcity.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.util.converter.LocalDateTimeStringConverter;
import mx.uv.fei.parkingcity.dao.TicketDAO;
import mx.uv.fei.parkingcity.logic.Ticket;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckOutController {
    @FXML
    private Label labelCheckOut;
    @FXML
    private Label labelParkingSlotID;
    @FXML
    private Label labelPaymentTotal;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    @FXML
    private void initialize() {
        labelCheckOut.setText("CheckOut: " + dateTimeFormatter.format(LocalDateTime.now()));
        labelParkingSlotID.setText("Lugar de estacionamiento: " + getSlotID());
        labelPaymentTotal.setText("Total a pagar: $" + getTotalPayment());
    }

    private int getTotalPayment() {
        int total = 10;
        TicketDAO ticketDAO = new TicketDAO();
        LocalDateTime checkIn = null;
        try {
            checkIn = ticketDAO.getCheckInByTicketID(1);
        } catch (SQLException sqlException) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Error al recuperar el ticket");
            error.showAndWait();
            sqlException.printStackTrace();
        }

        int count = 0;
        while (checkIn.isBefore(LocalDateTime.now())) {
            checkIn = checkIn.plusMinutes(120);
            count++;
        }

        total = total * count;

        return total;
    }

    private int getSlotID() {
        TicketDAO ticketDAO = new TicketDAO();
        int result = 0;
        try {
            result = ticketDAO.getSlotIDByTicketID(1);
        } catch (SQLException sqlException) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Error al recuperar el lugar de estacionamiento");
            error.showAndWait();
            sqlException.printStackTrace();
        }
        return result;
    }

    @FXML
    private void pay() {

    }

    @FXML
    private void redirectToMainMenu() throws IOException {
        MainStage.changeView("MainMenu.fxml", 1000, 700);
    }

}
