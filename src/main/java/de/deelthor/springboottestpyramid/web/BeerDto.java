package de.deelthor.springboottestpyramid.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import de.deelthor.springboottestpyramid.domain.Beer;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BeerDto {

    private final long id;

    private String brewery;

    private double abv;

    private String style;

    private int ibu;

    public BeerDto(Beer beer){
        id = beer.getId();
        brewery = beer.getBrewery();
        abv = beer.getAbv();
        style = beer.getStyle();
        ibu = beer.getIbu();
    }


}
