package fiveagency.internship.food.movieapp.ui.moviedetails.beer;

import fiveagency.internship.food.domain.model.Beer;

public final class BeerViewModelMapper {

    public BeerViewModel mapBeerViewModel(Beer beer) {
        return new BeerViewModel(beer);
    }
}
