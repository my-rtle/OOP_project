package app;

import com.calendarfx.model.*;
import com.calendarfx.view.CalendarView;
import fr.brouillard.oss.cssfx.CSSFX;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

public class CalendarFXMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 언어를 영어로 설정 (CalendarFX의 기본 형식)
        Locale.setDefault(Locale.ENGLISH);

        // CalendarFX의 메인 뷰 생성
        CalendarView calendarView = new CalendarView();
        calendarView.setEnableTimeZoneSupport(true);

        // 기본 캘린더를 생성하고 이벤트 추가
        Calendar defaultCal = new Calendar("My Schedule");
        defaultCal.setShortName("My");
        defaultCal.setStyle(Calendar.Style.STYLE1);

        // 테스트 이벤트 추가
        Entry<String> entry = new Entry<>("📌 Project Presentation");
        entry.changeStartDate(LocalDate.now());
        entry.changeStartTime(LocalTime.of(14, 0));
        entry.changeEndTime(LocalTime.of(16, 0));
        defaultCal.addEntry(entry);

        // 캘린더를 소스에 추가
        CalendarSource calendarSource = new CalendarSource("My Calendars");
        calendarSource.getCalendars().add(defaultCal);
        calendarView.getCalendarSources().add(calendarSource);

        // 초기 뷰를 주간 보기로 설정하고 시간 설정
        calendarView.showWeekPage();
        calendarView.setRequestedTime(LocalTime.now());

        // UI 제어 버튼 (툴바 설정)
        calendarView.setShowAddCalendarButton(true);
        calendarView.setShowPrintButton(false);
        calendarView.setShowSearchField(true);
        calendarView.setShowPageSwitcher(true);
        calendarView.setShowSourceTray(true);
        calendarView.setShowToolBar(true);

        // 현재 시간을 10초마다 갱신하는 스레드
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

        // 화면 구성
        StackPane root = new StackPane(calendarView);
        Scene scene = new Scene(root, 1300, 900);
        CSSFX.start(scene);

        primaryStage.setTitle("📅 Schedule Manager - CalendarFX");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
