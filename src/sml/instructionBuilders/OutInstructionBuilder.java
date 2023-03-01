package sml.instructionBuilders;

import sml.Instruction;
import sml.RegisterName;
import sml.Registers.Register;
import sml.instruction.OutInstruction;

/**
 * Builder for the out instruction. Takes the parameters required
 * in String format. Casts them to the appropriate type and returns a
 * fully formed instruction using those parameters.
 * @author AndrewWildgoose
 */
public class OutInstructionBuilder implements InstructionBuilder {

    @Override
    public Instruction buildInstruction(String[] params) {
        String label = params[0];
        RegisterName register = Register.valueOf(params[1]);
        return new OutInstruction(label, register);
    }
}