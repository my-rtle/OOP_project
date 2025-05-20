package service;

import db.ScheduleDAO;
import model.Schedule;
import java.util.List;


public class ScheduleService {
	private final ScheduleDAO scheduleDAO = new ScheduleDAO();
	
	public void addSchedule(String title, String date, String startTime) {
		scheduleDAO.insertSchedule(title, date, startTime);
	}
	public boolean deleteSchedule(int id) {
		return scheduleDAO.deleteSchedule(id);
	}
	public boolean updateSchedule(int id, String title, String date, String startTime, String endTime) {
		return scheduleDAO.updateSchedule(id, title, date, startTime, endTime);
	}
	public List<Schedule> getSchedulesByDate(String date) {
		return scheduleDAO.getSchedulesByDate(date);
	}
}
