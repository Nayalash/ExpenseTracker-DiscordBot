package data;

public final class Expense {

    private String name;
    private String description;
    private float price;

    public Expense(String name, float price, String description) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }



}
