package sml.instructionBuilders;

import sml.Instruction;

public interface InstructionBuilder {

    Instruction buildInstruction(String... args);
}
