package mx.uv.fei.parkingcity.dao;

import mx.uv.fei.parkingcity.dataaccess.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaymentDAO implements IPaymentDAO {

    @Override
    public int registerPayment(int ticketID) throws SQLException {
        int result;
        String sqlQuery = "INSERT INTO pagos (ticket_id) VALUES (?)";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, ticketID);
        result = preparedStatement.executeUpdate();

        databaseManager.closeConnection();
        return result;
    }

    @Override
    public LocalDateTime getDateTimeByPaymentID(int paymentID) throws SQLException {
        String query = "SELECT payment_datetime FROM pagos WHERE pago_id = ?";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, paymentID);
        ResultSet resultSet = preparedStatement.executeQuery();

        String checkIn = null;
        while (resultSet.next()) {
            checkIn = resultSet.getString("payment_datetime");
        }
        LocalDateTime result = LocalDateTime.parse(checkIn, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        databaseManager.closeConnection();

        return result;
    }

    @Override
    public int updatePayment(int ticketID) throws SQLException {
        int result;
        String sqlQuery = "UPDATE pagos SET state = 'pagado', payment_datetime = NOW() WHERE ticket_id = (?)";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, ticketID);
        result = preparedStatement.executeUpdate();

        databaseManager.closeConnection();
        return result;
    }
}
