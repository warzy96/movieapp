package fiveagency.internship.food.domain.model;

import java.util.LinkedList;
import java.util.List;

public class Beer {

    public static final Beer EMPTY = new Beer(0, "", "", "", "",
            "", 0.0, new LinkedList<String>(), "");
    private final int id;
    private final String name;
    private final String tagLine;
    private final String firstBrewed;
    private final String description;
    private final String imageUrl;
    private final double alcoholByVolume;
    private final List<String> foodPairing;
    private final String brewersTips;

    public Beer(int id, String name, String tagLine, String firstBrewed, String description,
                String imageUrl, double alcoholByVolume, List<String> foodPairing, String brewersTips) {
        this.id = id;
        this.name = name;
        this.tagLine = tagLine;
        this.firstBrewed = firstBrewed;
        this.description = description;
        this.imageUrl = imageUrl;
        this.alcoholByVolume = alcoholByVolume;
        this.foodPairing = foodPairing;
        this.brewersTips = brewersTips;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTagline() {
        return tagLine;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getAlcoholByVolume() {
        return alcoholByVolume;
    }

    public List<String> getFoodPairing() {
        return foodPairing;
    }

    public String getBrewersTips() {
        return brewersTips;
    }
}
