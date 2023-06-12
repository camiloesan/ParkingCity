package mx.uv.fei.parkingcity.logic;

public class Ticket {
    private int ticketID;
    private String checkIN;
    private String checkOUT;
    private int slotID;

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getCheckIN() {
        return checkIN;
    }

    public void setCheckIN(String checkIN) {
        this.checkIN = checkIN;
    }

    public String getCheckOUT() {
        return checkOUT;
    }

    public void setCheckOUT(String checkOUT) {
        this.checkOUT = checkOUT;
    }

    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }
}
