package ru.job4j.quoters;

import ru.job4j.quoters.contexlisiner.PostProxy;
import ru.job4j.quoters.deprecated.DeprecatedClass;
import ru.job4j.quoters.deprecated.T1000;
import ru.job4j.quoters.inject.InjectRandomInt;
import ru.job4j.quoters.profiling.Profiling;

import javax.annotation.PostConstruct;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 01.08.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Profiling
@DeprecatedClass(newImpl = T1000.class)
public class TerminatorQuoter implements Quoter {

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

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
    @PostProxy
    public void sayQuote() {
        System.out.println("3 phase");
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
