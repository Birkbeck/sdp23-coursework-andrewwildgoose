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

class MulInstructionTest {
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
    Instruction instruction = new MulInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(15, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, -4);
    registers.set(EBX, 6);
    Instruction instruction = new MulInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(-24, machine.getRegisters().get(EAX));
  }

  @Test
  void toStringTest() {
    String expectedOutput = "mul EAX EBX";
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new MulInstruction(null, EAX, EBX);
    instruction.execute(machine);
    String testOutput = instruction.toString();
    Assertions.assertEquals(expectedOutput, testOutput);
  }

  @Test
  void toStringWithLabelTest() {
    String expectedOutput = "f3: mul EAX EBX";
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new MulInstruction("f3", EAX, EBX);
    instruction.execute(machine);
    String testOutput = instruction.toString();
    Assertions.assertEquals(expectedOutput, testOutput);
  }

  @Test
  void equalsTestTrue() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction1 = new MulInstruction(null, EAX, EBX);
    Instruction instruction2 = new MulInstruction(null, EAX, EBX);
    Assertions.assertTrue(instruction1.equals(instruction2));
  }

  @Test
  void equalsTestTrueTwo() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction1 = new MulInstruction("f3", EAX, EBX);
    Instruction instruction2 = new MulInstruction("f3", EAX, EBX);
    Assertions.assertTrue(instruction1.equals(instruction2));
  }

  @Test
  void equalsTestFalse() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction1 = new MulInstruction(null, EAX, EBX);
    Instruction instruction2 = new MulInstruction(null, EBX, EAX);
    Assertions.assertFalse(instruction1.equals(instruction2));
  }

  @Test
  void equalsTestFalseTwo() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction1 = new MulInstruction("f3", EAX, EBX);
    Instruction instruction2 = new MulInstruction(null, EAX, EBX);
    Assertions.assertFalse(instruction1.equals(instruction2));
  }

  @Test
  void hashCodeTest() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new MulInstruction("f3", EAX, EBX);
    instruction.execute(machine);
    Object hash = instruction.hashCode();
    Assertions.assertEquals(6480487, hash);
  }
}