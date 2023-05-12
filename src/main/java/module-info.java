module mx.uv.fei.parkingcity {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
    requires java.sql;

    opens mx.uv.fei.parkingcity.logic to javafx.fxml;
    opens mx.uv.fei.parkingcity.gui to javafx.fxml;
    exports mx.uv.fei.parkingcity.gui;
}