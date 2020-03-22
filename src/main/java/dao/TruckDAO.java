package dao;

import Entity.Truck;
import configuration.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TruckDAO {

    public static void saveTruck(Truck truck) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            truck.getDriver().setTruck(truck);
            session.save(truck);
            transaction.commit();
        }
    }

    public static void saveOrUpdateTruck(Truck truck) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            truck.getDriver().setTruck(truck);
            session.saveOrUpdate(truck);
            transaction.commit();
        }
    }

    public static void saveTrucks(List<Truck> truckList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            truckList.stream().forEach((truck) ->truck.getDriver().setTruck(truck));
            truckList.stream().forEach((truck) -> session.save(truck));
            transaction.commit();
        }
    }

    public static List<Truck> getTrucks() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Truck", Truck.class).list();
        }
    }

    public static Truck getTruckById(long id) {
        Transaction transaction = null;
        Truck truck;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            truck = session.get(Truck.class, id);
            transaction.commit();
        }
        return truck;
    }



    public static void deleteTruck(Truck truck) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            truck.getDriver().setTruck(null);
            session.delete(truck);
            transaction.commit();
        }
    }
}
