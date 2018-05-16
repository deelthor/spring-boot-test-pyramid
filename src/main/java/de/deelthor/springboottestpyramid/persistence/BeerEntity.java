package de.deelthor.springboottestpyramid.persistence;

import de.deelthor.springboottestpyramid.domain.AddBeerCommand;
import de.deelthor.springboottestpyramid.domain.Beer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class BeerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brewery;

    private double abv;

    private String style;

    private int ibu;

    public BeerEntity() {
    }

    public BeerEntity(String brewery, double abv, String style, int ibu) {
        this.brewery = brewery;
        this.abv = abv;
        this.style = style;
        this.ibu = ibu;
    }

    public Long getId() {
        return id;
    }

    public String getBrewery() {
        return brewery;
    }

    public double getAbv() {
        return abv;
    }

    public String getStyle() {
        return style;
    }

    public int getIbu() {
        return ibu;
    }

    public Beer toBeer(){
        return new Beer(id, brewery, abv, style, ibu);
    }

    public static BeerEntity fromAddBeerCommand(AddBeerCommand addBeerCommand) {
        return new BeerEntity(
                addBeerCommand.getBrewery(),
                addBeerCommand.getAbv(),
                addBeerCommand.getStyle(),
                addBeerCommand.getIbu());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeerEntity that = (BeerEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BeerEntity{" +
                "id=" + id +
                '}';
    }

}
