package gui.view.util;

import gui.model.Coordinates;
import gui.view.util.converter.CoordinatesToTechnicalCoordinatesConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinatesToTechnicalCoordinatesConverterTest {

    @Test
    void conversionTest() {
        //Given a logic coordinates
        Coordinates c = Coordinates.of(1, 2);

        //When we try to convert to a technical coordinate
        TechnicalCoordinates tc = CoordinatesToTechnicalCoordinatesConverter.convert(c);

        //Then we expect to have this coordinate
        assertEquals(15, tc.getX());
        assertEquals(1, tc.getY());
    }
}