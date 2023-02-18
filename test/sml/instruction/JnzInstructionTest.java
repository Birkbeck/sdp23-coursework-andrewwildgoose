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
  private Instruction inst1;
  private Instruction inst2;
  private Instruction inst3;
  private List<Instruction> program;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
    inst1 = new AddInstruction(null, EAX, EBX);
    inst2 = new SubInstruction("f3", EAX, ECX);
    inst3 = new JnzInstruction(null, EAX, "f3");
    program = new ArrayList<>();
    program.add(inst1);
    program.add(inst2);
    program.add(inst3);
    //...
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
    program = null;
    inst1 = null;
    inst2 = null;
    inst3 = null;
  }

  @Test
  void executeValid() {
    registers.set(EAX, 0);
    registers.set(EBX, 5);
    registers.set(ECX, 3);
    Instruction instruction = new JnzInstruction(null, EAX, "f3");
    instruction.execute(machine);
    Assertions.assertEquals("f3", machine);
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, -5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }
}