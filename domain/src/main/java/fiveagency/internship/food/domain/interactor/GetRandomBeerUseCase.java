package fiveagency.internship.food.domain.interactor;

import fiveagency.internship.food.domain.interactor.type.FetchUseCase;
import fiveagency.internship.food.domain.model.Beer;
import fiveagency.internship.food.domain.repository.BeerRepository;
import io.reactivex.Single;

public final class GetRandomBeerUseCase implements FetchUseCase<Single<Beer>> {

    private final BeerRepository beerRepository;

    public GetRandomBeerUseCase(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public Single<Beer> execute() {
        return beerRepository.getRandomBeer();
    }
}
