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

class SubInstructionTest {
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
    registers.set(EAX, 5);
    registers.set(EBX, 3);
    Instruction instruction = new SubInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(2, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, -5);
    registers.set(EBX, 6);
    Instruction instruction = new SubInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(-11, machine.getRegisters().get(EAX));
  }

  @Test
  void toStringTest() {
    String expectedOutput = "sub EAX EBX";
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new SubInstruction(null, EAX, EBX);
    instruction.execute(machine);
    String testOutput = instruction.toString();
    Assertions.assertEquals(expectedOutput, testOutput);
  }

  @Test
  void toStringWithLabelTest() {
    String expectedOutput = "f3: sub EAX EBX";
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new SubInstruction("f3", EAX, EBX);
    instruction.execute(machine);
    String testOutput = instruction.toString();
    Assertions.assertEquals(expectedOutput, testOutput);
  }

  @Test
  void equalsTestTrue() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction1 = new SubInstruction(null, EAX, EBX);
    Instruction instruction2 = new SubInstruction(null, EAX, EBX);
    Assertions.assertTrue(instruction1.equals(instruction2));
  }

  @Test
  void equalsTestTrueTwo() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction1 = new SubInstruction("f3", EAX, EBX);
    Instruction instruction2 = new SubInstruction("f3", EAX, EBX);
    Assertions.assertTrue(instruction1.equals(instruction2));
  }

  @Test
  void equalsTestFalse() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction1 = new SubInstruction(null, EAX, EBX);
    Instruction instruction2 = new SubInstruction(null, EBX, EAX);
    Assertions.assertFalse(instruction1.equals(instruction2));
  }

  @Test
  void equalsTestFalseTwo() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction1 = new SubInstruction("f3", EAX, EBX);
    Instruction instruction2 = new SubInstruction(null, EAX, EBX);
    Assertions.assertFalse(instruction1.equals(instruction2));
  }

  @Test
  void hashCodeTest() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new SubInstruction("f3", EAX, EBX);
    instruction.execute(machine);
    Object hash = instruction.hashCode();
    Assertions.assertEquals(6658923, hash);
  }
}