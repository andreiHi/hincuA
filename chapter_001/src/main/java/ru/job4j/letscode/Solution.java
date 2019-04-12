package ru.job4j.letscode;

import java.util.StringJoiner;

/**
 * likes {} // must be "no one likes this"
 * likes {"Peter"} // must be "Peter likes this"
 *
 * likes {"Jacob", "Alex"} // must be "Jacob and Alex like this"
 *
 * likes {"Max", "John", "Mark"} // must be           "Max, John and Mark like this"
 * likes {"Alex", "Jacob", "Mark", "Max"} // must be "Alex, Jacob and 2 others like this"
 * @author Hincu Andrei (andreih1981@gmail.com)on 28.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class Solution {
    public static String whoLikesIt(String... names) {
        //Do your magic here
        String suffixS = " likes this";
        String suffixP = " like this";
        String and = " and ";
        String others = " others";
        String result = "";
        if (names.length == 0) {
            result = new StringJoiner("", "", suffixS).add("no one").toString();
        } else if (names.length == 1) {
            result = new StringJoiner("", "", suffixS).add(names[0]).toString();
        } else if (names.length == 2) {
            result = new StringJoiner(and, "", suffixP).add(names[0]).add(names[1]).toString();
        } else if (names.length == 3) {
            result = String.format("%s, %s%s%s%s", names[0], names[1], and, names[2], suffixP);
        } else {
            result = String.format("%s, %s%s%d%s%s", names[0], names[1], and, (names.length - 2), others, suffixP);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new StringJoiner(" ", "", "")
                .add("hello")
                .add("world")
        );
    }
}
