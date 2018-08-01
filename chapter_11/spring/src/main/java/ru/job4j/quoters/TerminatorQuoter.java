package ru.job4j.quoters;

import javax.annotation.PostConstruct;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 01.08.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Profiling
public class TerminatorQuoter implements Quoter {

    @InjectRandomInt(min = 2, max = 7)
    private int repeat;

    private String message;

    public TerminatorQuoter() {
        System.out.println("Phase 1");
    }

    @PostConstruct
    public void init() {
        System.out.println("Phase 2");
        System.out.println("repeat = " + this.repeat);
    }
    @Override
    public void sayQuote() {
        for (int i = 0; i < repeat; i++) {
            System.out.println("message = " + message);
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
