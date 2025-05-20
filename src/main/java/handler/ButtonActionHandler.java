package handler;

import service.ScheduleService;
import javax.swing.JButton;


public class ButtonActionHandler {

    private final ScheduleService scheduleService = new ScheduleService();

    // 일정 추가 버튼 이벤트 바인드
    public void bindAddButton(JButton addButton, InputProvider inputProvider) {
        addButton.addActionListener(e -> {
            String title = inputProvider.getTitle();
            String date = inputProvider.getDate();
            String startTime = inputProvider.getStartTime();
            scheduleService.addSchedule(title, date, startTime);
        });
    }

    // 일정 삭제 버튼 이벤트 바인드
    public void bindDeleteButton(JButton deleteButton, IdProvider idProvider) {
        deleteButton.addActionListener(e -> {
            int id = idProvider.getId();
            scheduleService.deleteSchedule(id);
        });
    }
    // 일정 수정 버튼 이벤트 바인드
    public void bindUpdateButton(JButton updateButton, UpdateProvider updateProvider) {
    	updateButton.addActionListener(e -> {
            int id = updateProvider.getId();
            String title = updateProvider.getTitle();
            String date = updateProvider.getDate();
            String startTime = updateProvider.getStartTime();
            String endTime = updateProvider.getEndTime();
            scheduleService.updateSchedule(id, title, date, startTime, endTime);
        });
    }
    // 날짜별 일정 조회 이벤트 바인드
    public void bindFindButton(JButton findButton, InputProvider inputProvider) {
        findButton.addActionListener(e -> {
            String date = inputProvider.getDate();
            scheduleService.getSchedulesByDate(date);
        });
    }
}