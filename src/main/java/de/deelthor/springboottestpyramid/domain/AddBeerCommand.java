package de.deelthor.springboottestpyramid.domain;

public class AddBeerCommand {

    private String brewery;
    private double abv;
    private String style;
    private int ibu;


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

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setIbu(int ibu) {
        this.ibu = ibu;
    }
}
