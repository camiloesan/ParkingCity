package mx.uv.fei.parkingcity.logic;

import java.sql.Date;

public class ParkingSlot {
    int slot_id;
    String available;
    Date check_in;
    Date check_out;

    public ParkingSlot(int slot_id, String available, Date check_in, Date check_out) {
        this.slot_id = slot_id;
        this.available = available;
        this.check_in = check_in;
        this.check_out = check_out;
    }

    public ParkingSlot() {
    }

    public int getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public Date getCheck_in() {
        return check_in;
    }

    public void setCheck_in(Date check_in) {
        this.check_in = check_in;
    }

    public Date getCheck_out() {
        return check_out;
    }

    public void setCheck_out(Date check_out) {
        this.check_out = check_out;
    }
}
