package mx.uv.fei.parkingcity.logic;

public class TransferTicket {
    private static int ticketID;

    public static int getTicketID() {
        return ticketID;
    }

    public static void setTicketID(int ticketID) {
        TransferTicket.ticketID = ticketID;
    }
}
