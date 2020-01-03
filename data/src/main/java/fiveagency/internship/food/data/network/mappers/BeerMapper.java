package fiveagency.internship.food.data.network.mappers;

import java.util.LinkedList;
import java.util.List;

import fiveagency.internship.food.data.network.model.ApiBeer;
import fiveagency.internship.food.domain.model.Beer;

public final class BeerMapper {

    public Beer mapBeer(final List<ApiBeer> apiBeers) {
        if (apiBeers == null) {
            return Beer.EMPTY;
        }
        final ApiBeer apiBeer = apiBeers.get(0);
        return new Beer(apiBeer.id,
                        apiBeer.name,
                        apiBeer.tagLine == null ? Beer.EMPTY.getTagline() : apiBeer.tagLine,
                        apiBeer.firstBrewed == null ? Beer.EMPTY.getFirstBrewed() : apiBeer.firstBrewed,
                        apiBeer.description == null ? Beer.EMPTY.getDescription() : apiBeer.description,
                        apiBeer.imageUrl == null ? Beer.EMPTY.getImageUrl() : apiBeer.imageUrl,
                        apiBeer.alcoholByVolume == 0.0 ? Beer.EMPTY.getAlcoholByVolume() : apiBeer.alcoholByVolume,
                        apiBeer.foodPairing == null ? Beer.EMPTY.getFoodPairing() : apiBeer.foodPairing,
                        apiBeer.brewersTips == null ? Beer.EMPTY.getBrewersTips() : apiBeer.brewersTips);
    }

    public Beer mapBeer(final ApiBeer apiBeer) {
        if (apiBeer == null) {
            return Beer.EMPTY;
        }
        return new Beer(apiBeer.id,
                        apiBeer.name,
                        apiBeer.tagLine == null ? Beer.EMPTY.getTagline() : apiBeer.tagLine,
                        apiBeer.firstBrewed == null ? Beer.EMPTY.getFirstBrewed() : apiBeer.firstBrewed,
                        apiBeer.description == null ? Beer.EMPTY.getDescription() : apiBeer.description,
                        apiBeer.imageUrl == null ? Beer.EMPTY.getImageUrl() : apiBeer.imageUrl,
                        apiBeer.alcoholByVolume == 0.0 ? Beer.EMPTY.getAlcoholByVolume() : apiBeer.alcoholByVolume,
                        apiBeer.foodPairing == null ? Beer.EMPTY.getFoodPairing() : apiBeer.foodPairing,
                        apiBeer.brewersTips == null ? Beer.EMPTY.getBrewersTips() : apiBeer.brewersTips);
    }

    public List<Beer> mapBeers(List<ApiBeer> apiBeers) {
        final List<Beer> result = new LinkedList<>();
        for (final ApiBeer apiBeer : apiBeers) {
            result.add(mapBeer(apiBeer));
        }
        return result;
    }
}
