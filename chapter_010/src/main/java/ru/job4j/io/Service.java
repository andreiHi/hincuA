package ru.job4j.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 12.02.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Service {

    public boolean isNumber(InputStream is) {
        boolean even = false;
        try {
            byte[] buffer = new byte[is.available()];
            is.read(buffer, 0, is.available());
            Integer integer = new Integer(new String(buffer));
            if (integer % 2 == 0) {
                even = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try  {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return even;
    }
}
