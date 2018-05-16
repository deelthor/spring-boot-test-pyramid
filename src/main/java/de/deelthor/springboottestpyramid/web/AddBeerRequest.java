package de.deelthor.springboottestpyramid.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import de.deelthor.springboottestpyramid.domain.AddBeerCommand;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AddBeerRequest {

    private String brewery;
    private double abv;
    private String style;
    private int ibu;

    AddBeerCommand toAddBeerCommand() {
        AddBeerCommand addBeerCommand = new AddBeerCommand();
        addBeerCommand.setBrewery(brewery);
        addBeerCommand.setAbv(abv);
        addBeerCommand.setStyle(style);
        addBeerCommand.setIbu(ibu);
        return addBeerCommand;
    }


}
