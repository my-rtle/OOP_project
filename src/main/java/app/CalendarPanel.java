package app;

import com.calendarfx.model.*;
import com.calendarfx.view.CalendarView;

import javafx.application.Platform;
import javafx.scene.layout.StackPane;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * 캡슐화된 일정 패널 컴포넌트로, 백엔드에서 new CalendarPanel로 바로 사용할 수 있습니다.
 */

public class CalendarPanel extends StackPane {

    private final CalendarView calendarView;

    public CalendarPanel() {
        Locale.setDefault(Locale.ENGLISH);

        calendarView = new CalendarView();
        calendarView.setEnableTimeZoneSupport(true);

        // 기본 캘린더 생성
        Calendar defaultCal = new Calendar("My Schedule");
        defaultCal.setShortName("My");
        defaultCal.setStyle(Calendar.Style.STYLE1);

        // 테스트 이벤트 추가
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

        // 현재 시간 갱신 스레드
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


        this.getChildren().add(calendarView);
    }

    public CalendarView getCalendarView() {
        return calendarView;
    }


    public List<Entry<?>> getAllEntries() {
        return calendarView.getCalendarSources().stream()
                .flatMap(source -> source.getCalendars().stream())
                .flatMap(calendar -> ((List<Entry<?>>) (List<?>) calendar.findEntries("")).stream())
                .collect(Collectors.toList());
    }
}
