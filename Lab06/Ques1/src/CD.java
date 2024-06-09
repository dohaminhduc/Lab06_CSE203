import java.io.Serializable;
import java.util.*;

public class CD implements Serializable {
    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public CD(String title, String type, String id, double price, int yearOfRelease, String collection) {
        this.title = title;
        this.type = type;
        this.id = id;
        this.price = price;
        this.yearOfRelease = yearOfRelease;
        this.collection = collection;
    }


    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    private String collection;
    private String title;
    private String type;
    private String id;
    private double price;
    private int yearOfRelease;



}
