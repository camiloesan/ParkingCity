package mx.uv.fei.parkingcity.dao;

import java.sql.SQLException;

public interface ITicketDAO {
    int registerEntry(int slotID) throws SQLException;
    int registerExit(int ticketID) throws SQLException;
}
