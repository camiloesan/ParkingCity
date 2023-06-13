package mx.uv.fei.parkingcity.dao;

import mx.uv.fei.parkingcity.logic.ParkingSlot;

import java.sql.SQLException;
import java.util.List;

public interface IParkingSlotsDAO {
    List<ParkingSlot> getAvailableParkingSlotsByLevel(String levelName) throws SQLException;
    int reserveSlot(String levelName, int slotID) throws SQLException;

    int openSlot(String levelName, int slotID) throws SQLException;
}
