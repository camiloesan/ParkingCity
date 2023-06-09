package mx.uv.fei.parkingcity.dao;

import mx.uv.fei.parkingcity.dataaccess.DatabaseManager;
import mx.uv.fei.parkingcity.logic.ParkingSlot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingSlotsDAO implements IParkingSlotsDAO {
    @Override
    public List<ParkingSlot> getAvailableParkingSlotsByLevel(String levelName) throws SQLException {
        String query = "SELECT * FROM " + levelName;
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, levelName);
        ResultSet resultSet = preparedStatement.executeQuery();
        databaseManager.closeConnection();

        List<ParkingSlot> availableParkingSlotList = new ArrayList<>();
        while(resultSet.next()) {
            ParkingSlot parkingSlot = new ParkingSlot();
            parkingSlot.setSlot_id(resultSet.getInt("slot_id"));
            parkingSlot.setAvailable(resultSet.getString("available"));
            availableParkingSlotList.add(parkingSlot);
        }

        return availableParkingSlotList;
    }
    
    @Override
    public int reserveSlot(String levelName, int slotID) throws SQLException {
        int result;
        String sqlQuery = "UPDATE " + levelName + " SET available = 'no' WHERE slot_id = (?)";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();
        
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, slotID);
        result = preparedStatement.executeUpdate();
        
        databaseManager.closeConnection();
        return result;
    }

    @Override
    public int openSlot(String levelName, int slotID) throws SQLException {
        int result;
        String sqlQuery = "UPDATE " + levelName + " SET available = 'yes' WHERE slot_id = (?)";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, slotID);
        result = preparedStatement.executeUpdate();

        databaseManager.closeConnection();
        return result;
    }
}
