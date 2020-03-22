package dao;

import Entity.Client;
import configuration.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class ClientDAO {
    public static void saveClient(Client client) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            client.getCompanies().stream().forEach(company -> company.getClients().add(client));
            session.save(client);
            transaction.commit();
        }
    }

    public static void saveOrUpdateClient(Client client) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            client.getCompanies().stream().forEach(company -> company.getClients().add(client));
            session.saveOrUpdate(client);
            transaction.commit();
        }
    }

    public static void saveClients(Set<Client> clientList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            clientList.stream().forEach(
                    client -> client
                            .getCompanies()
                            .stream()
                            .forEach(company -> company.setClients(clientList)));
            clientList.stream().forEach((client) -> session.save(client));
            transaction.commit();
        }
    }

    public static List<Client> getClients() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Client", Client.class).list();
        }
    }

    public static Client getClientById(long id) {
        Transaction transaction = null;
        Client client;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            client = session.get(Client.class, id);
            transaction.commit();
        }
        return client;
    }

    public static void deleteClient(Client client) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            client.getCompanies().stream().forEach(company -> company.getClients().remove(client));
            session.delete(client);
            transaction.commit();
        }
    }
}
