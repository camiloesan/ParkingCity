package mx.uv.fei.parkingcity.dao;

import mx.uv.fei.parkingcity.dataaccess.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TicketDAO implements ITicketDAO{
    @Override
    public int registerEntry(int slotID) throws SQLException{
        int result;
        String sqlQuery = "INSERT INTO tickets (check_in, slot_id) VALUES (NOW(), (?))";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();
        
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, slotID);
        result = preparedStatement.executeUpdate();
        
        databaseManager.closeConnection();
        return result;
    }
    
    @Override
    public int registerExit(int ticketID) throws SQLException {
        int result;
        String sqlQuery = "UPDATE tickets SET check_out = (NOW()) WHERE ticket_id = (?)";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();
        
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        
        preparedStatement.setInt(1, ticketID);
        result = preparedStatement.executeUpdate();
        
        databaseManager.closeConnection();
        return result;
    }
}
