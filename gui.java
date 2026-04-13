import javax.swing.*;
import java.awt.event.*;

public class gui {
     static JTable table;
     static JScrollPane scrollPane;
     public static void main(String[] args) {

        events service = new events();
       

        JFrame frame = new JFrame("Event Management System");
        frame.setSize(400, 400);
        frame.setLayout(null);

        JLabel nameLabel = new JLabel("Event Name:");
        nameLabel.setBounds(30, 50, 100, 30);
        frame.add(nameLabel);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(30, 100, 100, 30);
        frame.add(dateLabel);

        JLabel locLabel = new JLabel("Location:");
        locLabel.setBounds(30, 150, 100, 30);
        frame.add(locLabel);

        JLabel idLabel = new JLabel("Event ID:");
        idLabel.setBounds(30, 200, 100, 30);
        frame.add(idLabel);


        JTextField nameField = new JTextField();
        nameField.setBounds(150, 50, 150, 30);
        frame.add(nameField);

        JTextField dateField = new JTextField();
        dateField.setBounds(150, 100, 150, 30);
        frame.add(dateField);

        JTextField locField = new JTextField();
        locField.setBounds(150, 150, 150, 30);
        frame.add(locField);

        JTextField idField = new JTextField();
        idField.setBounds(150, 200, 150, 30);
        frame.add(idField);

        JLabel pnameLabel = new JLabel("Name:");
pnameLabel.setBounds(30, 450, 100, 30);
frame.add(pnameLabel);

JTextField pnameField = new JTextField();
pnameField.setBounds(150, 450, 150, 30);
frame.add(pnameField);

JLabel emailLabel = new JLabel("Email:");
emailLabel.setBounds(30, 500, 100, 30);
frame.add(emailLabel);

JTextField emailField = new JTextField();
emailField.setBounds(150, 500, 150, 30);
frame.add(emailField);

JLabel peventLabel = new JLabel("Event ID:");
peventLabel.setBounds(30, 550, 100, 30);
frame.add(peventLabel);

JTextField peventField = new JTextField();
peventField.setBounds(150, 550, 150, 30);
frame.add(peventField);


        // Button
        JButton addButton = new JButton("Add Event");
        addButton.setBounds(120, 250, 150, 40);
        frame.add(addButton);

        JButton viewButton = new JButton("View Events");
        viewButton.setBounds(160, 310, 120, 40);
        frame.add(viewButton);

        JButton updateButton = new JButton("Update Event");
        updateButton.setBounds(30, 310, 120, 40);
        frame.add(updateButton);

        JButton deleteButton = new JButton("Delete Event");
        deleteButton.setBounds(290, 310, 140, 40);
        frame.add(deleteButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(120, 600, 120, 40);
        frame.add(registerButton);

        JButton viewPartButton = new JButton("View Participants");
        viewPartButton.setBounds(100, 660, 180, 40);
        frame.add(viewPartButton);
        

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String date = dateField.getText();
                String location = locField.getText();

                service.addEvent(name, date, location);

                JOptionPane.showMessageDialog(frame, "Event Added!");

                nameField.setText("");
                dateField.setText("");
                locField.setText("");
            }
    });
    viewButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try {
            String[] columnNames = {"ID", "Name", "Date", "Location"};

            java.sql.Statement st = service.con.createStatement();
            java.sql.ResultSet rs = st.executeQuery("SELECT * FROM events");

            java.util.ArrayList<Object[]> data = new java.util.ArrayList<>();

            while (rs.next()) {
                data.add(new Object[]{
                        rs.getInt("event_id"),
                        rs.getString("event_name"),
                        rs.getDate("event_date"),
                        rs.getString("location")
                });
            }

            Object[][] tableData = new Object[data.size()][4];
            for (int i = 0; i < data.size(); i++) {
                tableData[i] = data.get(i);
            }

            table = new JTable(tableData, columnNames);

            if (scrollPane != null) frame.remove(scrollPane);

            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(20, 330, 350, 150);
            frame.add(scrollPane);

            frame.revalidate();
            frame.repaint();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
});
updateButton.addActionListener(e -> {
    int id = Integer.parseInt(idField.getText());
    String newName = nameField.getText();

    service.updateEvent(id, newName);

    JOptionPane.showMessageDialog(frame, "Event Updated!");
});
deleteButton.addActionListener(e -> {
    int id = Integer.parseInt(idField.getText());

    service.deleteEvent(id);

    JOptionPane.showMessageDialog(frame, "Event Deleted!");
});
registerButton.addActionListener(e -> {
    String name = pnameField.getText();
    String email = emailField.getText();
    int eventId = Integer.parseInt(peventField.getText());

    service.registerParticipant(name, email, eventId);

    JOptionPane.showMessageDialog(frame, "Participant Registered!");
});
viewPartButton.addActionListener(e -> {
    try {
        int eventId = Integer.parseInt(peventField.getText());

        String[] cols = {"ID", "Name", "Email"};

        java.sql.PreparedStatement ps = service.con.prepareStatement(
                "SELECT * FROM participants WHERE event_id=?"
        );
        ps.setInt(1, eventId);

        java.sql.ResultSet rs = ps.executeQuery();

        java.util.ArrayList<Object[]> data = new java.util.ArrayList<>();

        while (rs.next()) {
            data.add(new Object[]{
                    rs.getInt("participant_id"),
                    rs.getString("name"),
                    rs.getString("email")
            });
        }

        Object[][] tableData = new Object[data.size()][3];
        for (int i = 0; i < data.size(); i++) {
            tableData[i] = data.get(i);
        }

        table = new JTable(tableData, cols);

        if (scrollPane != null) frame.remove(scrollPane);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 720, 350, 150);
        frame.add(scrollPane);

        frame.revalidate();
        frame.repaint();

    } catch (Exception ex) {
        ex.printStackTrace();
    }
});

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
    

