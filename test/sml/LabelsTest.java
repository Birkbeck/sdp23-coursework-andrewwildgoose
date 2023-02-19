package sml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.exceptions.LabelAlreadyInUseException;

/**
 * Tests for Labels class.
 *
 * @author AndrewWildgoose
 */

public class LabelsTest {

    private Labels labels;

    @BeforeEach
    void setUp() {
        labels = new Labels();
    }

    @AfterEach
    void tearDown() {
        labels.reset();
    }

    /**
     * Testing overridden toString() method.
     */
    @Test
    void toStringTest() {
        labels.addLabel("f1", 1);
        labels.addLabel("f2", 2);
        labels.addLabel("f3", 3);
        String labelsExpected = "[Label: f1 at pos: 1, \n" +
                "Label: f2 at pos: 2, \n" +
                "Label: f3 at pos: 3]";
        Assertions.assertEquals(labelsExpected, labels.toString());
    }

    /**
     * Testing addLabel() method.
     */
    @Test()
    void duplicateLabelTest1() throws LabelAlreadyInUseException {
        labels.addLabel("f1", 1);
        LabelAlreadyInUseException thrown = Assertions.assertThrows(
                LabelAlreadyInUseException.class, () ->
                        labels.addLabel("f1", 2));
        Assertions.assertTrue(thrown.getMessage().contentEquals("Label: f1 already in use"));
    }
}
