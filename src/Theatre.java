import java.util.List;

public class Theatre {
    private String name;
    Section[] sections = new Section[1];
    public Theatre(String name) {
        this.name = name;
        int[] rowSeatCount = new int[] {15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15};
        sections[0] = new Section("Hoved", rowSeatCount);
        List<Customer> list = Customers.getDummyData();
        for (Customer customer : list) {
            Debug.console(customer.toString());
        }
        for (Section section : sections) {
            Debug.console("Section: " + Debug.NL + section.toString());
        }
    }
}
