package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import java.util.ArrayList;
import java.util.List;

import static sml.Registers.Register.*;

class JnzInstructionTest {
  private Machine machine;
  private Registers registers;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
    machine.getLabels().addLabel("f3", 2);
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
    Instruction instruction = new JnzInstruction(null, EAX, "f3");
    Assertions.assertEquals(-1, instruction.execute(machine));
  }

  @Test
  void executeValidTwo() {
    registers.set(EBX, 5);
    Instruction instruction = new JnzInstruction(null, EBX, "f3");
    Assertions.assertEquals(2, instruction.execute(machine));
  }

  @Test
  void toStringTest() {
    String expectedOutput = "jnz EAX f3";
    registers.set(EAX, 0);
    Instruction instruction = new JnzInstruction(null, EAX, "f3");
    instruction.execute(machine);
    String testOutput = instruction.toString();
    Assertions.assertEquals(expectedOutput, testOutput);
  }

  @Test
  void toStringWithLabelTest() {
    String expectedOutput = "f1: jnz EAX f3";
    registers.set(EAX, 0);
    Instruction instruction = new JnzInstruction("f1", EAX, "f3");
    instruction.execute(machine);
    String testOutput = instruction.toString();
    Assertions.assertEquals(expectedOutput, testOutput);
  }
}