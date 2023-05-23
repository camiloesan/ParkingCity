package mx.uv.fei.parkingcity.dao;

import mx.uv.fei.parkingcity.logic.ParkingSlot;

import java.sql.SQLException;
import java.util.List;

public interface IParkingSlotsDAO {
    List<ParkingSlot> getAvailableParkingSlots() throws SQLException;
}
