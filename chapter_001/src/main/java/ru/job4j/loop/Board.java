package ru.job4j.loop;
/**
 * Board.
 * @author Hincu Andrei (andreih1981@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class Board {
    /**
     * Метод возвращает доску заданной высоты и ширины.
     * @param width -высота доски.
     * @param height - ширина доски.
     * @return String paint.
     */
    public String paint(int width, int height) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (stringBuilder.length() == 0) {
                    stringBuilder.append("x");
                    continue;
                }
                if (stringBuilder.toString().endsWith(" ")) {
                    stringBuilder.append("x");
                } else if (stringBuilder.toString().endsWith("x")) {
                    stringBuilder.append(" ");
                } else {
                        char ch = stringBuilder.charAt(stringBuilder.length() - 2);
                    if (ch == 'x') {
                        stringBuilder.append(" ");
                    } else {
                        stringBuilder.append("x");
                    }
                }
            }
            stringBuilder.append(System.getProperty("line.separator"));
        }
        return stringBuilder.toString();
    }
}
