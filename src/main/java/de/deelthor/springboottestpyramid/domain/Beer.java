package de.deelthor.springboottestpyramid.domain;

import java.util.Objects;

public class Beer {
    private final long id;
    private final String brewery;
    private final double abv;
    private final String style;
    private final int ibu;

    public Beer(long id, String brewery, double abv, String style, int ibu) {
        this.id = id;
        this.brewery = Objects.requireNonNull(brewery, "Parameter 'brewery' must not be null");
        this.abv = abv;
        this.style = Objects.requireNonNull(style, "Parameter 'style' must not be null");;
        this.ibu = ibu;
    }

    public long getId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return id == beer.id &&
                Double.compare(beer.abv, abv) == 0 &&
                ibu == beer.ibu &&
                Objects.equals(brewery, beer.brewery) &&
                Objects.equals(style, beer.style);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brewery, abv, style, ibu);
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", brewery='" + brewery + '\'' +
                ", abv=" + abv +
                ", style='" + style + '\'' +
                ", ibu=" + ibu +
                '}';
    }
}
