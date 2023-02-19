package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

class MovInstructionTest {
  private Machine machine;
  private Registers registers;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
    //...
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
  }

  @Test
  void executeValid() {
    registers.set(EAX, 0);
    Instruction instruction = new MovInstruction(null, EAX, 5);
    instruction.execute(machine);
    Assertions.assertEquals(5, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, 0);
    Instruction instruction = new MovInstruction(null, EAX, -5);
    instruction.execute(machine);
    Assertions.assertEquals(-5, machine.getRegisters().get(EAX));
  }

  @Test
  void toStringTest() {
    String expectedOutput = "mov EAX -5";
    registers.set(EAX, 0);
    registers.set(EBX, 6);
    Instruction instruction = new MovInstruction(null, EAX, -5);
    instruction.execute(machine);
    String testOutput = instruction.toString();
    Assertions.assertEquals(expectedOutput, testOutput);
  }

  @Test
  void toStringWithLabelTest() {
    String expectedOutput = "f3: mov EAX 5";
    registers.set(EAX, 5);
    Instruction instruction = new MovInstruction("f3", EAX, 5);
    instruction.execute(machine);
    String testOutput = instruction.toString();
    Assertions.assertEquals(expectedOutput, testOutput);
  }

  @Test
  void equalsTestTrue() {
    registers.set(EAX, 5);
    Instruction instruction1 = new MovInstruction(null, EAX, 5);
    Instruction instruction2 = new MovInstruction(null, EAX, 5);
    Assertions.assertTrue(instruction1.equals(instruction2));
  }

  @Test
  void equalsTestTrueTwo() {
    registers.set(EAX, 5);
    Instruction instruction1 = new MovInstruction("f3", EAX, -5);
    Instruction instruction2 = new MovInstruction("f3", EAX, -5);
    Assertions.assertTrue(instruction1.equals(instruction2));
  }

  @Test
  void equalsTestFalse() {
    registers.set(EAX, 5);
    Instruction instruction1 = new MovInstruction(null, EAX, 5);
    Instruction instruction2 = new MovInstruction(null, EAX, -5);
    Assertions.assertFalse(instruction1.equals(instruction2));
  }

  @Test
  void equalsTestFalseTwo() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction1 = new MovInstruction(null, EAX, 5);
    Instruction instruction2 = new MovInstruction(null, EBX, 5);
    Assertions.assertFalse(instruction1.equals(instruction2));
  }

  @Test
  void equalsTestFalseThree() {
    registers.set(EAX, 5);
    Instruction instruction1 = new MovInstruction("f3", EAX, -6);
    Instruction instruction2 = new MovInstruction(null, EAX, -6);
    Assertions.assertFalse(instruction1.equals(instruction2));
  }

  @Test
  void hashCodeTest() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new MovInstruction(null, EAX, 5);
    instruction.execute(machine);
    Object hash = instruction.hashCode();
    Assertions.assertEquals(3387338, hash);
  }
}