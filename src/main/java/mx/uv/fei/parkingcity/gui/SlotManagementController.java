package mx.uv.fei.parkingcity.gui;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import mx.uv.fei.parkingcity.dao.ParkingSlotsDAO;
import mx.uv.fei.parkingcity.logic.ParkingSlot;

import java.sql.SQLException;
import java.util.List;

public class SlotManagementController {
    @FXML
    private AnchorPane anchorPaneCanvas;

    @FXML
    private void initialize() {
        anchorPaneCanvas.setStyle("-fx-border-radius: 5; " +
                "-fx-border-style: solid inside; " +
                "-fx-border-color: grey; " +
                "-fx-border-width: 2");
        try {
            drawParkingSlots();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    //#537188 taken
    //#CBB279 available

    private void drawParkingSlots() throws SQLException {
        ParkingSlotsDAO slotsDAO = new ParkingSlotsDAO();
        List<ParkingSlot> listAvailableParkingSlots = slotsDAO.getAvailableParkingSlots();

        int posX = 120;
        int posY = 46;
        int counter = 0;
        for (ParkingSlot availableParkingSlotObject : listAvailableParkingSlots) {
            Rectangle rectangleParkingSlot = new Rectangle(80, 40);
            boolean available;
            if (availableParkingSlotObject.getAvailable().equals("yes")) {
                available = true;
                rectangleParkingSlot.setFill(Color.web("#CBB279"));
            } else {
                available = false;
                rectangleParkingSlot.setFill(Color.web("#537188"));
            }

            rectangleParkingSlot.setArcWidth(10);
            rectangleParkingSlot.setArcHeight(10);

            if (counter % 20 == 0 && counter != 0) {
                posX += 200;
                posY = 92;
            } else if (counter % 10 == 0 && counter != 0) {
                posX += 90;
                posY = 92;
            } else {
                posY += 46;
            }
            counter++;

            rectangleParkingSlot.setLayoutX(posX);
            rectangleParkingSlot.setLayoutY(posY);

            var scaleTrans = new ScaleTransition(javafx.util.Duration.millis(250), rectangleParkingSlot);
            scaleTrans.setFromX(1.0);
            scaleTrans.setFromY(1.0);
            scaleTrans.setToX(1.2);
            scaleTrans.setToY(1.2);
            rectangleParkingSlot.setOnMouseEntered(e -> {
                scaleTrans.setRate(1.0);
                rectangleParkingSlot.setViewOrder(-1.0);
                scaleTrans.play();
            });
            rectangleParkingSlot.setOnMouseExited(e -> {
                scaleTrans.setRate(-1.0);
                rectangleParkingSlot.setViewOrder(0.0);
                scaleTrans.play();
            });
            rectangleParkingSlot.setOnMouseClicked(e -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                if (available) {
                    alert.setContentText("Tu lugar asignado es: " + availableParkingSlotObject.getSlot_id());
                    alert.show();
                } else {
                    alert.setContentText("El lugar ya se encuentra ocupado, elige otro");
                    alert.show();
                }
            });
            anchorPaneCanvas.getChildren().add(rectangleParkingSlot);
        }
    }
}
