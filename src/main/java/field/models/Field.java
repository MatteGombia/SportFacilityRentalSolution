package field.models;

public class Field {
    private Long id;
    private String name;
    private double price;
    private double maintenance;
    private int maxCapacity;
    private String location;
    private String description;

    public Field() { this(null, null, 0, 0, 0, null, null); }

    public Field(String name, double price, double maintenance, int maxCapacity, String location, String description) {
        this(null, name, price, maintenance, maxCapacity, location, description);
    }

    public Field(Long id, String name, double price, double maintenance, int maxCapacity, String location, String description) {
        this.setId(id);
        this.name = name;
        this.price = price;
        this.maintenance = maintenance;
        this.maxCapacity =maxCapacity;
        this.location = location;
        this.description = description;
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

