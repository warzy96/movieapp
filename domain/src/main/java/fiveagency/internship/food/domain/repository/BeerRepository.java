package fiveagency.internship.food.domain.repository;

import fiveagency.internship.food.domain.model.Beer;
import io.reactivex.Single;

public interface BeerRepository {

    Single<Beer> getRandomBeer();
}
