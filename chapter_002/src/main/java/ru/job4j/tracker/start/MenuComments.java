package ru.job4j.tracker.start;

import ru.job4j.tracker.action.BaseAction;
import ru.job4j.tracker.action.UserAction;
import ru.job4j.tracker.input.Input;

import java.util.ArrayList;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 09.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class MenuComments {
    Tracker tracker;
    Input input;
    ArrayList<UserAction> list = new ArrayList<>();
    public void init() {
        list.add(new ShowAllComments("Show all comments from this item.", 0));
    }

    public MenuComments(Tracker tracker, Input input) {
        this.tracker = tracker;
        this.input = input;
    }

    class  ShowAllComments extends BaseAction{

        /**
         * Конструктор.
         * @param name имя подменю.
         * @param key  номер подменю.
         */
        public ShowAllComments(String name, int key) {
            super(name, key);
        }


        @Override
        public void execute(Tracker tracker, Input input) {

        }
    }
}
