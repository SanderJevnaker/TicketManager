import java.util.ArrayList;
import java.util.List;

public class Customers {
    static int nextId = 1;
    static final List<Customer> list = new ArrayList<>();
    static List<Customer> getDummyData() {
        Customer customer = new Customer.Private();
        customer.setId(nextId++);
        customer.setName("Ole Olsen Lorem Ipsumius");
        customer.setPhoneNumber("4121412");
        customer.setEType(EType.PRIVATE);
        list.add(customer);

        customer = new Customer.Random();
        customer.setId(nextId++);
        customer.setName("Bolle mann");
        customer.setPhoneNumber("113");
        customer.setEType(EType.RANDOM);
        list.add(customer);

        customer = new Customer.Company();
        customer.setId(nextId++);
        customer.setName("Oskar");
        customer.setPhoneNumber("5123124");
        ((Customer.Company) customer).setContactPerson("Sander"); //CASTING
        customer.setEType(EType.COMPANY);
        list.add(customer);

        return list;
    }
}
