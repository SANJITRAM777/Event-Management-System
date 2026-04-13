import java.sql.*;
public class events {
    Connection con;
    public events() {
         try {
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/event_db",
                "root",
                "root123"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void addEvent(String name, String date, String location) {
        try {
            String query = "INSERT INTO events(event_name, event_date, location) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, date);
            ps.setString(3, location);

            ps.executeUpdate();
            System.out.println("✅ Event Added!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public void viewEvents() {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM events");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("event_id") + " | " +
                        rs.getString("event_name") + " | " +
                        rs.getDate("event_date") + " | " +
                        rs.getString("location")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public void updateEvent(int id, String newName) {
        try {
            String query = "UPDATE events SET event_name=? WHERE event_id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, newName);
            ps.setInt(2, id);

            ps.executeUpdate();
            System.out.println("✅ Event Updated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public void deleteEvent(int id) {
        try {
            String query = "DELETE FROM events WHERE event_id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("✅ Event Deleted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void registerParticipant(String name, String email, int eventId) {
    try {
        String query = "INSERT INTO participants(name, email, event_id) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, name);
        ps.setString(2, email);
        ps.setInt(3, eventId);

        ps.executeUpdate();
        System.out.println("✅ Participant Registered!");

    } catch (Exception e) {
        e.printStackTrace();
    }
}
public void viewParticipantsByEvent(int eventId) {
    try {
        String query = "SELECT * FROM participants WHERE event_id=?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, eventId);

        ResultSet rs = ps.executeQuery();

        System.out.println("\nParticipants List:");
        while (rs.next()) {
            System.out.println(
                rs.getInt("participant_id") + " | " +
                rs.getString("name") + " | " +
                rs.getString("email")
            );
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    
}
