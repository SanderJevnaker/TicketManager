enum SEATSTATE {free, reserved, randomReserved, randomCollected, selected};

public class Seat {
    Customer customer;
    int rowNdx;
    int seatNdx;
    SEATSTATE seatState;

    Seat(int rowNdx, int seatNdx) {
        this.customer = null;
        this.rowNdx = rowNdx;
        this.seatNdx = seatNdx;
        this.seatState = SEATSTATE.free;
    }
}