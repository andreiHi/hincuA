package ru.job4j.tracker.action;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.models.Comment;
import ru.job4j.tracker.storage.TrackerDb;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 09.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class MenuComments {

    private TrackerDb trackerDb;
    private Input input;
    private ArrayList<UserAction> list = new ArrayList<>();
    private String id;
    private int[] rang;

    public void init() {
        list.add(new ShowAllComments("Show all comments from this item.", 0));
        list.add(new AddNewComment("Add new comment.", 1));
        list.add(new DeleteComment("Delete comment.", 2));
        list.add(new ExitFromComments("Exit from this submenu.", 3));
        this.rang = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            rang[i] = list.get(i).key();
        }
        select();
    }
    private void select() {
        int k;
        do {
            showAllActions();
            k = input.ask("Select:", rang);
            execute(k);
        } while (k != rang[3]);
    }
   public void execute(int k) {
        list.get(k).execute(trackerDb, input);
   }
    private void showAllActions() {
        list.forEach(list-> System.out.println(list.info()));
    }

    public MenuComments(TrackerDb trackerDb, Input input, String id) {
        this.trackerDb = trackerDb;
        this.input = input;
        this.id = id;
        init();
    }

    class  ShowAllComments extends BaseAction {

        /**
         * Конструктор.
         * @param name имя подменю.
         * @param key  номер подменю.
         */
        public ShowAllComments(String name, int key) {
            super(name, key);
        }


        @Override
        public void execute(TrackerDb trackerDb, Input input) {
            List<Comment> comments = trackerDb.getAllComments(id);
            if (comments != null) {
                comments.forEach(System.out::println);
            } else {
                input.writeMessage("This item has no comments.");
            }
        }
    }
    class AddNewComment extends BaseAction {
        /**
         * Конструктор.
         *
         * @param name имя подменю.
         * @param key  номер подменю.
         */
        public AddNewComment(String name, int key) {
            super(name, key);
        }

        @Override
        public void execute(TrackerDb trackerDb, Input input) {
            String text = input.ask("Write comment:");
            if (trackerDb.addNewComment(id, text) > 0) {
                input.writeMessage("Comment was added successfully.");
            } else {
                input.writeMessage("Something wrong.");
            }
        }
    }
    class DeleteComment extends BaseAction {
        /**
         * Конструктор.
         * @param name имя подменю.
         * @param key  номер подменю.
         */
        public DeleteComment(String name, int key) {
            super(name, key);
        }

        @Override
        public void execute(TrackerDb trackerDb, Input input) {
            String text = input.ask("Write id of comment, or All to remove all comments from this item :");
            if (trackerDb.deleteComment(id, text) > 0) {
                input.writeMessage("Deletion was successful.");
            } else {
                input.writeMessage("Something wrong.");
            }
        }
    }

    class ExitFromComments extends BaseAction {

        /**
         * Конструктор.
         *
         * @param name имя подменю.
         * @param key  номер подменю.
         */
        public ExitFromComments(String name, int key) {
            super(name, key);
        }

        @Override
        public void execute(TrackerDb trackerDb, Input input) {
            input.writeMessage("Exit from comments for this item.");
        }
    }
}
