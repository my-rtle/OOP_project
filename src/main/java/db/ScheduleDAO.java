package db;

import model.Schedule;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO {

    // 1) 일정 추가 :contentReference[oaicite:2]{index=2}:contentReference[oaicite:3]{index=3}
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

    // 2) 일정 삭제
    public boolean deleteSchedule(int id) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affected = stmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3) 일정 수정
    public boolean updateSchedule(int id, String title, String date,
                                  String startTime, String endTime) {
        String sql = "UPDATE schedule "
                + "SET title = ?, date = ?, start_time = ?, end_time = ? "
                + "WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, title);
            stmt.setString(2, date);
            stmt.setString(3, startTime);
            stmt.setString(4, endTime);
            stmt.setInt(5, id);
            int affected = stmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4) 날짜별 일정 조회
    public List<Schedule> getSchedulesByDate(String date) {
        String sql = "SELECT id, title, description, date, start_time, end_time "
                + "FROM schedule WHERE date = ?";
        List<Schedule> list = new ArrayList<>();
        try (Connection conn = DBConnector.getConnection();       // :contentReference[oaicite:4]{index=4}:contentReference[oaicite:5]{index=5}
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, date);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Schedule s = new Schedule();
                s.setId(rs.getInt("id"));
                s.setTitle(rs.getString("title"));
                s.setDescription(rs.getString("description"));
                s.setDate(rs.getString("date"));
                s.setStartTime(rs.getString("start_time"));
                s.setEndTime(rs.getString("end_time"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
