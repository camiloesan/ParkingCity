package mx.uv.fei.parkingcity.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TicketWindowController {
    @FXML
    private Label labelCheckIn;
    @FXML
    private Label labelParkingSlotID;

    @FXML
    private void initialize() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        labelCheckIn.setText("Check In:\t" + dateTimeFormatter.format(now));
        labelParkingSlotID.setText("Lugar de estacionamiento: " + SlotManagementController.selectedParkingSlot);
    }

    @FXML
    private void returnToMainMenu() throws IOException {
        MainStage.changeView("SlotManagement.fxml", 1000, 700 + MainStage.HEIGHT_OFFSET);
    }
}
