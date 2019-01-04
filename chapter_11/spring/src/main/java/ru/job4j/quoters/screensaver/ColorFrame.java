package ru.job4j.quoters.screensaver;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 08.08.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Component
public abstract  class ColorFrame extends JFrame {
    private static final Logger LOG = LogManager.getLogger(ColorFrame.class);


    public ColorFrame()  {
        setSize(200, 200);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void showOnRandomPlace() {
        Random random = new Random();
        setLocation(random.nextInt(1200), random.nextInt(700));
        getContentPane().setBackground(getColor());
        repaint();
    }

   protected abstract Color getColor();
}
