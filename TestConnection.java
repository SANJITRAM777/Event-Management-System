import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/event_db",
                "root",
                "root123"
            );

            System.out.println("✅ Connected to MySQL!");

        } catch (Exception e) {
            System.out.println("❌ Connection Failed");
            e.printStackTrace();
        }
    }
}