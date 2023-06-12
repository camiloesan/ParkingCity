package mx.uv.fei.parkingcity.dao;

import mx.uv.fei.parkingcity.logic.Payment;

import java.sql.SQLException;

public interface IPaymentDAO {
    int registerPayment(Payment payment) throws SQLException;
}
