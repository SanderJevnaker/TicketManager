import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
        JPanel jFields = new JPanel();
        JPanel buttonsPanel = new JPanel();
        form.setName("form");
        jFields.setName("jFields");
        buttonsPanel.setName("buttonsPanel");

        // Outer form: All but window-title
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{C.size.dialog.width};
        gbl.columnWeights = new double[]{0.0};
        gbl.rowHeights = new int[]{C.height.fields, C.height.buttons};
        gbl.rowWeights = new double[]{0.0, 0.0};
        form.setLayout(gbl);

        // Fields panel: 2 columns, 5 rows
        GridBagLayout gblF = new GridBagLayout();
        gblF.columnWidths = new int[]{100, 200};
        gblF.columnWeights = new double[]{0.0, 0.0};
        gblF.rowHeights = new int[]{30, 30, 30, 30, 30};
        gblF.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        jFields.setLayout(gblF);


        /*
        gblC.weightx = 1;
        gblC.weighty = 1;
        gblC.fill = GridBagConstraints.BOTH;

         */

        jFields.add(promptName, makeConstraints(0,0));
        jFields.add(promptPhone, makeConstraints(0,1));
        jFields.add(promptType, makeConstraints(0,2));
        jFields.add(promptContactPerson, makeConstraints(0,3));
        jFields.add(promptSeats, makeConstraints(0,4));
        jFields.add(valueId);
        jFields.add(valueName, makeConstraints(1,0));
        jFields.add(valuePhone, makeConstraints(1,1));
        if (customer.getEType()==Customer.EType.COMPANY) {
            jFields.add(promptContactPerson, makeConstraints(0,3));
            jFields.add(valueContact, makeConstraints(1,3));
        }
        jFields.add(valueSeats, makeConstraints(1,4));

        buttons.put("pickUp", new JButton("Get tickets"));
        buttons.put("ok", new JButton("OK"));
        buttons.put("cancel", new JButton("Cancel"));
        buttonsPanel.add(buttons.get("pickUp"));
        buttonsPanel.add(buttons.get("ok"));
        buttonsPanel.add(buttons.get("cancel"));

        valueId.setVisible(false);
        valueSeats.setEditable(false);
        valueId.setText(String.valueOf(customer.getId()));
        valueName.setText(customer.getName());
        valuePhone.setText(customer.getPhoneNumber());
        if (customer.getEType()==Customer.EType.COMPANY) {
            valueContact.setText(((Customer.Company) customer).getContactPerson());
        }
        Seat[] seats = customer.getSeats();
        String[] seatsTxt = new String[seats.length];
        int i = 0;
        for (Seat seat : seats) {
            seatsTxt[i++] = "Section " + (seat.getSectionNdx()+1) + ", row " + (seat.getRowNdx()+1) + ", seat " + (seat.getSeatNdx()+1);
        }
        valueSeats.setText(Arrays.stream(seatsTxt).collect(Collectors.joining("\n")));

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        jScrollPane.add(jFields);

        jScrollPane.setBackground(Color.red);

        form.add(jScrollPane, makeConstraints(0,0));
        form.add(buttonsPanel, makeConstraints(0,1));
        jFields.setBackground(Color.cyan);

        dialog.add(form);
        dialog.setLocation(C.pos.dialog.x, C.pos.dialog.y);

        // TODO: Not required?
        dialog.setSize(C.size.dialog.width, C.size.dialog.height);

        dialog.setVisible(true);
        dialog.pack();



    }
}
