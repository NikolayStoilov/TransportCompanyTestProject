package Entity;

import javax.persistence.*;

@Entity
@Table(name = "truck")
public class Truck {
    @Id
    @Column(name = "id",nullable = false,updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "reg_number",nullable = false)
    private int registrationNumber;

    @OneToOne
    @JoinColumn(name = "driver_id",unique = false,nullable = false)
    private Drivers driver;

    public Truck(){}
    public Truck (int registrationNumber){
        this.registrationNumber = registrationNumber;
    }
//https://www.java2novice.com/hibernate/bidirectional-onetoone-annotation/
    @Override
    public String toString() {
        return "Truck{" +
                "id=" + id +
                ", registrationNumber=" + registrationNumber +
                ", driver=" + driver +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Drivers getDriver() {
        return driver;
    }

    public void setDriver(Drivers driver) {
        this.driver = driver;
    }
}
