package sml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register;

/**
 * Tests for Registers class.
 *
 * @author AndrewWildgoose
 */
public class RegistersTest {

    private Machine machine1;
    private Machine machine2;
    private Registers registers1;
    private Registers registers2;
    @BeforeEach
    void setUp() {
        machine1 = new Machine(new Registers());
        registers1 = machine1.getRegisters();

        machine2 = new Machine(new Registers());
        registers2 = machine2.getRegisters();
    }

    @AfterEach
    void tearDown() {
        machine1 = null;
        registers1 = null;

        machine2 = null;
        registers2 = null;
    }

    /**
     * tests for overriden equals() method.
     */
    // Test to ensure newly created sets of registers are considered equal
    // after being initialised all containing '0'.
    @Test
    void equalsTest1() {
        Assertions.assertTrue(registers1.equals(registers2));
    }

    // Test to ensure registers are still considered equal after a value
    // has been placed into the EAX register of each set.
    @Test
    void equalsTest2() {
        registers1.set(EAX, 1);
        registers2.set(EAX, 1);
        Assertions.assertTrue(registers1.equals(registers2));
    }

    // Test to ensure registers are not considered equal when a value has
    // only been placed into one set of registers.
    @Test
    void equalsTest3() {
        registers1.set(EAX, 1);
        Assertions.assertFalse(registers1.equals(registers2));
    }
}
