package ru.job4j.dao;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.model.Persistent;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public abstract class AbstractController<E extends Persistent, K> implements Dao<E, K> {
//    private static final Logger LOG = LogManager.getLogger(AbstractController.class);
//    private SessionFactory sessionFactory = HibernateService.getSessionFactory();
//    private Session currentSession;
//    private Transaction currentTransaction;
//
//    public Session getCurrentSession() {
//        return currentSession;
//    }
//
//    public Session openCurrentSession() {
//        this.currentSession = sessionFactory.openSession();
//        return currentSession;
//    }
//    public void closeCurrentSession() {
//        this.currentSession.close();
//    }
//    public Session openCurrentSessionWithTransaction() {
//        this.currentSession = sessionFactory.openSession();
//        this.currentTransaction = currentSession.beginTransaction();
//        return currentSession;
//    }
//    public void closeSessionWithTransaction() {
//        this.currentTransaction.commit();
//        this.currentSession.close();
//    }
//    public void addTransactionToCurrentSession() {
//        this.currentTransaction = currentSession.beginTransaction();
//    }

}
