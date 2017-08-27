package ru.job4j.max;
/**
 * Max.
 * @author Hincu Andrei (andreih1981@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Max {
    /**
     * Method max максимальное из двух значений.
     * @param first первое значение.
     * @param second второе значение.
     *@return max значение;
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }
}
