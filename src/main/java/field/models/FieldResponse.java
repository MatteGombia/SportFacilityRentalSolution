package field.models;

public class FieldResponse extends FieldRequest {

    private Long id;

    public FieldResponse() { this(null, null, 0, 0, null, null); }

    public FieldResponse(Long id, String name, double price, int maxCapacity, String location, String description) {
        super(name, price, maxCapacity, location,description);
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
