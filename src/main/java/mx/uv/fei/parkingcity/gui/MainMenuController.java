package mx.uv.fei.parkingcity.gui;

import javafx.fxml.FXML;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private void redirectToSlotManagement() throws IOException {
        MainStage.changeView("SlotManagement.fxml", 1000, 700);
    }

    @FXML
    private void redirectToCheckOut() throws IOException {
        MainStage.changeView("SlotToPay.fxml", 1000, 700);
    }

    @FXML
    private void redirectToExit() throws IOException {
        MainStage.changeView("Exit.fxml", 1000, 700);
    }

}
