package ru.job4j.configuration;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 07.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class StartingData {
    private static final Logger LOG = LogManager.getLogger(StartingData.class);
   // private SessionFactory sessionFactory = HibernateService.getSessionFactory();
    private static final String BRANDS = "brand.txt";
    private static final String FOLDER = "models/";


//    /**
//     * Create a session and transaction.
//     * @param function input function.
//     */
//    private void transaction(final Consumer<Session> function) {
//        final Session session = this.sessionFactory.openSession();
//        final Transaction trs = session.beginTransaction();
//        try {
//            function.accept(session);
//        } catch (HibernateError e) {
//            LOG.error(e.getMessage(), e);
//        } finally {
//            trs.commit();
//            session.close();
//        }
//    }
//
//    /**
//     * Filling tables with initial data.
//     */
    public void initTables() {
    }
//        transaction(session -> {
//            int count = ((Long) session.createQuery("select count (*) from Brand ").uniqueResult()).intValue();
//            if (count == 0) {
//                readFile(BRANDS).forEach(b -> {
//                    Brand brand = new Brand(b);
//                    long id = (long) session.save(brand);
//                    brand.setId(id);
//                    readFile(FOLDER + b).forEach(m -> {
//                        Model model = new Model(m, brand);
//                        session.save(model);
//                    });
//                });
//            }
//        });
//    }

    /**
     * Read initial data.
     * @param file path to file.
     * @return list with names of brands(models).
     */
    private List<String> readFile(String file)  {
        List<String> list = new ArrayList<>();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource(file);
        if (url != null) {
            try (Stream<String> stream =
                         Files.lines(Paths.get(url.toURI()))) {
                list = stream.collect(Collectors.toList());
            } catch (URISyntaxException | IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return list;
    }

}
