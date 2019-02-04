package ru.job4j.io.manager;

import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 27.01.2019.
 * @version $Id$.
 * @since 0.1.
 */
public interface Manager {
    List<String> getRoot();
    String getAtRoot(String root);
    String getParent();
    String downloadFile(String fileName, String path);
    String uploadFile(String fileName, String path);
}
