package gui.controller.util;

import gui.controller.GameOptions;
import gui.model.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CoordinatesValidatorTest {

    @Test
    void testAssertFalse() {
        assertFalse(CoordinatesValidator.validate(200, 2011));
        assertFalse(CoordinatesValidator.validate(2011, 200));
        assertFalse(CoordinatesValidator.validate(0, -5));
        assertFalse(CoordinatesValidator.validate(-8, -5));
    }

    @Test
    void testAssertFalseWithCoordinatesObj() {
        assertFalse(CoordinatesValidator.validate(Coordinates.of(200, 2011)));
        assertFalse(CoordinatesValidator.validate(Coordinates.of(2011, 200)));
        assertFalse(CoordinatesValidator.validate(Coordinates.of(0, -5)));
        assertFalse(CoordinatesValidator.validate(Coordinates.of(-8, -5)));
    }

    @Test
    void testAssertTrue() {
        for (int i = 0; i <= GameOptions.getNbRows(); i++) {
            for (int j = 0; j <= GameOptions.getNbColumns(); j++) {
                assertTrue(CoordinatesValidator.validate(i, j));
            }
        }
    }
}