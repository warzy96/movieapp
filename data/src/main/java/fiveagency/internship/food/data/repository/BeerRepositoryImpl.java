package fiveagency.internship.food.data.repository;

import fiveagency.internship.food.data.network.client.BeerClient;
import fiveagency.internship.food.domain.model.Beer;
import fiveagency.internship.food.domain.repository.BeerRepository;
import io.reactivex.Single;

public final class BeerRepositoryImpl implements BeerRepository {

    private final BeerClient beerClient;

    public BeerRepositoryImpl(BeerClient beerClient) {
        this.beerClient = beerClient;
    }

    @Override
    public Single<Beer> getRandomBeer() {
        return beerClient.getRandomBeer();
    }
}
