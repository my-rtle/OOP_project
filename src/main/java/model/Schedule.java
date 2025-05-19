package model;

public class Schedule {
    private int id;               // DB AUTO_INCREMENT
    private String title;
    private String date;          // YYYY-MM-DD
    private String startTime;     // HH:MM:SS

    /* 생성자 (insert용) */
    public Schedule(String title, String date, String startTime) {
        this.title = title;
        this.date = date;
        this.startTime = startTime;
    }

    /* 생성자 (select 결과용) */
    public Schedule(int id, String title, String date, String startTime) {
        this(title, date, startTime);
        this.id = id;
    }

    /* getter / setter … */
}
