package mx.uv.fei.parkingcity.dao;

import mx.uv.fei.parkingcity.dataaccess.DatabaseManager;
import mx.uv.fei.parkingcity.logic.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaymentDAO implements IPaymentDAO {

    @Override
    public int registerPayment(Payment payment) throws SQLException {
        int result;
        String sqlQuery = "INSERT INTO pagos (nivel, slot, payment_datetime) VALUES (?,?,NOW())";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, payment.getLevel());
        preparedStatement.setInt(2, payment.getSlotID());
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
}
