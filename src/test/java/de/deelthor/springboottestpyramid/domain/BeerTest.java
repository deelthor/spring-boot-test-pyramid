package de.deelthor.springboottestpyramid.domain;

import org.junit.Test;

public class BeerTest {

    @Test(expected = NullPointerException.class)
    public void should_throw_exception_when_passing_null_breweryname() {
        new Beer(1, null, 5.6, "IPA", 45);
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_exception_when_passing_null_style() {
        new Beer(1, "big_endian_brewery", 5.6, null, 45);
    }
}
