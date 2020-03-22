package dao;

import Entity.Drivers;
import configuration.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DriversDAO {
    public static void saveDriver(Drivers driver) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            System.out.println(driver.getCompany());
            driver.getCompany().addDriver(driver);
            session.save(driver);
            transaction.commit();
        }
    }

    public static void saveOrUpdateDriver(Drivers driver) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            driver.getCompany().addDriver(driver);
            session.saveOrUpdate(driver);
            transaction.commit();
        }
    }

    public static void saveDrivers(List<Drivers> driversList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            driversList.stream().forEach(driver -> driver.getCompany().addDriver(driver));
            driversList.stream().forEach(driver -> session.save(driver));
            transaction.commit();
        }
    }
    public static List<Drivers> getDrivers() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Drivers", Drivers.class).list();
        }
    }

    public static Drivers getDriver(long id) {
        Transaction transaction = null;
        Drivers driver;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            driver = session.get(Drivers.class, id);
            transaction.commit();
        }
        return driver;
    }


    public static void deleteDriver(Drivers driver) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            driver.getCompany().removeDriver(driver);
            session.delete(driver);
            transaction.commit();
        }
    }


}
