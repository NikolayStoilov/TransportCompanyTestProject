import Entity.Client;
import Entity.Company;
import Entity.Drivers;
import Entity.Truck;
import dao.ClientDAO;
import dao.CompanyDAO;
import dao.DriversDAO;
import dao.TruckDAO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Company testcompany = new Company();
        testcompany.setId(1);
        testcompany.setName("DHL");
        testcompany.setFoundationdate(LocalDate.of(2000, 10, 23));
        testcompany.setInitialCapital(BigDecimal.valueOf(10_000));

        Company testCompany2 = new Company("Microsoft",LocalDate.of(1998, 10, 23),BigDecimal.valueOf(1000));
        CompanyDAO.saveCompany(testcompany);
        CompanyDAO.saveCompany(testCompany2);

        List<Company> companyList = Arrays.asList(new Company("Speedy",LocalDate.of(2015,4,23),BigDecimal.valueOf(2500)),
                new Company("TestCompany",LocalDate.of(2002,2,12),BigDecimal.valueOf(60000)));
        // save list of companies
        CompanyDAO.saveCompanies(companyList);

        // save or update company
        Company company1 = CompanyDAO.getCompany(2);
        company1.setName("Updated name");
        CompanyDAO.saveOrUpdateCompany(company1);

        // get all companies
        CompanyDAO.getCompanies().stream().forEach(System.out::println);

        // delete company
        CompanyDAO.deleteCompany(testcompany);

        // save driver
        Drivers driver1 = new Drivers("Ivan", LocalDate.of(1999,3,19));
        driver1.setCompany(testCompany2);
        DriversDAO.saveDriver(driver1);

        // save driver
        Drivers driver2 = new Drivers("Gosho", LocalDate.of(1995,3,19));
        driver2.setCompany(testCompany2);
        DriversDAO.saveDriver(driver2);

        // save Trucks
        Truck truck1 = new Truck(1254);
        truck1.setDriver(driver1);
        TruckDAO.saveTruck(truck1);

        Truck truck2 = new Truck(4554);
        truck2.setDriver(driver2);
        TruckDAO.saveTruck(truck2);

        //save Client
        Client client1 = new Client("John");
        client1.getCompanies().add(testCompany2);
        ClientDAO.saveClient(client1);

        Client client2 = new Client("Peter");
        client1.getCompanies().add(testCompany2);
        ClientDAO.saveClient(client1);

        //Get all clients
        System.out.println(ClientDAO.getClients());

    }
}
