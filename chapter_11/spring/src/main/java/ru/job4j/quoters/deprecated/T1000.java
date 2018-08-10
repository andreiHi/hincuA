package ru.job4j.quoters.deprecated;

import ru.job4j.quoters.Quoter;
import ru.job4j.quoters.TerminatorQuoter;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 03.08.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class T1000 extends TerminatorQuoter implements Quoter {
    @Override
    public void sayQuote() {
        System.out.println("Я жидкий.");
    }

}
