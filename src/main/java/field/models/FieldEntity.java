package field.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FieldEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double price;
    private double maintenance;
    private int maxCapacity;
    private String location;
    private String description;

    public FieldEntity() {}

    public FieldEntity(Long id, String name, double price, double maintenance, int maxCapacity, String location, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.maintenance = maintenance;
        this.maxCapacity = maxCapacity;
        this.location = location;
        this.description =description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getMaintenance() { return maintenance; }
    public void setMaintenance(double maintenance) { this.maintenance = maintenance; }
    public int getMaxCapacity() {
        return maxCapacity;
    }
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
