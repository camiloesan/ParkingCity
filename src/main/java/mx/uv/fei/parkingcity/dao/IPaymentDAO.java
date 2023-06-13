package mx.uv.fei.parkingcity.dao;

import mx.uv.fei.parkingcity.logic.Payment;

import java.sql.SQLException;
import java.time.LocalDateTime;

public interface IPaymentDAO {
    int registerPayment(int ticketID) throws SQLException;
    LocalDateTime getDateTimeByPaymentID(int paymentID) throws SQLException;

    int updatePayment(int ticketID) throws SQLException;
}
