package Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @Column(name = "id",updatable = false,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "foundation_date")
    private LocalDate foundationdate;

    @Column(name = "initial_capital",nullable = false)
    private BigDecimal initialCapital;

     @OneToMany(mappedBy = "company")
    private List<Drivers> driversList;

     @ManyToMany(mappedBy = "companies")
    Set<Client>clients = new HashSet<Client>();

     public Company(){}

    public Company(String name, LocalDate foundationdate, BigDecimal initialCapital) {
        this.name = name;
        this.foundationdate = foundationdate;
        this.initialCapital = initialCapital;
        this.driversList = new LinkedList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getFoundationdate() {
        return foundationdate;
    }

    public void setFoundationdate(LocalDate foundationdate) {
        this.foundationdate = foundationdate;
    }

    public BigDecimal getInitialCapital() {
        return initialCapital;
    }

    public void setInitialCapital(BigDecimal initialCapital) {
        this.initialCapital = initialCapital;
    }

    public List<Drivers> getDriversList() {
        return driversList;
    }

    public void setDriversList(List<Drivers> driversList) {
        this.driversList = driversList;
    }
public void addDriver(Drivers driver){
         this.driversList.add(driver);
}
public void removeDriver(Drivers driver){
         this.driversList.remove(driver);
}

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", foundationdate=" + foundationdate +
                ", initialCapital=" + initialCapital +
                '}';
    }
}
