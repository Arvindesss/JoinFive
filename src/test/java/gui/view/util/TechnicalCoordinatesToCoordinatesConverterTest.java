package gui.view.util;

import gui.model.Coordinates;
import gui.view.util.converter.TechnicalCoordinatesToCoordinatesConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TechnicalCoordinatesToCoordinatesConverterTest {

    @Test
    void conversionTest() {
        //Given a logic coordinates
        TechnicalCoordinates tc = TechnicalCoordinates.of(1, 15);

        //When we try to convert to a technical coordinate
        Coordinates c = TechnicalCoordinatesToCoordinatesConverter.convert(tc);

        //Then we expect to have this coordinate
        assertEquals(1, c.getX());
        assertEquals(2, c.getY());
    }

    @Test
    void conversionTest2() {
        //Given a logic coordinates
        TechnicalCoordinates tc = TechnicalCoordinates.of(14, 7);

        //When we try to convert to a technical coordinate
        Coordinates c = TechnicalCoordinatesToCoordinatesConverter.convert(tc);

        //Then we expect to have this coordinate
        assertEquals(14, c.getX());
        assertEquals(10, c.getY());
    }

}