package sml.instructionBuilders;

import sml.Instruction;

/**
 * Interface for the instruction builders. Allows the Translator
 * to work with any type of instruction provided it has an Instruction class,
 * a relevant Builder and an appropriate Bean.
 * @author AndrewWildgoose
 */
public interface InstructionBuilder {

    Instruction buildInstruction(String... args);
}
