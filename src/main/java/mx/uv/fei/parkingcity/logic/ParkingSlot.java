package mx.uv.fei.parkingcity.logic;

import javafx.scene.shape.Rectangle;

public class ParkingSlot extends Rectangle implements Shape {
    Rectangle parkingSlot;

    public ParkingSlot(Rectangle parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    @Override
    public Shape makeCopy() {
        Shape shapeObject = null;

        try {
            shapeObject = (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            e.getStackTrace();
        }

        return shapeObject;
    }
}
