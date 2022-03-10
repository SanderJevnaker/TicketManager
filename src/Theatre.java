import java.util.List;

public class Theatre {
    private String name;
    Section[] sections = new Section[1];
    List<Customer> list;
    public Theatre(String name) {
        this.name = name;
        int[] rowSeatCount = new int[] {15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15};
        sections[0] = new Section(0, "Hoved", rowSeatCount);
        list = Customers.getDummyData();
        for (Customer customer : list) {
            int sectionNdx;
            int rowNdx;
            int seatNdx;
             Seat[] seats = customer.getSeats();
             for (Seat customerSeat : seats) {
                 sectionNdx = customerSeat.getSection();
                 rowNdx = customerSeat.getRowNdx();
                 seatNdx = customerSeat.getSeatNdx();
                 Section.Row r[] = sections[sectionNdx].rows;
                 Seat sectionSeat = r[rowNdx].seats[seatNdx];
                 boolean foo = true;
                 r[rowNdx].seats[seatNdx] = customerSeat;
             }
        }

/*        for (Section section : sections) {
            for (Section.Row r : section.rows) {
                boolean foo = true;
            }
            Debug.console(section.toString());
        }   */




        StringBuilder str = new StringBuilder();
        str.append("Theatre: ").append(name).append(Debug.NL).
                append("\tRows: ").append(rowSeatCount.length);
        Debug.console(str.toString());
        for (Customer customer : list) {
            Debug.console(customer.toString());
        }
        for (Section section : sections) {
            Debug.console(section.toString());
        }
        boolean foo = true;
    }
    @Override
    public String toString() {

        return  "";
    }
}
