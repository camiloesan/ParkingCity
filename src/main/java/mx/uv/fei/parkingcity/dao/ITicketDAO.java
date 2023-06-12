package mx.uv.fei.parkingcity.dao;

import mx.uv.fei.parkingcity.logic.Ticket;

import java.sql.SQLException;
import java.time.LocalDateTime;

public interface ITicketDAO {
    int registerEntry(int slotID) throws SQLException;
    int registerExit(int ticketID) throws SQLException;
    Ticket getTicketByTicketID(int ticketID) throws SQLException;
    int getSlotIDByTicketID(int ticketID) throws SQLException;
    LocalDateTime getCheckInByTicketID(int ticketID) throws SQLException;
}
