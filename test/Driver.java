public class Driver {
    private int id;
    private String name;
    private boolean available;

    public Driver(int id, String name, boolean available) {
        this.id = id;
        this.name = name;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + (available ? "Available" : "Unavailable");
    }
}