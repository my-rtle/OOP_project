package db;

public class DBTest {
    public static void main(String[] args) {
        ScheduleDAO dao = new ScheduleDAO();
        dao.insertSchedule("회의", "2025-05-20", "10:00:00");
    }
}