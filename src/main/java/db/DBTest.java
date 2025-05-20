/*
package db;

public class DBTest {
    public static void main(String[] args) {
        ScheduleDAO dao = new ScheduleDAO();
        dao.insertSchedule("회의", "2025-05-20", "10:00:00");
    }
}

 */
package db;

import model.Schedule;
import java.util.List;

public class DBTest {
    public static void main(String[] args) {
        ScheduleDAO dao = new ScheduleDAO();
        String testDate = "2025-05-22";

        // 1) 삽입 테스트
        System.out.println("=== INSERT TEST ===");
        dao.insertSchedule("테스트 일정", testDate, "08:00:00");

        // 2) 조회 테스트 (삽입 후)
        System.out.println("\n=== SELECT TEST (after insert) ===");
        List<Schedule> list = dao.getSchedulesByDate(testDate);
        for (Schedule s : list) {
            System.out.printf("id=%d, title=%s, time=%s\n",
                    s.getId(), s.getTitle(), s.getStartTime());
        }

        // 3) 수정 테스트
        if (!list.isEmpty()) {
            Schedule toUpdate = list.get(0);
            System.out.println("\n=== UPDATE TEST ===");
            boolean updated = dao.updateSchedule(
                    toUpdate.getId(),
                    "업데이트된 일정",
                    testDate,
                    "09:00:00",
                    "10:00:00"
            );
            System.out.println("Update success? " + updated);
        }

        // 4) 조회 테스트 (수정 후)
        System.out.println("\n=== SELECT TEST (after update) ===");
        list = dao.getSchedulesByDate(testDate);
        for (Schedule s : list) {
            System.out.printf("id=%d, title=%s, from %s to %s\n",
                    s.getId(), s.getTitle(), s.getStartTime(), s.getEndTime());
        }

        // 5) 삭제 테스트
        if (!list.isEmpty()) {
            int idToDelete = list.get(0).getId();
            System.out.println("\n=== DELETE TEST ===");
            boolean deleted = dao.deleteSchedule(idToDelete);
            System.out.println("Delete success? " + deleted);
        }

        // 6) 조회 테스트 (삭제 후)
        System.out.println("\n=== SELECT TEST (after delete) ===");
        list = dao.getSchedulesByDate(testDate);
        System.out.println("Remaining count: " + list.size());
    }
}

