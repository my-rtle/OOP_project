package model;

public class Schedule {
    private int id;               // DB AUTO_INCREMENT
    private String title;
    private String description;   // schedule.description :contentReference[oaicite:0]{index=0}:contentReference[oaicite:1]{index=1}
    private String date;          // YYYY-MM-DD
    private String startTime;     // HH:MM:SS
    private String endTime;       // HH:MM:SS

    // (1) 디폴트 생성자
    public Schedule() {}

    // (2) insert용 생성자
    public Schedule(String title, String date, String startTime) {
        this.title = title;
        this.date = date;
        this.startTime = startTime;
    }

    // (3) select 결과용 생성자
    public Schedule(int id, String title, String description,
                    String date, String startTime, String endTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // — 이하 getter / setter —
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
