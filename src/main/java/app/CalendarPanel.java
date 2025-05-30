package app;

import com.calendarfx.model.*;
import com.calendarfx.view.CalendarView;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.MouseEvent;
import service.ScheduleService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CalendarPanel extends VBox {

    private final CalendarView calendarView;
    private final ScheduleService scheduleService = new ScheduleService();
    private final Calendar defaultCal = new Calendar("My Schedule");

    public CalendarPanel() {
        Locale.setDefault(Locale.ENGLISH);

        calendarView = new CalendarView();
        calendarView.setEnableTimeZoneSupport(true);

        defaultCal.setShortName("My");
        defaultCal.setStyle(Calendar.Style.STYLE1);

        // 示例事件
        Entry<String> entry = new Entry<>("📌 Project Presentation");
        entry.changeStartDate(LocalDate.now());
        entry.changeStartTime(LocalTime.of(14, 0));
        entry.changeEndTime(LocalTime.of(16, 0));
        defaultCal.addEntry(entry);

        CalendarSource calendarSource = new CalendarSource("My Calendars");
        calendarSource.getCalendars().add(defaultCal);
        calendarView.getCalendarSources().add(calendarSource);

        calendarView.showDayPage();
        calendarView.setRequestedTime(LocalTime.now());

        calendarView.setShowAddCalendarButton(true);
        calendarView.setShowPrintButton(false);
        calendarView.setShowSearchField(true);
        calendarView.setShowPageSwitcher(true);
        calendarView.setShowSourceTray(false);
        calendarView.setShowToolBar(true);

        // 添加时间刷新线程
        Thread updateTimeThread = new Thread(() -> {
            while (true) {
                Platform.runLater(() -> {
                    calendarView.setToday(LocalDate.now());
                    calendarView.setTime(LocalTime.now());
                });
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();

        // 👉 添加按钮栏
        HBox buttonBox = createButtonBar();

        // 👉 整合布局（按钮在上，日历在下）
        this.getChildren().addAll(buttonBox, calendarView);
        this.setSpacing(10);
    }

    private HBox createButtonBar() {
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        Button updateButton = new Button("Update");
        Button searchByDateButton = new Button("Search by Date");
        Button searchByTitleButton = new Button("Search by Title");

        addButton.setOnAction(e -> {
            Entry<String> newEntry = new Entry<>();
            newEntry.changeStartDate(LocalDate.now());
            newEntry.changeStartTime(LocalTime.of(10, 0));
            newEntry.changeEndTime(LocalTime.of(11, 0));

            // Pop up dialog to get user-entered task title
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add New Task");
            dialog.setHeaderText("Please enter the task title:");
            dialog.setContentText("Title:");


            dialog.showAndWait().ifPresent(title -> {
                newEntry.setTitle(title);
                defaultCal.addEntry(newEntry);

                // ⬇️ 保存到数据库时使用用户填写的标题
                scheduleService.addSchedule(
                        title,
                        LocalDate.now().toString(),
                        "10:00"
                );
            });
        });


        deleteButton.setOnAction(e -> {
            List<Entry<?>> entries = getAllEntries();
            if (!entries.isEmpty()) {
                Entry<?> last = entries.get(entries.size() - 1);
                defaultCal.removeEntry(last);
                scheduleService.deleteSchedule(last.hashCode()); // 示例：以 hashCode 模拟 ID
            }
        });

        updateButton.setOnAction(e -> {
            List<Entry<?>> entries = getAllEntries();
            if (!entries.isEmpty()) {
                Entry<?> entry = entries.get(0);
//                entry.setTitle("🛠 Updated Task");
                scheduleService.updateSchedule(entry.hashCode(), "Updated Task", LocalDate.now().toString(), "12:00", "13:00");
            }
        });

        searchByDateButton.setOnAction(e -> {
            scheduleService.getSchedulesByDate(LocalDate.now().toString());
        });

        searchByTitleButton.setOnAction(e -> {
            scheduleService.getSchedulesByTitle("Task");
        });

        HBox box = new HBox(10, addButton, deleteButton, updateButton, searchByDateButton, searchByTitleButton);
        return box;
    }

    public List<Entry<?>> getAllEntries() {
        return calendarView.getCalendarSources().stream()
                .flatMap(source -> source.getCalendars().stream())
                .flatMap(calendar -> ((List<Entry<?>>) (List<?>) calendar.findEntries("")).stream())
                .collect(Collectors.toList());
    }

    public CalendarView getCalendarView() {
        return calendarView;
    }
}

