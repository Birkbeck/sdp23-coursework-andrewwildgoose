package sml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.exceptions.LabelAlreadyInUseException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static sml.Registers.Register.EAX;

/**
 * Test cases to ensure Translator correctly decodes and passes
 * instructions to the programme ready to be executed.
 *
 * @author AndrewWildgoose
 */

public class TranslatorTest {

    private Machine machine;
    private Translator translator;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

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
     * Integration testing for custom Exception class LabelAlreadyInUseException catches
     * duplicate label and returns correctly formatted error message.
     * @throws LabelAlreadyInUseException
     */
    @Test()
    void duplicateLabelTest1() throws LabelAlreadyInUseException{
        String labelTest1 = "smlFilesForTesting/labelTestFiles/labelTest1.sml";
        translator = new Translator(labelTest1);
        LabelAlreadyInUseException thrown = Assertions.assertThrows(
                LabelAlreadyInUseException.class, () ->
                translator.readAndTranslate(machine.getLabels(), machine.getProgram()));
        Assertions.assertTrue(thrown.getMessage().contentEquals("Label: f1 already in use"));
    }

    /**
     * Test for mov instructions - tested first as all other instruction 
     * tests will require values adding to the registers using the mov instruction.
     * @throws IOException
     */

    // mov test to store value '5' in register EAX - see movTest1.sml
    @Test
    void movTest1() throws IOException {
        String movTest = "smlFilesForTesting/movTestFiles/movTest.sml";
        translator = new Translator(movTest);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        machine.execute();
        Assertions.assertEquals(5, machine.getRegisters().get(EAX));
    }

    /**
     * Tests for add instructions.
     * @throws IOException
     */

    // Simple add test: '5 + 5' - see addTest1.sml file
    @Test
    void addTest1() throws IOException {
        String addTest1 = "smlFilesForTesting/addTestFiles/addTest1.sml";
        translator = new Translator(addTest1);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        machine.execute();
        Assertions.assertEquals(10, machine.getRegisters().get(EAX));
    }

    // Add test using a negative number: '6 + -5' - see addTest2.sml
    @Test
    void addTest2() throws IOException {
        String addTest2 = "smlFilesForTesting/addTestFiles/addTest2.sml";
        translator = new Translator(addTest2);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        machine.execute();
        Assertions.assertEquals(1, machine.getRegisters().get(EAX));
    }

    /**
     * Tests for sub instructions.
     * @throws IOException
     */

    // Simple sub test: '6 - 5' - see subTest1.sml
    @Test
    void subTest1() throws IOException {
        String subTest1 = "smlFilesForTesting/subTestFiles/subTest1.sml";
        translator = new Translator(subTest1);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        machine.execute();
        Assertions.assertEquals(1, machine.getRegisters().get(EAX));
    }
    // Sub test with negative number: '-5 - 6' - see subTest1.sml
    @Test
    void subTest2() throws IOException {
        String subTest2 = "smlFilesForTesting/subTestFiles/subTest2.sml";
        translator = new Translator(subTest2);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        machine.execute();
        Assertions.assertEquals(-11, machine.getRegisters().get(EAX));
    }

    /**
     * Tests for mul instructions.
     * @throws IOException
     */

    // Simple mul test: '6 * 5' - see mulTest1.sml
    @Test
    void mulTest1() throws IOException {
        String mulTest1 = "smlFilesForTesting/mulTestFiles/mulTest1.sml";
        translator = new Translator(mulTest1);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        machine.execute();
        Assertions.assertEquals(30, machine.getRegisters().get(EAX));
    }

    // mul test with negative number: '-4 * 6' - see mulTest2.sml
    @Test
    void mulTest2() throws IOException {
        String mulTest2 = "smlFilesForTesting/mulTestFiles/mulTest2.sml";
        translator = new Translator(mulTest2);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        machine.execute();
        Assertions.assertEquals(-24, machine.getRegisters().get(EAX));
    }

    /**
     * Tests for div instructions.
     * @throws IOException
     */

    // Simple div test: '6 / 2' - see divTest1.sml
    @Test
    void divTest1() throws IOException {
        String divTest1 = "smlFilesForTesting/divTestFiles/divTest1.sml";
        translator = new Translator(divTest1);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        machine.execute();
        Assertions.assertEquals(3, machine.getRegisters().get(EAX));
    }

    // div test with negative number: '-40 / 10' - see diveTest1.sml
    @Test
    void divTest2() throws IOException {
        String divTest2 = "smlFilesForTesting/divTestFiles/divTest2.sml";
        translator = new Translator(divTest2);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        machine.execute();
        Assertions.assertEquals(-4, machine.getRegisters().get(EAX));
    }

    /**
     * Tests for out instructions.
     * @throws IOException
     */

    // Simple out test: '5' - see outTest1.sml
    @Test
    void outTest1() throws IOException {
        String outTest1 = "smlFilesForTesting/outTestFiles/outTest1.sml";
        translator = new Translator(outTest1);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        machine.execute();
        Assertions.assertEquals("5", outputStream.toString()
                .trim());
    }

    // out test with negative number: '-5' - see outTest1.sml
    @Test
    void outTest2() throws IOException {
        String outTest2 = "smlFilesForTesting/outTestFiles/outTest2.sml";
        translator = new Translator(outTest2);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        machine.execute();
        Assertions.assertEquals("-5", outputStream.toString()
                .trim());
    }

    /**
     * Tests for jnz instructions.
     * @throws IOException
     */

    /**
     * jnz test to ensure instruction order is changed:
     * register EAX initiated as '1', register EBX initiated as '5'.
     * jnz instruction will jump to last instruction which prints
     * the value of EAX to the console, the add instruction in between
     * should be missed and the originally initialised value of EAX ('1')
     * should be returned. - See jnzTest1.sml
     */
    @Test
    void jnzTest1() throws IOException {
        String jnzTest1 = "smlFilesForTesting/jnzTestFiles/jnzTest1.sml";
        translator = new Translator(jnzTest1);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        machine.execute();
        Assertions.assertEquals("1", outputStream.toString()
                .trim());
    }

    /**
     * jnz test to ensure instruction order is NOT changed:
     * register EAX initiated as '0', register EBX initiated as '5'.
     * jnz instruction will NOT miss the add instruction in between
     * and will instead add the value of EBX ('5') to the EAX register
     * which will now store 5 ('0+5') and '5' should be returned. - See jnzTest2.sml
     */
    @Test
    void jnzTest2() throws IOException {
        String jnzTest2 = "smlFilesForTesting/jnzTestFiles/jnzTest2.sml";
        translator = new Translator(jnzTest2);
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        machine.execute();
        Assertions.assertEquals("5", outputStream.toString()
                .trim());
    }

}
