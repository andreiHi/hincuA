package ru.job4j.tracker.start;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.tracker.action.MenuTracker;
import ru.job4j.tracker.connection.ConnectionSQL;
import ru.job4j.tracker.input.ConsoleInput;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.ValidateInput;
import ru.job4j.tracker.storage.TrackerDb;

/**
 *StartUi .
 * @author Hincu Andrei (andreih1981@gmail.com) by 05.09.17;
 * @version $Id$
 * @since 0.1
 */
public class StartUi {
    private static final Logger LOG = LogManager.getLogger(StartUi.class);
    /**
     * Input.
     */
    private Input input;
    /**
     * TrackerDb.
     */
    private TrackerDb trackerDb;
    private boolean exit = true;

    /**
     * Конструктор класса.
     * @param input взаимодействие с пользователем.
     * @param trackerDb трекер.
     */
    public StartUi(Input input, TrackerDb trackerDb) {
        this.input = input;
        this.trackerDb = trackerDb;
    }

    /**
     * Start.
     * @param args отсутствует.
     */
    public static void main(String[] args) {
        try (TrackerDb trackerDb = new TrackerDb(ConnectionSQL.getInstance().getConnection())) {
            new StartUi(new ValidateInput(new ConsoleInput()), trackerDb).init();
        } catch (Exception e) {
            LOG.trace(e.getMessage(), e);
        }
    }

    /**
     * Главный метод программы.
     */
    public void init() {
        MenuTracker menuTracker = new MenuTracker(this.input, this.trackerDb, this);
        menuTracker.fillActions();
        int[] rang = menuTracker.rangs();
        int key;
        do {
            menuTracker.show(System.out :: println);
            key = input.ask("Select:", rang);
            menuTracker.select(key);
        } while (exit);
    }
    public void exit() {
        this.exit = false;
    }
}
