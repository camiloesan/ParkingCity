package mx.uv.fei.parkingcity.logic;

public class CloneFactory {
    public Shape getClone(Arrow arrowSample) {
        return arrowSample.makeCopy();
    }

    public Shape getClone(ParkingSlot parkingSlotSample) {
        return parkingSlotSample.makeCopy();
    }
}
