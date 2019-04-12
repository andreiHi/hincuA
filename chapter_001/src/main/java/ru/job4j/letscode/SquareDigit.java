package ru.job4j.letscode;


/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 29.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class SquareDigit {

    public int squareDigits(int n) {
        String strDigits = String.valueOf(n);
        StringBuilder result = new StringBuilder();

        for (char c : strDigits.toCharArray()) {
            int digit = Character.digit(c, 10);
            result.append(digit * digit);
        }

        return Integer.parseInt(result.toString());
    }

}
