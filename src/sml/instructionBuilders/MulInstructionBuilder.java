package sml.instructionBuilders;

import sml.Instruction;
import sml.RegisterName;
import sml.Registers.Register;
import sml.instruction.MulInstruction;

/**
 * Builder for the mul instruction. Takes the parameters required
 * in String format. Casts them to the appropriate type and returns a
 * fully formed instruction using those parameters.
 * @author AndrewWildgoose
 */
public class MulInstructionBuilder implements InstructionBuilder {

    @Override
    public Instruction buildInstruction(String[] params) {
        String label = params[0];
        RegisterName register = Register.valueOf(params[1]);
        RegisterName source = Register.valueOf(params[2]);
        return new MulInstruction(label, register, source);
    }
}