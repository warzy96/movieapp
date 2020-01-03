package fiveagency.internship.food.movieapp.ui.moviedetails.beer;

import java.util.List;

import fiveagency.internship.food.domain.model.Beer;

public final class BeerViewModel {

    private final int id;
    private final String name;
    private final String tagLine;
    private final String firstBrewed;
    private final String description;
    private final String imageUrl;
    private final double alcoholByVolume;
    private final List<String> foodPairing;
    private final String brewersTips;

    public BeerViewModel(int id, String name, String tagLine, String firstBrewed, String description,
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

    public BeerViewModel(Beer beer) {
        this.id = beer.getId();
        this.name = beer.getName();
        this.tagLine = beer.getTagline();
        this.firstBrewed = beer.getFirstBrewed();
        this.description = beer.getDescription();
        this.imageUrl = beer.getImageUrl();
        this.alcoholByVolume = beer.getAlcoholByVolume();
        this.foodPairing = beer.getFoodPairing();
        this.brewersTips = beer.getBrewersTips();
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
