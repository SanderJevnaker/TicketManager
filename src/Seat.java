;

public class Seat {
    enum STATE {free, reserved, randomReserved, randomCollected, selected}

    private Customer customer;
    private int rowNdx;
    private int seatNdx;
    private STATE seatState;
    private int sectionNdx;

    Seat(int section, int rowNdx, int seatNdx, Customer customer) {
        this.customer = customer;
        this.rowNdx = rowNdx;
        this.seatNdx = seatNdx;
        this.seatState = STATE.free;
        this.sectionNdx = section;
    }

    Seat(int section, int rowNdx, int seatNdx) {
        this.customer = null;
        this.rowNdx = rowNdx;
        this.seatNdx = seatNdx;
        this.seatState = STATE.free;
        this.sectionNdx = section;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getRowNdx() {
        return rowNdx;
    }

    public void setRowNdx(int rowNdx) {
        this.rowNdx = rowNdx;
    }

    public int getSeatNdx() {
        return seatNdx;
    }

    public void setSeatNdx(int seatNdx) {
        this.seatNdx = seatNdx;
    }

    public STATE getSeatState() {
        return seatState;
    }

    public void setSeatState(STATE seatState) {
        this.seatState = seatState;
    }

    public int getSectionNdx() {
        return sectionNdx;
    }

    public void setSection(int section) {
        this.sectionNdx = section;
    }

    static Seat[] copy(Seat[] seats) {
        Seat[] seatS = new Seat[seats.length];
        for (int i = 0; i < seats.length; i++) {
            seatS[i] = seats[i];
            seatS[i].seatState = seats[i].seatState;
            seatS[i].customer = seats[i].customer;
            seatS[i].sectionNdx = seats[i].sectionNdx;
            seatS[i].rowNdx = seats[i].rowNdx;
            seatS[i].seatNdx = seats[i].seatNdx;
        }
        return seatS;
    }

}
