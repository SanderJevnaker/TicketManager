import java.util.List;

public class Theatre {
    private String name;
    Section[] sections = new Section[1];
    List<Customer> list;
    Gui gui;
    private int[] rowSeatCount;


    public Theatre(String name) {
        this.name = name;
        gui = new Gui(this);
        rowSeatCount = new int[]{15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15};
        sections[0] = new Section(0, "Hoved", rowSeatCount);
        list = Customers.getDummyData();
        for (Customer customer : list) {
            int sectionNdx;
            int rowNdx;
            int seatNdx;
            Seat[] seats = customer.getSeats();
            for (Seat customerSeat : seats) {
                sectionNdx = customerSeat.getSectionNdx();
                rowNdx = customerSeat.getRowNdx();
                seatNdx = customerSeat.getSeatNdx();
                Section.Row r[] = sections[sectionNdx].rows;
                r[rowNdx].seats[seatNdx] = customerSeat;
            }
        }
        gui.showSeatPanel(sections[0]);
        gui.frame.pack();
        Debug.console(this.toString());
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Theatre: ").append(name).append(Debug.NL);

        for (Section section : sections) {
            str.append(section.toString());
        }
        return str.toString();
    }
}