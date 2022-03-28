import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CustomerForm extends GuiBase implements Form {

    private final Map<String, JButton> buttons = new HashMap<>();

    private final JLabel promptName = new JLabel("Name");
    private final JLabel promptPhone = new JLabel("Telephone");
    private final JLabel promptType = new JLabel("Type");
    private final JLabel promptContactPerson = new JLabel("Contact person");
    private final JLabel promptSeats = new JLabel("Seat");


    private final JTextField valueId = new JTextField();
    private final JTextField valueName = new JTextField();
    private final JTextField valuePhone = new JTextField();
    private final JComboBox ddCustomerTypes = new JComboBox();
    private final JTextArea valueSeats = new JTextArea();

    private static class C { // Constants
        static class size {
            static final Dimension dialog = new Dimension(380, 250);
        }
        static class pos {
            static final Point dialog = new Point(560, 250);
        }
        static class height {
            static final int fields = 190;
            static final int buttons = 36;
        }
        static class color {
            static final Color seatsBackground = Color.white; // valueSeats: Reset to inputBackgroundColor
            static final Color highlightColor = new Color(0, 255, 27); // valueSeats
            static final Color changedField = Color.yellow;
        }
        static class background {
            static final Color baseBackground = Color.white;
            static final Color customerForm = Color.red; // customerForm (should be hidden by formFields panel)
            static final Color formFields = new Color(45,150,255); // formFields
            static final Color seats = Color.white; // seats
            static final Color formButtons = new Color(228,228,228); // formButtons
        }
        static class border {
            static Border field; // Set by constructor
        }
    }



    public CustomerForm(CustomerTable table, Form.FORM_ACTION action) {
        Debug.console("CustomerForm, action: " + action);
        //table.getCustomers();
        String selectedId = table.getSelectedId();
        Customer customer = Customers.find(Integer.valueOf(selectedId));
        if (action == FORM_ACTION.INSERT) {
            customer = Customer.factory(customer.getEType());
        } else {
            customer = customer.copy();
        }
        boolean foo = true;
        JDialog dialog = new JDialog();
        dialog.setTitle(ACTION.get(action) + " Customer");

        JPanel form = new JPanel();

        buttons.put("pickUp", new JButton("Get tickets"));
        buttons.put("ok", new JButton("Ok"));
        buttons.put("cancel", new JButton("Cancel"));

        dialog.setVisible(true);



       /* GridBagLayout gblMatrix = new GridBagLayout();
        gblMatrix.columnWidths = new int[seatCount];
        gblMatrix.columnWeights = new double[seatCount];
        gblMatrix.rowHeights = new int[rowCount];
        gblMatrix.rowWeights = new double[rowCount];

        */
    }
}
