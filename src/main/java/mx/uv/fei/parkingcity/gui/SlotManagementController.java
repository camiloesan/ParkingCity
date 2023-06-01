package mx.uv.fei.parkingcity.gui;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import mx.uv.fei.parkingcity.dao.ParkingSlotsDAO;
import mx.uv.fei.parkingcity.logic.ParkingSlot;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SlotManagementController {
    @FXML
    private AnchorPane anchorPaneCanvas;
    @FXML
    private Label labelTitle;
    private static final String PB = "nivel0";
    private static final String FIRST_FLOOR = "nivel1";
    private static final String SECOND_FLOOR = "nivel2";
    private static final String THIRD_FLOOR = "nivel3";
    public static int selectedParkingSlot = 0;

    @FXML
    private void initialize() {
        labelTitle.setText("Planta Baja");
        anchorPaneCanvas.setStyle("-fx-border-radius: 5; " +
                "-fx-border-style: solid inside; " +
                "-fx-border-color: grey; " +
                "-fx-border-width: 2");
        viewPBSlots();
    }

    @FXML
    private void viewPBSlots() {
        labelTitle.setText("Planta Baja");
        ParkingSlotsDAO parkingSlotsDAO = new ParkingSlotsDAO();
        anchorPaneCanvas.getChildren().clear();
        try {
            drawParkingSlots(parkingSlotsDAO.getAvailableParkingSlotsByLevel(PB));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @FXML
    private void viewFirstFloorSlots() {
        labelTitle.setText("Primer Piso");
        ParkingSlotsDAO parkingSlotsDAO = new ParkingSlotsDAO();
        anchorPaneCanvas.getChildren().clear();
        try {
            drawParkingSlots(parkingSlotsDAO.getAvailableParkingSlotsByLevel(FIRST_FLOOR));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @FXML
    private void viewSecondFloorSlots() {
        labelTitle.setText("Segundo Piso");
        ParkingSlotsDAO parkingSlotsDAO = new ParkingSlotsDAO();
        anchorPaneCanvas.getChildren().clear();
        try {
            drawParkingSlots(parkingSlotsDAO.getAvailableParkingSlotsByLevel(SECOND_FLOOR));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @FXML
    private void viewThirdFloorSlots() {
        labelTitle.setText("Tercer Piso");
        ParkingSlotsDAO parkingSlotsDAO = new ParkingSlotsDAO();
        anchorPaneCanvas.getChildren().clear();
        try {
            drawParkingSlots(parkingSlotsDAO.getAvailableParkingSlotsByLevel(THIRD_FLOOR));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    private void confirmSelectedParkingSpot(int id) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Estas seguro que quieres el lugar: " + id + "?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.orElse(null) == ButtonType.OK) {
            MainStage.changeView("TicketWindow.fxml", 1000, 700 + MainStage.HEIGHT_OFFSET);
        }
    }

    private void drawParkingSlots(List<ParkingSlot> listAvailableParkingSlots) throws SQLException {
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
                    try {
                        selectedParkingSlot = availableParkingSlotObject.getSlot_id();
                        confirmSelectedParkingSpot(availableParkingSlotObject.getSlot_id());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    alert.setContentText("El lugar ya se encuentra ocupado, elige otro");
                    alert.show();
                }
            });
            anchorPaneCanvas.getChildren().add(rectangleParkingSlot);
        }
    }
}
