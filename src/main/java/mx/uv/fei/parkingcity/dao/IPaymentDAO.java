package mx.uv.fei.parkingcity.dao;

import mx.uv.fei.parkingcity.logic.Payment;

import java.sql.SQLException;
import java.time.LocalDateTime;

public interface IPaymentDAO {
    int registerPayment(Payment payment) throws SQLException;

    LocalDateTime getDateTimeByPaymentID(int paymentID) throws SQLException;
}
