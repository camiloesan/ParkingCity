package mx.uv.fei.parkingcity.dao;

import mx.uv.fei.parkingcity.dataaccess.DatabaseManager;
import mx.uv.fei.parkingcity.logic.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Ticket getTicketByTicketID(int ticketID) throws SQLException {
        String query = "SELECT * FROM tickets WHERE ticket_id = ?";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, ticketID);
        ResultSet resultSet = preparedStatement.executeQuery();

        Ticket ticket = new Ticket();

        while (resultSet.next()) {
            ticket.setTicketID(resultSet.getInt("ticket_id"));
            ticket.setCheckIN(resultSet.getString("check_id"));
            ticket.setCheckOUT(resultSet.getString("check_out"));
            ticket.setSlotID(resultSet.getInt("slot_id"));
        }

        databaseManager.closeConnection();

        return ticket;
    }

    @Override
    public int getSlotIDByTicketID(int ticketID) throws SQLException {
        String query = "SELECT slot_id FROM tickets WHERE ticket_id = ?";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, ticketID);
        ResultSet resultSet = preparedStatement.executeQuery();

        int result = 0;
        while (resultSet.next()) {
            result = resultSet.getInt("slot_id");
        }

        databaseManager.closeConnection();

        return result;
    }

    @Override
    public LocalDateTime getCheckInByTicketID(int ticketID) throws SQLException {
        String query = "SELECT check_in FROM tickets WHERE ticket_id = ?";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, ticketID);
        ResultSet resultSet = preparedStatement.executeQuery();

        String checkIn = null;
        while (resultSet.next()) {
            checkIn = resultSet.getString("check_in");
        }
        LocalDateTime result = LocalDateTime.parse(checkIn, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        databaseManager.closeConnection();

        return result;
    }

    @Override
    public int getLastTicketID() throws SQLException {
        String query = "select max(ticket_id) from tickets";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        int result = 0;
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            result = resultSet.getInt("max(ticket_id)");
        }

        databaseManager.closeConnection();
        return result;
    }

    @Override
    public List<Integer> getTicketsWithoutPay() throws SQLException {
        String query = "SELECT tickets.ticket_id FROM tickets " +
                "INNER JOIN pagos on pagos.ticket_id = tickets.ticket_id " +
                "WHERE pagos.state = 'pendiente'";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Integer> slotsList = new ArrayList<>();
        while (resultSet.next()) {
            slotsList.add(resultSet.getInt("ticket_id"));
        }

        databaseManager.closeConnection();

        return slotsList;
    }

    @Override
    public List<Integer> getTicketsPay() throws SQLException {
        String query = "SELECT tickets.ticket_id FROM tickets " +
                "INNER JOIN pagos on pagos.ticket_id = tickets.ticket_id " +
                "WHERE pagos.state = 'pagado'";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Integer> slotsList = new ArrayList<>();
        while (resultSet.next()) {
            slotsList.add(resultSet.getInt("ticket_id"));
        }

        databaseManager.closeConnection();

        return slotsList;
    }

}
