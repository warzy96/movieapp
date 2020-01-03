package fiveagency.internship.food.data.network.client;

import fiveagency.internship.food.data.network.mappers.BeerMapper;
import fiveagency.internship.food.data.network.service.BeerService;
import fiveagency.internship.food.domain.model.Beer;
import io.reactivex.Single;

public final class BeerClient {

    private final BeerService beerService;
    private final BeerMapper beerMapper;

    public BeerClient(BeerService beerService, BeerMapper beerMapper) {
        this.beerService = beerService;
        this.beerMapper = beerMapper;
    }

    public Single<Beer> getRandomBeer() {
        return beerService.fetchRandomBeer().map(beerMapper::mapBeer);
    }
}
