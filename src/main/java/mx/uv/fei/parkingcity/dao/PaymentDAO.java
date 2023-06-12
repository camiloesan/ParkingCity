package mx.uv.fei.parkingcity.dao;

import mx.uv.fei.parkingcity.dataaccess.DatabaseManager;
import mx.uv.fei.parkingcity.logic.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
