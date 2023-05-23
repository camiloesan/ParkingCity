package mx.uv.fei.parkingcity.logic;

import javafx.scene.shape.Polygon;

public class Arrow implements Shape {
    Polygon arrow;

    public Arrow(Polygon arrow) {
        this.arrow = arrow;
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
