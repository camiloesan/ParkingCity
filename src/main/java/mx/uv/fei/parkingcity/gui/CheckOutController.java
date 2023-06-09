package mx.uv.fei.parkingcity.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import mx.uv.fei.parkingcity.dao.PaymentDAO;
import mx.uv.fei.parkingcity.dao.TicketDAO;
import mx.uv.fei.parkingcity.logic.ParkingSlot;
import mx.uv.fei.parkingcity.logic.TransferTicket;

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
        fillTicket();
    }

    private int getTotalPayment() {
        int total = 10;
        TicketDAO ticketDAO = new TicketDAO();
        LocalDateTime checkIn = null;
        try {
            checkIn = ticketDAO.getCheckInByTicketID(TransferTicket.getTicketID());
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
            result = ticketDAO.getSlotIDByTicketID(TransferTicket.getTicketID());
        } catch (SQLException sqlException) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Error al recuperar el lugar de estacionamiento");
            error.showAndWait();
            sqlException.printStackTrace();
        }
        return result;
    }

    @FXML
    private void fillTicket() {
        labelCheckOut.setText("CheckOut: " + dateTimeFormatter.format(LocalDateTime.now()));
        labelParkingSlotID.setText("Lugar de estacionamiento: " + getSlotID());
        labelPaymentTotal.setText("Total a pagar: $" + getTotalPayment());
    }

    @FXML
    private void pay() throws IOException {
        PaymentDAO paymentDAO = new PaymentDAO();

        int result = 0;
        try {
            result = paymentDAO.updatePayment(TransferTicket.getTicketID());
        } catch (SQLException sqlException) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Error al conectarse con la base de datos");
            error.showAndWait();
            sqlException.printStackTrace();
        }

        if (result == 1) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setContentText("Pago hecho correctamente");
            error.showAndWait();
            labelCheckOut.setText("CheckOut: ");
            labelParkingSlotID.setText("Lugar de estacionamiento: ");
            labelPaymentTotal.setText("Total a pagar: ");
            MainStage.changeView("SlotsToPay.fxml", 1000, 700);
        }

    }

    @FXML
    private void redirectToMainMenu() throws IOException {
        MainStage.changeView("MainMenu.fxml", 1000, 700);
    }

}
