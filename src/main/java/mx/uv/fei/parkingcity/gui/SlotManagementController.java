package mx.uv.fei.parkingcity.gui;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class SlotManagementController {
    @FXML
    private AnchorPane anchorPaneCanvas;

    @FXML
    private void initialize() {
        anchorPaneCanvas.setStyle("-fx-border-radius: 5; " +
                "-fx-border-style: solid inside; " +
                "-fx-border-color: grey; " +
                "-fx-border-width: 2");
        drawParkingSlots();
    }

    private void drawParkingSlots() {
        //#537188 taken
        //#CBB279 available

        //check for taken slots
        //left
        int posY = 90;
        for (int i = 0; i < 10; i++) {
            Rectangle rectangleParkingSlot = new Rectangle(80, 40, Color.web("#CBB279"));
            rectangleParkingSlot.setArcWidth(10);
            rectangleParkingSlot.setArcHeight(10);
            rectangleParkingSlot.setLayoutX(10);
            rectangleParkingSlot.setLayoutY(posY);
            anchorPaneCanvas.getChildren().add(rectangleParkingSlot);
            posY+=46;

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
                //give the thing
            });
        }

        //top
        int posX = 90;
        for (int i = 0; i < 10; i++) {
            Rectangle rectangleParkingSlot = new Rectangle(40, 80, Color.web("#CBB279"));
            rectangleParkingSlot.setArcWidth(10);
            rectangleParkingSlot.setArcHeight(10);
            rectangleParkingSlot.setLayoutX(posX);
            rectangleParkingSlot.setLayoutY(10);
            anchorPaneCanvas.getChildren().add(rectangleParkingSlot);
            posX+=46;

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
                //give the thing
            });
        }

        //right
        

    }
}
