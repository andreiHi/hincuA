package ru.job4j.model.car;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.model.Persistent;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 09.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Entity
@Table(name = "image")
public class Image extends Persistent {
    private static final Logger LOG = LogManager.getLogger(Image.class);


}
