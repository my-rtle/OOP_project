package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ScheduleDAO {
    public void insertSchedule(String title, String date, String startTime) {
        String sql = "INSERT INTO schedule (title, date, start_time) VALUES (?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, title);
            stmt.setString(2, date);
            stmt.setString(3, startTime);

            stmt.executeUpdate();
            System.out.println("Schedule inserted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}