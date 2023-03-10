package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

class OutInstructionTest {
  private Machine machine;
  private Registers registers;
  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
    System.setOut(new PrintStream(outputStream));
    //...
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
  }

  @Test
  void executeValid() {
    registers.set(EAX, 5);
    Instruction instruction = new OutInstruction(null, EAX);
    instruction.execute(machine);
    Assertions.assertEquals("5", outputStream.toString()
            .trim());
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, -5);
    Instruction instruction = new OutInstruction(null, EAX);
    instruction.execute(machine);
    Assertions.assertEquals("-5", outputStream.toString()
            .trim());
  }

  @Test
  void toStringTest() {
    String expectedOutput = "out EAX";
    registers.set(EAX, 5);
    Instruction instruction = new OutInstruction(null, EAX);
    instruction.execute(machine);
    String testOutput = instruction.toString();
    Assertions.assertEquals(expectedOutput, testOutput);
  }

  @Test
  void toStringWithLabelTest() {
    String expectedOutput = "f3: out EAX";
    registers.set(EAX, 5);
    Instruction instruction = new OutInstruction("f3", EAX);
    instruction.execute(machine);
    String testOutput = instruction.toString();
    Assertions.assertEquals(expectedOutput, testOutput);
  }

  @Test
  void equalsTestTrue() {
    registers.set(EAX, 5);
    Instruction instruction1 = new OutInstruction(null, EAX);
    Instruction instruction2 = new OutInstruction(null, EAX);
    Assertions.assertTrue(instruction1.equals(instruction2));
  }

  @Test
  void equalsTestTrueTwo() {
    registers.set(EAX, 5);
    Instruction instruction1 = new OutInstruction("f3", EAX);
    Instruction instruction2 = new OutInstruction("f3", EAX);
    Assertions.assertTrue(instruction1.equals(instruction2));
  }

  @Test
  void equalsTestFalse() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction1 = new OutInstruction(null, EAX);
    Instruction instruction2 = new OutInstruction(null, EBX);
    Assertions.assertFalse(instruction1.equals(instruction2));
  }

  @Test
  void equalsTestFalseTwo() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction1 = new OutInstruction("f3", EAX);
    Instruction instruction2 = new OutInstruction(null, EAX);
    Assertions.assertFalse(instruction1.equals(instruction2));
  }

  @Test
  void hashCodeTest() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new OutInstruction(null, EAX);
    instruction.execute(machine);
    Object hash = instruction.hashCode();
    Assertions.assertEquals(3452624, hash);
  }
}