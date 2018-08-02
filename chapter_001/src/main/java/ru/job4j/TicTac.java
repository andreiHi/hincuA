package ru.job4j;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 03.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class TicTac {

    private String trimGmail(String email) {
        String part = email.split("@")[0];
        return part.replaceAll("\\.", "");
    }

    public static void main(String[] args) {
        String s = "Вася";
        char[] c = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c.length - 1; i++) {
            sb.append(c[i]).append('.');
        }
        sb.append(c[c.length - 1]);
        System.out.println(sb);
        String sF = sb.toString();
        StringBuilder sL = new StringBuilder(sb);
        String s1;
        StringBuilder sM = new StringBuilder(sb);
        String s2;
        for (int i =0; i < sb.length()/2 - 1; i++) {

            sF = sF.replaceFirst("\\.", "");
            System.out.println(sF);

            s1 = sL.reverse().toString().replaceFirst("\\.", "");
            sL = new StringBuilder(s1).reverse();
            System.out.println(sL);

            s2 = sM.toString().replaceFirst("\\.", "");
            System.out.println(s2);
            s2 = new StringBuilder(s2).reverse().toString().replaceFirst("\\.", "");
            sM = new StringBuilder(s2).reverse();
            System.out.println(sM);
        }



    }
}
