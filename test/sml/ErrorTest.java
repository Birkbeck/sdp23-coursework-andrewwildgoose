package sml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import sml.exceptions.LabelAlreadyInUseException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static sml.Registers.Register.EAX;

/**
 * Test cases to check errors are caught correctly and the program terminates with useful error message.
 *
 * @author AndrewWildgoose
 */
public class ErrorTest {

    private Machine machine;
    private Translator translator;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private Labels labels;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        machine = null;
    }

    /**
     * Testing the translator sends a useful error code when passed an unknown opcode,
     * it is not able to execute and terminates the program.
     * @throws IOException
     */
    @Test
    void ErrorTest1() throws NoSuchBeanDefinitionException {
        String errorTest1 = "smlFilesForTesting/ErrorTestFiles/ErrorTest1.sml";
        translator = new Translator(errorTest1);
        NoSuchBeanDefinitionException thrown =
                Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                        () -> translator.readAndTranslate(machine.getLabels(), machine.getProgram()));
        Assertions.assertEquals("Unknown instruction: mav\nThat instruction type has not been added to this translator",
                outputStream.toString().trim());
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
