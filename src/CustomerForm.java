import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
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
    private final JTextField valueContact = new JTextField();
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
        String selectedId = table.getSelectedId();
        Customer customer = Customers.find(Integer.valueOf(selectedId));
        if (action == FORM_ACTION.INSERT) {
            customer = Customer.factory(customer.getEType());
        } else {
            customer = customer.copy();
        }
        buttons.put("pickUp", new JButton("Get tickets"));
        buttons.put("ok", new JButton("OK"));
        buttons.put("cancel", new JButton("Cancel"));

        // Create dialog
        JDialog dialog = new JDialog();
        dialog.setTitle(ACTION.get(action) + " Customer");

        JPanel form = new JPanel(); // Outer form: All but window-title - 1 column, 2 rows
        JPanel jFields = new JPanel(); // All field prompts and values - 2 columns, 5 rows
        JPanel buttonsPanel = new JPanel(); // All buttons - no grid
        JScrollPane jScrollPane = new JScrollPane(); // scroll-panel for jFields
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Informative only
        form.setName("form");
        jFields.setName("jFields");
        buttonsPanel.setName("buttonsPanel");
        jScrollPane.setName("jScrollPane");

        // Outer form: 1 column, 2 rows (All but window-title)
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

        // Add all prompts to fields-panel
        jFields.add(promptName, makeConstraints(0,0));
        jFields.add(promptPhone, makeConstraints(0,1));
        jFields.add(promptType, makeConstraints(0,2));
        if (customer.getEType()==Customer.EType.COMPANY) {
            jFields.add(promptContactPerson, makeConstraints(0,3));
        }
        jFields.add(promptSeats, makeConstraints(0,4));
        // Add all values to fields-panel
        jFields.add(valueId);
        jFields.add(valueName, makeConstraints(1,0));
        jFields.add(valuePhone, makeConstraints(1,1));
//      jFields.add(ddCustomerTypes, makeConstraints(1,2));
        if (customer.getEType()==Customer.EType.COMPANY) {
            jFields.add(valueContact, makeConstraints(1,3));
        }

        jFields.add(new JScrollPane(valueSeats), makeConstraints(1,4));

        // All field- prompts and values added to jFields

        // Add all buttons to buttons panel
        buttonsPanel.add(buttons.get("pickUp"));
        buttonsPanel.add(buttons.get("ok"));
        buttonsPanel.add(buttons.get("cancel"));

        // jFields and buttonsPanel completely populated

        // Set all field values
        valueId.setVisible(false);
        valueId.setText(String.valueOf(customer.getId()));
        valueName.setText(customer.getName());
        valuePhone.setText(customer.getPhoneNumber());
        if (customer.getEType()==Customer.EType.COMPANY) {
            valueContact.setText(((Customer.Company) customer).getContactPerson());
        }

//        final Dimension maxSize = valueSeats.getPreferredSize();
//        maxSize.height = 100;
        //valueSeats.setAutoscrolls(true);
//        valueSeats.setPreferredSize(maxSize);
//        valueSeats.setMinimumSize(maxSize);
        //valueSeats.setMaximumSize(maxSize);
        //valueSeats.revalidate();

        Seat[] seats = customer.getSeats();
        String[] seatsTxt = new String[seats.length];
        int i = 0;
        for (Seat seat : seats) {
            seatsTxt[i++] = "Section " + (seat.getSectionNdx()+1) + ", row " + (seat.getRowNdx()+1) + ", seat " + (seat.getSeatNdx()+1);
        }
        valueSeats.setText(Arrays.stream(seatsTxt).collect(Collectors.joining("\n")));
        valueSeats.setEditable(false);
        valueSeats.setRows(5);
        // Add jFields to scroll-panel  ← Problem
        //jScrollPane.add(jFields);  ← Problem - use setViewportView
        // Set jFields as content in jScrollPane
        //jScrollPane.setViewportView(jFields); // ← Ok
//        final Dimension preferredSize = jScrollPane.getPreferredSize();
//        preferredSize.height = 20;
//        jScrollPane.setPreferredSize(preferredSize);
//        jScrollPane.setMaximumSize(preferredSize);
        // Add scroll-panel to form row 0
        //form.add(jScrollPane, makeConstraints(0,0));
        // Test without scroll-panel
        form.add(jFields, makeConstraints(0,0));

        // Add buttons to form row 1
        form.add(buttonsPanel, makeConstraints(0,1));

        // All fields and buttons added to form
        // Add form to dialog
        dialog.add(form);
        dialog.setLocation(C.pos.dialog.x, C.pos.dialog.y);

        // TODO: Not required?
        dialog.setSize(C.size.dialog.width, C.size.dialog.height);

        // Debug!
        jScrollPane.setBackground(Color.red);
        jFields.setBackground(Color.blue);

        jScrollPane.add(new JLabel("Heisan!"));

        dialog.setVisible(true);
        dialog.pack();



    }
}
